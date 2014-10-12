package edu.unsw.comp9321.model;

import java.text.SimpleDateFormat;

public class Session {
	private long id;

	private long movieId;
	private long cinemaId;

	private int availableSeats;
	private SimpleDateFormat showTime = new SimpleDateFormat(
			"E yyyy.MM.dd 'at' hh:mm");
	
}
