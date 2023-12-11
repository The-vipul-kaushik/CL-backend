package com.cg.cricketleague.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* <h1>model class for Organiser</h1>
* <p>
* <b>attributes: organiserId, organiserName, email, phone, payment, budget,	tournaments</b> 
*
* @author Vipul
* @version 1.0
* @since 07-04-2022
*/

@Entity
@Table(name = "organiser")
public class Organiser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organiser_id")
	private int organiserId;
	
	@NotEmpty(message = "Please enter a valid Organiser name !")
	@Column(name = "organiser_name")
	private String organiserName;

	@NotEmpty
	@Email
	@Column(name = "email")
	private String email;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})", message = "Please enter a valid Phone number !")
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "payment")
	private double payment;
	
	@Column(name = "budget")
	private double budget;
	
	@JsonIgnore
	@OneToMany(mappedBy = "organiser")
	private List<Tournament> tournaments;
	
		
	public Organiser() {
		super();
	}


	public Organiser(int organiserId, String organiserName, String email, String phone, double payment, double budget,
			List<Tournament> tournaments) {
		super();
		this.organiserId = organiserId;
		this.organiserName = organiserName;
		this.email = email;
		this.phone = phone;
		this.payment = payment;
		this.budget = budget;
		this.tournaments = tournaments;
	}


	public int getOrganiserId() {
		return organiserId;
	}


	public void setOrganiserId(int organiserId) {
		this.organiserId = organiserId;
	}


	public String getOrganiserName() {
		return organiserName;
	}


	public void setOrganiserName(String organiserName) {
		this.organiserName = organiserName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public double getPayment() {
		return payment;
	}


	public void setPayment(double payment) {
		this.payment = payment;
	}


	public double getBudget() {
		return budget;
	}


	public void setBudget(double budget) {
		this.budget = budget;
	}


	public List<Tournament> getTournaments() {
		return tournaments;
	}


	public void setTournaments(List<Tournament> tournaments) {
		this.tournaments = tournaments;
	}


	@Override
	public int hashCode() {
		return Objects.hash(budget, email, organiserId, organiserName, payment, phone, tournaments);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organiser other = (Organiser) obj;
		return Double.doubleToLongBits(budget) == Double.doubleToLongBits(other.budget)
				&& Objects.equals(email, other.email) && organiserId == other.organiserId
				&& Objects.equals(organiserName, other.organiserName)
				&& Double.doubleToLongBits(payment) == Double.doubleToLongBits(other.payment)
				&& Objects.equals(phone, other.phone) && Objects.equals(tournaments, other.tournaments);
	}


	
	@Override
	public String toString() {
		return "Organiser [organiserId=" + organiserId + ", organiserName=" + organiserName + ", email=" + email
				+ ", phone=" + phone + ", payment=" + payment + ", budget=" + budget + ", tournaments=" + tournaments
				+ "]";
	}




	
}
