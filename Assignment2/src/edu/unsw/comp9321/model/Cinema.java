package edu.unsw.comp9321.model;

import java.io.Serializable;

public class Cinema implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String location;
	private int seatingCapacity;
	private int ATM;
	private int widescreen;
	private int snackBar;
	private int restaurant;
	 
	public Cinema(){
		ATM = 0;
		widescreen = 0;
		snackBar = 0;
		restaurant = 0;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public int getATM() {
		return ATM;
	}
	public void setATM(int aTM) {
		ATM = aTM;
	}
	public int getWidescreen() {
		return widescreen;
	}
	public void setWidescreen(int widescreen) {
		this.widescreen = widescreen;
	}
	public int getSnackBar() {
		return snackBar;
	}
	public void setSnackBar(int snackBar) {
		this.snackBar = snackBar;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	 
	 
	 
}
