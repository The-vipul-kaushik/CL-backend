package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.Ticket;

public interface ITicketController {
	
	public abstract ResponseEntity<Ticket> getTicketById(int ticketId);
	
	public abstract ResponseEntity<Ticket> insertTicket(Ticket ticket);
	
	public abstract ResponseEntity<Ticket> updateTicket(Ticket ticket);
	
	public abstract ResponseEntity<Ticket> cancelTicketById(int ticketId);
	
	public abstract ResponseEntity<List<Ticket>> getAllTickets();
	//public abstract Match getMatch();
	//public abstract Audience getSchedule(int ticketId);
	//public abstract double getBill();
	
	public abstract ResponseEntity<Double> calculateBill(int ticketId);
}
