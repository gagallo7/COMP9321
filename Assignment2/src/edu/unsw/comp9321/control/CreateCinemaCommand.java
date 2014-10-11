package edu.unsw.comp9321.control;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.Movie;

public class CreateCinemaCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
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

		MovieDAO dao = null;
		try {
			dao = new HibernateMovieDAO();
			dao.createCinema(cinema);
			
			List<Cinema> cinemas = dao.getCinemaList();
			if (cinemas == null)
				System.out.println("VAZIA");
			else{
				for (Cinema dcinema: cinemas){
					System.out.println(dcinema.getLocation());
				}			
			}
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.setAttribute("carList", carDAO.findAll());
		//destinationPage = "/carList.jsp";
	}
}
