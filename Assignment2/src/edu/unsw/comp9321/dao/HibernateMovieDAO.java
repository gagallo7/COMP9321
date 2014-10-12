package edu.unsw.comp9321.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.HibernateSessionFactory;
import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.Movie;

public class HibernateMovieDAO implements MovieDAO {
	static Logger logger = Logger.getLogger(DerbyMovieDAO.class.getName());
	private Session session;
	
	public HibernateMovieDAO() throws ServiceLocatorException {
		session = HibernateSessionFactory.getSession();
		logger.info("Got connection");			
	}
	
	@Override
	public void addMovie(Movie movie){
		session.beginTransaction();
		session.save(movie);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovieList() {
		List<Movie> movieList = new ArrayList<Movie>();
		Criteria criteria = session.createCriteria(Movie.class);
		movieList = criteria.list();
		session.close();
		return movieList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getNowShowingMovies() {
		List<Movie> movieList = new ArrayList<Movie>();
		Criteria criteria = session.createCriteria(Movie.class);
		criteria.add(Restrictions.le("releaseDate", Calendar.getInstance().getTime())); //less or equal to
		criteria.addOrder(Order.desc("ageRating"));
		criteria.setMaxResults(3);
		movieList = criteria.list();
		return movieList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getComingSoonMovies() {
		List<Movie> movieList = new ArrayList<Movie>();
		Criteria criteria = session.createCriteria(Movie.class);
		criteria.add(Restrictions.gt("releaseDate", Calendar.getInstance().getTime())); //greater then
		criteria.addOrder(Order.asc("releaseDate"));
		criteria.setMaxResults(3);
		movieList = criteria.list();
		session.close();
		return movieList;
	}

	@Override
	public void createCinema(Cinema cinema) {
		session.beginTransaction();
		session.save(cinema);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemaList() {
		List<Cinema> cinemaList = new ArrayList<Cinema>();
		Criteria criteria = session.createCriteria(Cinema.class);
		cinemaList = criteria.list();
		session.close();
		return cinemaList;
	}





}
