package edu.unsw.comp9321.control;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet({ "/Controller", "/control" })
@MultipartConfig// expects requests to made using the multipart/form-data - read file-image
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(Controller.class.getName());
    
    private HashMap<Actions, Command> commands;
    private enum Actions {logout, toHomePage, toPage, detailProfile, login, registerUser, confirmUser, editProfile, addCinema, addCinemaSession, addMovie, addShowtime,  searchMovie, detailMovie, reviewMovie, bookSession, checkoutBooking, detailBookings};
    
    public Controller() {
        super();   
    }
    
    public void init(ServletConfig config) throws ServletException {
    	logger.info("Control Servlet init");
    	commands = new HashMap<Actions, Command>();
    	commands.put(Actions.toHomePage, 	new ToHomePageCommand());
    	commands.put(Actions.toPage, 		new ToPageCommand());
    	commands.put(Actions.detailProfile, new DetailProfileCommand());
    	commands.put(Actions.login, 		new LoginCommand());
    	commands.put(Actions.registerUser, 	new RegisterUserCommand());
    	commands.put(Actions.confirmUser, 	new ConfirmUserCommand());
    	commands.put(Actions.editProfile,   new EditProfileCommand());
    	commands.put(Actions.addCinema, 	new AddCinemaCommand());
    	commands.put(Actions.addCinemaSession, new AddCinemaSessionCommand());
    	commands.put(Actions.addMovie, 		new AddMovieCommand());
    	commands.put(Actions.addShowtime, 	new ToPageCommand());
    	commands.put(Actions.searchMovie, 	new SearchMovieCommand());
    	commands.put(Actions.detailMovie, 	new DetailMovieCommand());
    	commands.put(Actions.reviewMovie, 	new ReviewMovieCommand());
    	commands.put(Actions.bookSession, 	new BookSessionCommand());
    	commands.put(Actions.detailBookings,new DetailBookingsCommand());
    	commands.put(Actions.checkoutBooking, new CheckoutBookingCommand());
    	commands.put(Actions.logout, new LogoutCommand());
	}

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String actionParameter = request.getParameter("action"); 
    	Actions action;
		
    	if (actionParameter == null || actionParameter.isEmpty()){
    		action = Actions.toHomePage;
		}else
    		action = Enum.valueOf(Actions.class, actionParameter);
    	
    	if (action == null){
    		response.sendError(404);
    	}else{
	    	Command command = commands.get(action);
		    command.execute(request, response);	
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

}