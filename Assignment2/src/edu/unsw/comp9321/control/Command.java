package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jboss.logging.Logger;

public interface Command {
	static Logger logger = Logger.getLogger(Command.class.getName());
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
