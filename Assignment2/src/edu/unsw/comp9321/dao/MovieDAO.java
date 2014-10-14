package edu.unsw.comp9321.dao;

import edu.unsw.comp9321.model.Booking;
import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.CinemaSession;
import edu.unsw.comp9321.model.Movie;
import edu.unsw.comp9321.model.Review;
import edu.unsw.comp9321.model.UserLogin;

import java.util.List;

public interface MovieDAO {
	public void addMovie(Movie movie);
	public Movie getMovie(long id);
	public List<Movie> getMovieList();
	public List<Movie> getNowShowingMovies();
	public List<Movie> getComingSoonMovies();
	public List<Movie> searchMovie(String title, String genre);
	
	public void addReview(Review review);
	public List<Review> getReviewList(Long movieID);
	
	public void addCinema(Cinema cinema);
	public Cinema getCinema(Long id);
	public List<Cinema> getCinemaList();
	
	public void addCinemaSession(CinemaSession cinemaSession);
	public CinemaSession getCinemaSession(Long id);
	public List<CinemaSession> getMovieSessions(Long movieID);
	public boolean isSessionAvailable(Long cinemaSessionID, int numTickets);
	
	public void registerUser(UserLogin user);
	public void addUserLogin (UserLogin ul);
	public void updateUser (UserLogin user);
	public UserLogin confirmUser(String code);
	public UserLogin getUser(String username);
	public boolean usernameExists (String username);
	
	public List<Booking> getBookings(String username);
	
	public void closeSession();
}
