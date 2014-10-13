package edu.unsw.comp9321.control;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.UserLogin;
import edu.unsw.comp9321.util.MailSenderUtil;
import edu.unsw.comp9321.util.OwaspHashUtil;
import edu.unsw.comp9321.util.PostRedirectGetUtil;

public class RegisterUserCommand implements Command {

	private String newUser;
	private String newPass;
	private String target;
	private String toAddress;
	private String code;

	private String createCode(String login, String password) {
		try {
			if (login != null && password != null && login.length() <= 100) {
				// Uses a secure Random not a simple Random
				SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
				// Salt generation 64 bits long
				byte[] bSalt = new byte[8];
				random.nextBytes(bSalt);
				// Digest computation
				byte[] bDigest = OwaspHashUtil.getHash(
						OwaspHashUtil.ITERATION_NUMBER, password, bSalt);
				newPass = OwaspHashUtil.byteToBase64(bDigest);
				String sSalt = OwaspHashUtil.byteToBase64(bSalt);

				return sSalt;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean handleMail(HttpServletRequest request)
			throws ServletException, IOException {
		MailSenderUtil sender = null;
		String toAddress = request.getParameter("email");
		logger.info("user email: " + toAddress);

		try {
			sender = MailSenderUtil.getMailSender();

			String subject = "Welcome to Popcorn Troll";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("Your activation code is " + code);
			sender.sendMessage(toAddress, subject, mailBody);
			target = "success.jsp";

			return true;
		} catch (Exception e) {
			logger.warn("Oopsies, could not send message " + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());

		request.getSession().setAttribute("info", "Registration");
		target = "fail.jsp";
		logger.info("Registering a user...");

		newUser = safeString(request.getParameter("username"));

		try {
			MovieDAO dao = new HibernateMovieDAO();
			if (dao.usernameExists(newUser)) {
				request.getSession().setAttribute(
						"moreInfo",
						"Be more creative. Username "
								+ newUser + " already exists.");

				logger.info("Duplicate username " + newUser);
				dao.closeSession();
				PostRedirectGetUtil.postRedirectGet(request, response, target);
				return;
			}
		} catch (ServiceLocatorException e) {
			throw new ServletException();
		}

		toAddress = safeString(request.getParameter("email"));
		code = createCode(newUser, safeString(request.getParameter("password")));

		UserLogin user = new UserLogin();

		user.setUsername(newUser);
		user.setPassword(newPass);
		user.setEmail(toAddress);
		user.setConfirmed(0); // To be confirmed
		user.setCode(code);

		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.addUserLogin(user);
			dao.closeSession();

			if (handleMail(request)) {
				request.getSession().setAttribute(
						"moreInfo",
						"Verify your e-mail to confirm your registration. ("
								+ toAddress + ")");

				target = "success.jsp";
			}

		} catch (ServiceLocatorException e) {
			throw new ServletException();
		}
		PostRedirectGetUtil.postRedirectGet(request, response, target);
	}

	public String safeString(String string) {
		return StringEscapeUtils.unescapeHtml4(string);
	}

}
