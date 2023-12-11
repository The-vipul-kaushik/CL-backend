package com.cg.cricketleague.service;


import java.util.List;


import com.cg.cricketleague.model.Ticket;

public interface ITicketService {
	public abstract Ticket getTicket(int ticketId);
	public abstract Ticket insertTicket(Ticket ticket);
	public abstract Ticket updateTicket(Ticket ticket); 
	public abstract Ticket cancelTicket(int ticketId);
	public abstract List<Ticket> getAllTickets();
	//public abstract Match getMatch();
	//public abstract Audience getSchedule(int ticketId);
	//public abstract double getBill();
	public abstract double calculateBill(int ticketId);
}
