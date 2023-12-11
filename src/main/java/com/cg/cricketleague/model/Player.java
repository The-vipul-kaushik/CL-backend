package com.cg.cricketleague.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
	private int playerId;
	
	@NotBlank(message = "Player Name is required")
	@Column(name = "player_name")
	private String playerName;
	
	@Column(name = "salary")
	@Min(value = 1000000, message="Salary must be atleast Rs.10,00,000")
	private double salary;
	
	@Column(name = "skill")
	private Skill skill;
	
	@ManyToOne
	private Team team;
	
	public Player() {
		super();
	}
	
	public Player(int playerId, String playerName, double salary, Skill skill, Team team) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.salary = salary;
		this.skill = skill;
		this.team = team;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerId, playerName, salary, skill, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return playerId == other.playerId && Objects.equals(playerName, other.playerName)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary) && skill == other.skill
				&& Objects.equals(team, other.team);
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", salary=" + salary + ", skill=" + skill
				+ ", team=" + team + "]";
	}
	
}