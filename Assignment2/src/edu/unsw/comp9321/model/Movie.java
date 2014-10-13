package edu.unsw.comp9321.model;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String title;
	private String urlPost;
	private String genre;
	private String Director;
	private String synopsis;
	private int ageRating;
	private int rating;
	private Date releaseDate;
	private int nowShowing; //without mapping cause it changes day by day
	
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
	public String getUrlPost() {
		return urlPost;
	}
	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getNowShowing() {
		return nowShowing;
	}
	public void setNowShowing(int nowShowing) {
		this.nowShowing = nowShowing;
	}
	

	
	//Ctrl+3 generate
}
