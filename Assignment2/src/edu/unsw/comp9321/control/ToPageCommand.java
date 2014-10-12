package edu.unsw.comp9321.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ToPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "home.jsp";

		HttpSession session = request.getSession();

		if (session.getAttribute("UserRole") != null) {
			if (request.getParameter("page") != null) {
				nextPage = "WEB-INF/restricted/" + request.getParameter("page")
						+ ".jsp";
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/" + nextPage);
		rd.forward(request, response);

	}

}
