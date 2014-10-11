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
@Entity
public class MoviePersonality {
	@Id
	private long movieId;
	@Id
	private long personalityId;
	
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public long getPersonalityId() {
		return personalityId;
	}
	public void setPersonalityId(long personalityId) {
		this.personalityId = personalityId;
	}
}
