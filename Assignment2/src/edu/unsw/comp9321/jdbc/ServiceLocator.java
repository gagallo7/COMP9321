package edu.unsw.comp9321.jdbc;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import edu.unsw.comp9321.exception.ServiceLocatorException;

public abstract class ServiceLocator {
	static Logger logger = Logger.getLogger(ServiceLocator.class.getName());
	private InitialContext jndiContext;
	
	// get initialContext
	public ServiceLocator() throws ServiceLocatorException {
		try {
			jndiContext = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServiceLocatorException();
		}
	}
	
	// Lookup operation
	public Object lookup(String name) throws NamingException {
		Object o = jndiContext.lookup(name);
		return o;
	}
			
}
