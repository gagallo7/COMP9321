package edu.unsw.comp9321.jdbc;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.unsw.comp9321.exception.ServiceLocatorException;

public abstract class ServiceLocator {
	private InitialContext jndiContext;
	
	// get initialContext
	public ServiceLocator() throws ServiceLocatorException, NamingException {
		jndiContext = new InitialContext();
	}
	
	// Lookup operation
	public Object lookup(String name) throws NamingException {
		Object o = jndiContext.lookup(name);
		return o;
	}
			
}
