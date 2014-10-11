package edu.unsw.comp9321.dao;

import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.Movie;

import java.util.List;

public interface MovieDAO {
	public List<Movie> getMovieList();
	
	public void createCinema(Cinema cinema);
}
