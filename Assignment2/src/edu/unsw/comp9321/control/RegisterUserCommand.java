package edu.unsw.comp9321.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.model.UserLogin;
import edu.unsw.comp9321.util.MailSender;

public class RegisterUserCommand implements Command {

	private void handleMail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MailSender sender = null;
		RequestDispatcher disp;
		String target="";
		try {
			sender = MailSender.getMailSender();
//			String fromAddress = request.getParameter("from");
			String toAddress = request.getParameter("email");
			
			logger.info ( "user email: " + toAddress );
			String subject = "Welcome to Popcorn Troll";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("testing " + request.getParameter("body"));
			sender.sendMessage(toAddress, subject, mailBody);
			target="home.jsp";
		} catch (Exception e) {
			logger.warn("Oopsies, could not send message "+e.getMessage());
			e.printStackTrace();
			target="fail.jsp";
		}
		/* Standard RequestDispatcher **
		disp = request.getRequestDispatcher(target);
		disp.forward(request, response);
		*/
		/* Post-Redirect-Get implementation */
//		URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(),
//				request.getContextPath());
//		logger.info(url.toExternalForm());
//		response.sendRedirect(url.toExternalForm()+"/"+target);
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		logger.info( "Registering a user...");
		
		handleMail(request, response);
		
	/*	final String username = "popcorn.troll@yahoo.com";
		final String password = "123Admin";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.port", "587");
 
		System.out.println("Getting instance");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		System.out.println("Messagging...");
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("popcorn.troll@yahoo.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("gagallo7@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}*/
		
		UserLogin user = new UserLogin();

		user.setUsername(safeString(request.getParameter("username")));
		user.setPassword(safeString(request.getParameter("password")));
		user.setEmail(safeString(request.getParameter("email")));
		user.setConfirmed(0);

	}

	public String safeString(String string) {
		return StringEscapeUtils.unescapeHtml4(string);
	}

}
