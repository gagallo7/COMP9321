package edu.unsw.comp9321.dao;

import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.Movie;

import java.util.List;

public interface MovieDAO {
	public void addMovie(Movie movie);
	public List<Movie> getMovieList();
	public List<Movie> getNowShowingMovies();
	public List<Movie> getComingSoonMovies();
	
	public void createCinema(Cinema cinema);
	public List<Cinema> getCinemaList();
}
