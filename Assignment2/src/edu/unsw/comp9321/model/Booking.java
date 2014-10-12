package edu.unsw.comp9321.model;

import java.io.Serializable;

/*
 * one User to many bookings
 */

public class Booking implements Serializable{
	private long id;
	private boolean confirmed;
	private int numberOfTickets;
}
