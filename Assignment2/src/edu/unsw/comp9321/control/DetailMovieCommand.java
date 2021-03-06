package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.CinemaSession;

public class DetailMovieCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			request.setAttribute("movie", dao.getMovie(id));
			//get comments
			request.setAttribute("cinemas",  dao.getCinemaList());
			request.setAttribute("sessions", dao.getMovieSessions(id));
			request.setAttribute("reviews",  dao.getReviewList(id));
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("details.jsp");
		rd.forward(request, response);
	}

}
