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

public class ToProfileCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			MovieDAO dao = new HibernateMovieDAO();
			UserLogin user = dao.getUser((String) session.getAttribute("username"));
			request.setAttribute("user", user);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	
	RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
	rd.forward(request, response);

	}

}
