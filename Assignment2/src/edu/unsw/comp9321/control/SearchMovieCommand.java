package edu.unsw.comp9321.control;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.control.Command;
import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.util.SaferStringUtil;


public class SearchMovieCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = SaferStringUtil.convert(request.getParameter("title"));
		String genre =  request.getParameter("genre");
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			request.setAttribute("movies", dao.searchMovie(title, genre));
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/restricted/results.jsp");
		rd.forward(request, response);
	}

}
