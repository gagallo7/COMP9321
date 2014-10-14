package edu.unsw.comp9321.model;

import java.io.Serializable;

/*
 * one User to many bookings
 */

public class Booking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String username;
	private int numberOfTickets;
	private CinemaSession cinemaSession;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getNumberOfTickets() {
		return numberOfTickets;
	}
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	public CinemaSession getCinemaSession() {
		return cinemaSession;
	}
	public void setCinemaSession(CinemaSession cinemaSession) {
		this.cinemaSession = cinemaSession;
	}
	
}