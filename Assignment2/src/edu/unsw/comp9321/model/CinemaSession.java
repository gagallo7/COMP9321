package edu.unsw.comp9321.model;

import java.io.Serializable;
import java.util.Date;

public class CinemaSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long movieId;
	//private long cinemaId;
	private int availableSeats;
	private Date showTime;
	private Cinema cinema; //join
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
}
