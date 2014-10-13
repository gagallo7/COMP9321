package edu.unsw.comp9321.jdbc;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.ObjectFactory;



import edu.unsw.comp9321.exception.ServiceLocatorException;

public class MailSessionFactory extends ServiceLocator {
	static Logger logger = Logger.getLogger(MailSessionFactory.class.getName());
	
	private static MailSessionFactory factory = null; //Singleton
	private Session session = null;
	
	public MailSessionFactory() throws ServiceLocatorException{
		super();
	}
	
	public Session getSession() throws ServiceLocatorException{
		if (session== null){
			try {
				session = (Session) lookup("java:comp/env/mail/cs9321");
				logger.info("Session Factory found:"+session.toString());	
			} catch (NamingException e) {
				logger.severe("Cannot get Session Factory " + e.getStackTrace());
				throw new ServiceLocatorException();
			}	
		}
		return session;
	}
	
}
