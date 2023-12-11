package com.cg.cricketleague.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "owners")
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "owner_id")
	private int ownerId;
	
	@NotBlank(message = "Owner Name is required")
	@Column(name = "owner_name")
	private String ownerName;
	
	@Column(name = "budget")
	@Min(value = 100000000, message="Budget must be atleast Rs.10,00,00,000")
	private double budget;
	
	@OneToOne
	private Team team;

	public Owner() {
		super();
	}
	
	public Owner(int ownerId, String ownerName, double budget, Team team) {
		super();
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.budget = budget;
		this.team = team;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(budget, ownerId, ownerName, team);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Owner other = (Owner) obj;
		return Double.doubleToLongBits(budget) == Double.doubleToLongBits(other.budget) && ownerId == other.ownerId
				&& Objects.equals(ownerName, other.ownerName) && Objects.equals(team, other.team);
	}
	
	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", ownerName=" + ownerName + ", budget=" + budget + ", team=" + team + "]";
	}
	
}