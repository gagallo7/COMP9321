package edu.unsw.comp9321.control;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import edu.unsw.comp9321.model.UserLogin;


public class RegisterUserCommand implements Command {

	private void handleMail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//MailSender sender = null;
		String target="";
		try {
			//sender = MailSender.getMailSender();
			String toAddress = request.getParameter("email");
			
			logger.info ( "user email: " + toAddress );
			String subject = "Welcome to Popcorn Troll";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("testing " + request.getParameter("body"));
			//sender.sendMessage(toAddress, subject, mailBody);
			target="restricted/home.jsp";
		} catch (Exception e) {
			logger.warn("Oopsies, could not send message "+e.getMessage());
			e.printStackTrace();
			target="restricted/fail.jsp";
		}
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
		
		logger.info( "Registering a user...");
		
		handleMail(request, response);
		
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
