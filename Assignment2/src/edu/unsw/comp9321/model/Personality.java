package edu.unsw.comp9321.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Personality {
	@Id
	@GeneratedValue
	private long personality_id;
	private String name;
	public long getPersonality_id() {
		return personality_id;
	}
	public void setPersonality_id(long personality_id) {
		this.personality_id = personality_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
