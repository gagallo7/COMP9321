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
import edu.unsw.comp9321.model.Movie;

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
				movieList.add(new Movie(id, title, age_rating, rating));
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
	public void createCinema(Cinema cinema) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cinema> getCinemaList() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
