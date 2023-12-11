package com.cg.cricketleague.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
* <h1>model class for Audience</h1>
* <p>
* <b>attributes: audienceId, audienceName, ticket, match</b> 
*
* @author Priyanshu
* @version 1.0
* @since 07-04-2022
*/

@Entity
@Table(name = "audiences")
public class Audience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audience_id")
	private int audienceId;
	
	@NotBlank(message = "Audience Name is required")
	@Column(name = "audience_name")
	private String audienceName;
	
	@ManyToOne
	private Match match;


	@OneToOne
	private Ticket tickets;
	
	
	public Audience() {
		super();
	}
	
	public Audience(int audienceId) {
		this.audienceId=audienceId;
	}
	public Audience(int audienceId, String audienceName,  
			Match matches, Ticket tickets) {
		super();
		this.audienceId = audienceId;
		this.audienceName = audienceName;
		this.match = matches;
		this.tickets = tickets;
	}
	public int getAudienceId() {
		return audienceId;
	}
	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}
	public String getAudienceName() {
		return audienceName;
	}
	public void setAudienceName(String audienceName) {
		this.audienceName = audienceName;
	}

	public Match getMatches() {
		return match;
	}
	public void setMatches(Match matches) {
		this.match = matches;
	}
	public Ticket getTickets() {
		return tickets;
	}
	public void setTickets(Ticket tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Audience [audienceId=" + audienceId + ", audienceName=" + audienceName + ", match=" + match
				+ ", tickets=" + tickets + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(audienceId, audienceName, tickets);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Audience other = (Audience) obj;
		return  audienceId == other.audienceId;
	}
	
	
}