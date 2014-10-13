package edu.unsw.comp9321.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
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

public class RegisterUserCommand2 implements Command {

	private void handleMail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MailSender sender = null;
		RequestDispatcher disp;
		String target="";
		try{
			sender = MailSender.getMailSender();
			String fromAddress = request.getParameter("from");
			String toAddress = request.getParameter("to");
			String subject = request.getParameter("subject");
			StringBuffer mailBody = new StringBuffer();
			mailBody.append(request.getParameter("body"));
			sender.sendMessage(fromAddress, toAddress, subject, mailBody);
			target="success.jsp";
		}catch (Exception e){
			logger.warn("Oopsies, could not send message "+e.getMessage());
			e.printStackTrace();
			target="fail.jsp";
		}
		/* Standard RequestDispatcher **
		disp = request.getRequestDispatcher(target);
		disp.forward(request, response);
		*/
		/* Post-Redirect-Get implementation */
		URL url = new URL(request.getScheme(),request.getServerName(),request.getServerPort(),
				request.getContextPath());
		logger.info(url.toExternalForm());
		response.sendRedirect(url.toExternalForm()+"/"+target);
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		logger.info( "Registring a user...");
		
		UserLogin user = new UserLogin();

		user.setUsername(safeString(request.getParameter("username")));
		user.setPassword(safeString(request.getParameter("password")));
		user.setEmail(safeString(request.getParameter("email")));
		user.setConfirmed(0);

		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.registerUser(user);

			// Recipient's email ID needs to be mentioned.
			String to = safeString(request.getParameter("email"));

			// Sender's email ID needs to be mentioned
			String from = "z5030891@cse.unsw.edu.au";

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
			
			handleMail(request, response);

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
				// Set Subject: header field
				message.setSubject("This is the Subject Line!");

				// Send the actual HTML message, as big as you like
				message.setContent("<h1>This is actual message</h1>",
						"text/html");
				// Send message
				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public String safeString(String string) {
		return StringEscapeUtils.unescapeHtml4(string);
	}

}
