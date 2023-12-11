package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Ticket;

public interface IAudienceService {
 
	public abstract Audience getAudience(int audienceId);
	public abstract Audience insertAudience(Audience audience);
	public abstract Match getMatch(int audienceId);
	public abstract Audience updateAudience(Audience audience);
	public abstract List<Audience> getAllAudiences();
	public abstract Ticket getTicket(int ticketId);
	public abstract double getPaidAmountForAllTickets();
}
