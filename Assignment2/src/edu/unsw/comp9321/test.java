package edu.unsw.comp9321;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		// ServletOutputStream out = response.getOutputStream();
		// out.println("<html><body><h1>No data found! PLEASE CHECK THE XML FILE!</h1></body></html>");

		String nextPage = "home.jsp";

		ServletContext context = request.getServletContext();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		ServletContext context = request.getServletContext();

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
		doGet(request, response);
	}

}
