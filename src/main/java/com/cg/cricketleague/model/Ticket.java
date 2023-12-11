package com.cg.cricketleague.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


/**
* <h1>model class for Ticket</h1>
* <p>
* <b>attributes: ticketId, ticketName, totalAmount, noOfSeats</b> 
*
* @author Priyanshu
* @version 1.0
* @since 07-04-2022
*/

@Entity
@Table(name = "tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private int ticketId;
	
	@NotBlank(message = "ticket Name is required")
	@Column(name = "ticket_name")
	private String ticketName;
	
	@Min(value = 100, message="Amount must be atleast Rs.100")
	@Column(name = "total_amount")
	private double totalAmount;
	
	@Min(value = 1, message="No. of Seats must be atleast 1")
	@Column(name = "no_of_seats")
	private int noOfSeats;
	
	
	public Ticket() {
		super();
	}

	public Ticket(int ticketId, String ticketName, double totalAmount, int noOfSeats) {
		super();
		this.ticketId = ticketId;
		this.ticketName = ticketName;
		this.totalAmount = totalAmount;
		this.noOfSeats = noOfSeats;
	
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", ticketName=" + ticketName + ", totalAmount=" + totalAmount
				+ ", noOfSeats=" + noOfSeats + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(noOfSeats, ticketId, ticketName, totalAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return noOfSeats == other.noOfSeats && ticketId == other.ticketId
				&& Objects.equals(ticketName, other.ticketName)
				&& Double.doubleToLongBits(totalAmount) == Double.doubleToLongBits(other.totalAmount);
	}
	
	
	
}
