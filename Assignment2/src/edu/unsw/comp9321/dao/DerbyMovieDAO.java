package edu.unsw.comp9321.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.DBConnectionFactory;
import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.CinemaSession;
import edu.unsw.comp9321.model.Movie;
import edu.unsw.comp9321.model.Review;
import edu.unsw.comp9321.model.UserLogin;

public class DerbyMovieDAO implements MovieDAO {
	static Logger logger = Logger.getLogger(DerbyMovieDAO.class.getName());
	private Connection connection;
	
	public DerbyMovieDAO() throws ServiceLocatorException, SQLException, NamingException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");	
	}
	
	@Override
	public List<Movie> getMovieList() {
		// TODO Auto-generated method stub
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		try{
			Statement stmnt = connection.createStatement();
			String query = "SELECT ID, TITLE, AGE_RATING, RATING FROM MOVIE";			
			
			ResultSet res = stmnt.executeQuery(query);
			logger.info("The result set size is "+res.getFetchSize());
			
			while(res.next()){
				int id = res.getInt("ID");
				String title = res.getString("TITLE");
				int age_rating = res.getInt("AGE_RATING");
				int rating = res.getInt("RATING");
				//movieList.add(new Movie(id, title, age_rating, rating));
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return movieList;
	}


	@Override
	public List<Cinema> getCinemaList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMovie(Movie movie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Movie> getNowShowingMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> getComingSoonMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(UserLogin user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Movie> searchMovie(String title, String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie getMovie(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cinema getCinema(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCinema(Cinema cinema) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCinemaSession(CinemaSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CinemaSession> getMovieSessions(Long movieID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReview(Review review) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Review> getReviewList(Long movieID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSessionAvailable(Long cinemaSessionID, int numTickets) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CinemaSession getCinemaSession(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
