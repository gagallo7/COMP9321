package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class BookSessionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long cinemaSessionID = Long.parseLong(request.getParameter("sessionId"));
		int numTickets = Integer.parseInt(request.getParameter("numTickets"));
		
		MovieDAO dao;
		try {
			dao = new HibernateMovieDAO();
			if (dao.isSessionAvailable(cinemaSessionID, numTickets)){
				request.setAttribute("cinemaSession", dao.getCinemaSession(cinemaSessionID));
				RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
				rd.forward(request, response);
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	}

}
