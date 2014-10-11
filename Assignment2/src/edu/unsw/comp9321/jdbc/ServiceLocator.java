package edu.unsw.comp9321.jdbc;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.unsw.comp9321.exception.ServiceLocatorException;

public abstract class ServiceLocator {
	private InitialContext jndiContext;
	
	// connection details to JNDI
	//private String initialContextFactory = "org.jnp.interfaces.NamingContextFactory";
	//private String urlPkgPrefix= "org.jboss.naming:org.jnp.interfaces";
	//private String url = "jnp://localhost:1099";
	
	// get initialContext
	public ServiceLocator() throws ServiceLocatorException, NamingException {
		jndiContext = new InitialContext();//(initialContextFactory, urlPgkprefix, url);
	}
	
	// Lookup operation
	public Object lookup(String name) throws NamingException {
		Object o = jndiContext.lookup(name);
		return o;
	}
			
}
