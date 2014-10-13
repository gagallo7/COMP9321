package edu.unsw.comp9321.util;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import edu.unsw.comp9321.exception.MailSenderException;
import edu.unsw.comp9321.exception.ServiceLocatorException;

public class MailSender {

	static Logger logger = Logger.getLogger(MailSender.class.getName());
	static MailSender sender;

	// Get Session class from the tomcat lib folder
	private static Session session;
	private Properties mailProps;
	final String username = "popcorn.troll@yahoo.com";
	final String password = "123Admin";

	private InitialContext ctx;

	/**
	 * This contructor is invoked according to singleton pattern. It looks up
	 * the mail session from the context, initialises it and then
	 * 
	 * @throws MailSenderException
	 * @throws ServiceLocatorException
	 */
	private MailSender() throws MailSenderException, ServiceLocatorException {
		try {
			ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			session = (Session) envContext.lookup("mail/cs9321");
			logger.info("Mailer Session obtained" + session.toString());
			mailProps = session.getProperties();
			logger.info("User " + username);
			logger.info("Pass " + password);
			
			logger.info("auth " + (String) mailProps.get("mail.smtp.auth"));
			logger.info("ttls " + (String) mailProps.get("mail.smtp.starttls.enable"));
			logger.info("host " + (String) mailProps.get("mail.smtp.host"));
			logger.info("port " + (String) mailProps.get("mail.smtp.port"));
			System.out.println("Getting instance");
			session = Session.getInstance(mailProps,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
			
		} catch (NamingException e) {
			logger.severe("Cannot find context, throwing exception"
					+ e.getMessage());
			e.printStackTrace();
			throw new ServiceLocatorException();
		}
	}

	/**
	 * This function sends a mail from fromAddress to toAddress. The actual
	 * authentication is performed here, so there could be an authentication
	 * error.
	 * 
	 * In contrast, setAuthData always returns true as no authentication is
	 * performed in that function
	 * 
	 * This is a blocking call, so may be better executed in a separate thread.
	 * 
	 * @param fromAddress
	 * @param toAddress
	 * @param mailSubject
	 * @param text
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void sendMessage(String toAddress,
			String mailSubject, StringBuffer text) throws AddressException,
			MessagingException {
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
		message.setSubject(mailSubject);
		message.setText(text.toString());

		Transport.send(message);

		logger.info("Sent message " + toAddress + " with subject "
				+ mailSubject);
	}

	public static MailSender getMailSender() throws ServiceLocatorException,
			MailSenderException {
		if (sender == null)
			sender = new MailSender();
		return sender;
	}

}