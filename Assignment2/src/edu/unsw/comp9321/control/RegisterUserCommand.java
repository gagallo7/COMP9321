package edu.unsw.comp9321.control;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.UserLogin;

public class RegisterUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		UserLogin user = new UserLogin();
		
		user.setUsername(safeString(request.getParameter("username")));
		user.setPassword(safeString(request.getParameter("password")));
		user.setEmail(safeString(request.getParameter("email")));
		user.setConfirmed(0);
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.registerUser(user);
			dao.closeSession();
			/*
		      // Recipient's email ID needs to be mentioned.
		      String to = "abcd@gmail.com";
		 
		      // Sender's email ID needs to be mentioned
		      String from = "web@gmail.com";
		 
		      // Assuming you are sending email from localhost
		      String host = "localhost";
		 
		      // Get system properties
		      Properties properties = System.getProperties();
		 
		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);
		 
		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);
		      
			  // Set response content type
		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();

		      try{
		         // Create a default MimeMessage object.
		         MIMEMessage message = new MIMEMessage(session);
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));
		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
		         // Set Subject: header field
		         message.setSubject("This is the Subject Line!");

		         // Send the actual HTML message, as big as you like
		         message.setContent("<h1>This is actual message</h1>",
		                            "text/html" );
		         // Send message
		         Transport.send(message);
		         String title = "Send Email";
		         String res = "Sent message successfully....";
		         */
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String safeString(String string){
		return StringEscapeUtils.unescapeHtml4(string);
	}
	

}
