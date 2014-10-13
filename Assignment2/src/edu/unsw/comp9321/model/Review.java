package edu.unsw.comp9321.model;

import java.io.Serializable;

public class Review implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private long movieId;
	private String text;
	private int rating;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
