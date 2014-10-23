package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.UserLogin;
import edu.unsw.comp9321.util.SaferStringUtil;

public class EditProfileCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.getSession().setAttribute("info", "Edit profile ");
		String nickname = SaferStringUtil.convert(request.getParameter("nickname"));
		String firstName = SaferStringUtil.convert(request.getParameter("firstName"));
		String lastName = SaferStringUtil.convert(request.getParameter("lastName"));
		String email = SaferStringUtil.convert(request.getParameter("email"));
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			UserLogin user = dao.getUser((String) session.getAttribute("username"));
			
			if(!nickname.isEmpty())
				user.setNickname(nickname);
			if(!firstName.isEmpty())
				user.setFirstName(firstName);
			if(!lastName.isEmpty())
				user.setLastName(lastName);
			if(!email.isEmpty())
				user.setEmail(email);
			
			dao.updateUser(user);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	
//	RequestDispatcher rd = request.getRequestDispatcher("control?action=detailProfile");
		
	RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
	rd.forward(request, response);
	
	

	}

}
