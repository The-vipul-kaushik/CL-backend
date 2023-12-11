package com.cg.cricketleague.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/**
 * <h1>model class for Match</h1>
 * <p>
 * <b>attributes: </b> matchId matchName
 *
 * @author Avinash
 * @version 1.0
 * @since 07-04-2022
 */

@Entity
@Table(name = "matches")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_id")
	private int matchId;

	@NotBlank(message = "Match name is mandatory")
	@Column(name = "match_name")
	private String matchName;

	@NotBlank(message = "Match venue is mandatory")
	@Column(name = "match_venue")
	private String matchVenue;

	@Column(name = "match_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date matchDate;

	@ManyToOne
	private Tournament tournament;

	@JsonIgnore
	@OneToMany(mappedBy = "match")
	private List<Audience> audiences;

	public Match() {

	}

	public Match(int matchId, String matchName, String matchVenue, Date matchSchedule, Tournament tournament,
			List<Audience> audiences) {
		super();
		this.matchId = matchId;
		this.matchName = matchName;
		this.matchVenue = matchVenue;
		this.matchDate = matchSchedule;
		this.tournament = tournament;
		this.audiences = audiences;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getMatchVenue() {
		return matchVenue;
	}

	public void setMatchVenue(String matchVenue) {
		this.matchVenue = matchVenue;
	}

	public Date getMatchSchedule() {
		return matchDate;
	}

	public void setMatchSchedule(Date matchSchedule) {
		this.matchDate = matchSchedule;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public List<Audience> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<Audience> audiences) {
		this.audiences = audiences;
	}

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", matchName=" + matchName + ", matchVenue=" + matchVenue
				+ ", matchSchedule=" + matchDate + ", tournament=" + tournament + ", audiences=" + audiences + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(audiences, matchId, matchName, matchDate, matchVenue, tournament);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return matchId == other.matchId || matchName == other.getMatchName();
	}

}
