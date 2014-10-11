package edu.unsw.comp9321.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.Session;

import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.HibernateSessionFactory;
import edu.unsw.comp9321.model.Movie;

public class HibernateMovieDAO implements MovieDAO {
	static Logger logger = Logger.getLogger(DerbyMovieDAO.class.getName());
	//private static SessionFactory sessionFactory;
	private Session session;
	
	public HibernateMovieDAO() throws ServiceLocatorException, SQLException, NamingException{
		//sessionFactory = new Configuration().configure().buildSessionFactory();
		//session = sessionFactory.openSession();
		session = HibernateSessionFactory.getSession();		
		logger.info("Got connection");	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getList() {
		List<Movie> movieList = new ArrayList<Movie>();
		Criteria criteria = session.createCriteria(Movie.class);
		movieList = criteria.list();
		return movieList;
	}

}
