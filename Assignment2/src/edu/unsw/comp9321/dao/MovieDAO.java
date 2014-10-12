package edu.unsw.comp9321.dao;

import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.CinemaSession;
import edu.unsw.comp9321.model.Movie;
import edu.unsw.comp9321.model.UserLogin;

import java.util.List;

public interface MovieDAO {
	public void addMovie(Movie movie);
	public Movie getMovie(long id);
	public List<Movie> getMovieList();
	public List<Movie> getNowShowingMovies();
	public List<Movie> getComingSoonMovies();
	public List<Movie> searchMovie(String title, String genre);
	
	public void addCinema(Cinema cinema);
	public Cinema getCinema(Long id);
	public List<Cinema> getCinemaList();
	
	public void addCinemaSession(CinemaSession cinemaSession);
	public List<CinemaSession> getMovieSessions(Long movieID);
	
	public void registerUser(UserLogin user);
	
	public void closeSession();
}
