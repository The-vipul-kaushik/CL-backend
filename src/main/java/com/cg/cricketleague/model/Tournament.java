package com.cg.cricketleague.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
* <h1>model class for Tournament</h1>
* <p>
* <b>attributes: tournamentId, tournamentName, matches, organisers</b> 
*
* @author Vipul
* @version 1.0
* @since 07-04-2022
*/

@Entity
@Table(name = "tournament")
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tournament_id")
	private int tournamentId;
	
	@NotEmpty(message = "Please enter a valid tournament name !")
	@Column(name = "tournament_name")
	private String tournamentName;
	
	@ManyToOne
	private Organiser organiser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private List<Match> matches;
	
	public Tournament() {
		super();
	}

	public Tournament(int tournamentId, String tournamentName, Organiser organiser, List<Match> matches) {
		super();
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
		this.organiser = organiser;
		this.matches = matches;
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public Organiser getOrganiser() {
		return organiser;
	}

	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public int hashCode() {
		return Objects.hash(organiser, tournamentId, tournamentName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		return Objects.equals(organiser, other.organiser) && tournamentId == other.tournamentId
				&& Objects.equals(tournamentName, other.tournamentName);
	}

	@Override
	public String toString() {
		return "Tournament [tournamentId=" + tournamentId + ", tournamentName=" + tournamentName + ", organiser="
				+ organiser + ", matches=" + matches + "]";
	}


	
}
