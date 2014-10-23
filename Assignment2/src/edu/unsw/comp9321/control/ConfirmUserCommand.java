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

public class ConfirmUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8080/Assign2/control?action=confirmUser&code=
		HttpSession session = request.getSession(); // it can no longer have the
													// session, so we can the
													// username from the db

		String code = request.getParameter("code");

		try {
			MovieDAO dao = new HibernateMovieDAO();
			UserLogin user = dao.confirmUser(code);
			request.setAttribute("user", user);
			dao.closeSession();
			session.setAttribute("UserRole", "user");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("nickname", user.getNickname());
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/restricted/profile.jsp");
		rd.forward(request, response);

	}

}
