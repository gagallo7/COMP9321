package edu.unsw.comp9321.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.DBConnectionFactory;

public class DerbyMovieDAO  {
	static Logger logger = Logger.getLogger(DerbyMovieDAO.class.getName());
	private Connection connection;
	
	public DerbyMovieDAO() throws ServiceLocatorException, SQLException, NamingException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");	
	}
}
