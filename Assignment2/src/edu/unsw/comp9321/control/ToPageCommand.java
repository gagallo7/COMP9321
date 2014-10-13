package edu.unsw.comp9321.control;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ToPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "home.jsp";
		Set <String> userOnlyPages = new HashSet < String > ();
		Set <String> managerOnlyPages = new HashSet < String > ();
		userOnlyPages.add ("booking");
		managerOnlyPages.add("cinemas");
		managerOnlyPages.addAll(userOnlyPages);
		

		HttpSession session = request.getSession();

		if (session.getAttribute("UserRole") == "manager") {
			if ( managerOnlyPages.contains(request.getParameter("page")) ) {
				nextPage = "WEB-INF/restricted/" + request.getParameter("page")
						+ ".jsp";
			}
		}
		else if (session.getAttribute("UserRole") == "user") {
			if ( userOnlyPages.contains(request.getParameter("page")) ) {
				nextPage = "WEB-INF/restricted/" + request.getParameter("page")
						+ ".jsp";
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/" + nextPage);
		rd.forward(request, response);

	}

}
