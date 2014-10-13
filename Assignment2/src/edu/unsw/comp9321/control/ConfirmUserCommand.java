package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class ConfirmUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//http://localhost:8080/Assign2/control?action=confirmUser&code=
		
		String code = request.getParameter("code");
		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.confirmUser(code);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}

	}

}
