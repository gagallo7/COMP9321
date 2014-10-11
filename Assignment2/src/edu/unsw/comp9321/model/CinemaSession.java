package edu.unsw.comp9321.model;

/*
 * One cinema to many sessions
 */
public class CinemaSession {
	private long cinemaId;
	private long sessionId;
	
	public long getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(long cinemaId) {
		this.cinemaId = cinemaId;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
}
