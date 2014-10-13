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
import edu.unsw.comp9321.model.Review;

public class ReviewMovieCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Review review = new Review();
		Long movieID = Long.parseLong(request.getParameter("movieId"));
		review.setUsername(session.getAttribute("username").toString());
		review.setMovieId(movieID);
		review.setText(request.getParameter("review"));
		review.setRating(Integer.parseInt(request.getParameter("rating")));
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.addReview(review);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("control?action=detailMovie&id="+movieID);
		rd.forward(request, response);
	}

}
