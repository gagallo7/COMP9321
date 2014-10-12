package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		logger.info("Username = " + username);
		if (username != null) {
			HttpSession session = request.getSession();
			if (username.equals("admin")) {
				session.setAttribute("UserRole", "manager");
			} else {
				session.setAttribute("UserRole", "user");
			}
			session.setAttribute("username", username);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("control?action=toHomePage");
		rd.forward(request, response);

	}

}
