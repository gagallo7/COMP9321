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
	private String actors;
	private int ageRating;
	private int rating;
	private int ratingSum;
	private int ratingNum;
	private Date releaseDate;
	private int nowShowing; //without mapping cause it changes day by day
	
	public Movie(){
		rating = 0;
		ratingSum = 0;
		ratingNum = 0;
	}
	
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
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
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
	public int getRatingSum() {
		return ratingSum;
	}
	public void setRatingSum(int ratingSum) {
		this.ratingSum = ratingSum;
	}
	public int getRatingNum() {
		return ratingNum;
	}
	public void setRatingNum(int ratingNum) {
		this.ratingNum = ratingNum;
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

