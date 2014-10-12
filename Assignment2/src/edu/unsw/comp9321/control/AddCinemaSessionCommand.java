package edu.unsw.comp9321.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.CinemaSession;

public class AddCinemaSessionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CinemaSession cinemaSession = new CinemaSession();
		
		Long cinemaID = Long.parseLong(request.getParameter("movieId"));
		Long movieID = Long.parseLong(request.getParameter("cinemaId"));
		cinemaSession.setMovieId(cinemaID);
		cinemaSession.setCinemaId(movieID);
		
		String showTime = request.getParameter("showTime");
		String showDate = request.getParameter("showDate");
		String finalShowTime = showDate.concat(" ").concat(showTime);	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");	
		Date date;
		
		try {
			date = dateFormat.parse(finalShowTime);
			cinemaSession.setShowTime(date);
			
			MovieDAO dao = new HibernateMovieDAO();
			Cinema cinema = dao.getCinema(cinemaID);
			cinemaSession.setAvailableSeats(cinema.getSeatingCapacity());
			dao.addCinemaSession(cinemaSession);
			dao.closeSession();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("control?action=detailMovie&id="+movieID);
		rd.forward(request, response);
	
	}

}
