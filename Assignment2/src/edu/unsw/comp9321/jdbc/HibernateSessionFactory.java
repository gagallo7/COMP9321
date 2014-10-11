package edu.unsw.comp9321.jdbc;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.ObjectFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.unsw.comp9321.exception.ServiceLocatorException;

public class HibernateSessionFactory extends ServiceLocator implements ObjectFactory {
	static Logger logger = Logger.getLogger(HibernateSessionFactory.class.getName());
	
	private static HibernateSessionFactory factory = null; //Singleton
	private SessionFactory sf = null;
	
	public HibernateSessionFactory() throws ServiceLocatorException{
		super();
	}
	
	public SessionFactory getSessionFactory() throws ServiceLocatorException{
		if (sf== null){
			try {
				sf = (SessionFactory) lookup("java:comp/env/hibernate/cs9321");
				logger.info("Session Factory found:"+sf.toString());	
			} catch (NamingException e) {
				logger.severe("Cannot get Session Factory " + e.getStackTrace());
				throw new ServiceLocatorException();
			}	
		}
		return sf;
	}
	
	public static Session getSession() throws ServiceLocatorException {
		if(factory==null)
			factory = new HibernateSessionFactory();
		Session session = factory.getSessionFactory().openSession();
	
		return session;
	}

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable environment) throws NamingException {
	      SessionFactory sessionFactory = null;  
	        
	      try{  
	    	  sessionFactory = (new Configuration()).configure().buildSessionFactory();  
	      }catch(Exception ex){  
	         throw new javax.naming.NamingException(ex.getMessage());  
	      }  
	  
	      return sessionFactory; 
	} 
}
