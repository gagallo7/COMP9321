/**
 * 
 */
package edu.unsw.comp9321.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author guilherme
 *
 * many-to-many association for recommendations system
 * that's why it isn't an association many-to-one (many persons for one movie)
 */
public class UserBooking {
	private long userId;
	private long bookingId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	
	
}
