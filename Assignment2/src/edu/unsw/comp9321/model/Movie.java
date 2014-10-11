package edu.unsw.comp9321.model;

import java.io.Serializable;

public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Movie(){
		
	}
	
	public Movie(long id, String title, int ageRating, int rating) {
		super();
		this.id = id;
		this.title = title;
		this.ageRating = ageRating;
		this.rating = rating;
	}
	
	private long id;
	private String title;
	private int ageRating;
	private int rating;
	 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAgeRating() {
		return ageRating;
	}
	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	//Ctrl+3 generate
}
