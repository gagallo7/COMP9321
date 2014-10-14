package edu.unsw.comp9321.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.Booking;

public class DetailBookingsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			MovieDAO dao = new HibernateMovieDAO();
			List<Booking> bookingList = dao.getBookings((String) session.getAttribute("username"));
			request.setAttribute("bookings", bookingList);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	
	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/restricted/booking.jsp");
	rd.forward(request, response);

	}

}
