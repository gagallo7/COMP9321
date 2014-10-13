package edu.unsw.comp9321.jdbc;

import java.util.logging.Logger;

import javax.mail.Session;
import javax.naming.NamingException;

import edu.unsw.comp9321.exception.ServiceLocatorException;

public class MailSessionFactory extends ServiceLocator {
	static Logger logger = Logger.getLogger(MailSessionFactory.class.getName());
	
	private static MailSessionFactory factory = null; //Singleton
	private Session session = null;
	
	public MailSessionFactory() throws ServiceLocatorException{
		super();
		try {
			session = (Session) lookup("java:comp/env/mail/cs9321");
			logger.info("Session found:"+session.toString());
		} catch (NamingException e) {
			logger.severe("Cannot find context, throwing exception"+e.getMessage());
			e.printStackTrace();
			throw new ServiceLocatorException();
		}
	}
	
	public static Session getSession() throws ServiceLocatorException{
		if(factory==null)
			factory = new MailSessionFactory();
		
		return factory.session;
	}
	
}
