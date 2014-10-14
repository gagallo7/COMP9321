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
import edu.unsw.comp9321.model.Booking;
import edu.unsw.comp9321.model.CinemaSession;

public class CheckoutBookingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int numTickets = Integer.parseInt(request.getParameter("numTickets"));
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			Booking book = new Booking();
			CinemaSession cinemaSession = dao.getCinemaSession(Long.parseLong(request.getParameter("cinemaSessionID")));
			cinemaSession.setAvailableSeats(cinemaSession.getAvailableSeats()-numTickets);
			dao.updateCinemaSession(cinemaSession);
			
			book.setCinemaSession(cinemaSession);
			book.setUsername((String) session.getAttribute("username"));
			book.setNumberOfTickets(numTickets);

			dao.addBooking(book);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("control?action=detailBookings");
		rd.forward(request, response);
	}

}
