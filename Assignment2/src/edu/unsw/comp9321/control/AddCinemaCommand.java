package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.Cinema;

public class AddCinemaCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cinema cinema = new Cinema();
		cinema.setLocation(request.getParameter("location"));
		cinema.setSeatingCapacity((Integer.parseInt(request.getParameter("seatCap"))));
		
		String[] amenities = request.getParameterValues("amenities");
		if (amenities != null){
			for(String amenitie: amenities){
				if (amenitie.equals("atm")){
					cinema.setATM(1);
				}else if (amenitie.equals("restaurant")){
					cinema.setRestaurant(1);
				}else if (amenitie.equals("snack")){
					cinema.setSnackBar(1);
				}else if (amenitie.equals("widescreen")){
					cinema.setWidescreen(1);
				}
			}
		}

		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.addCinema(cinema);
			dao.closeSession();
		} catch (ServiceLocatorException e) {
			throw new ServletException();
		}

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/restricted/cinemas.jsp");
		rd.forward(request, response);
	}
}
