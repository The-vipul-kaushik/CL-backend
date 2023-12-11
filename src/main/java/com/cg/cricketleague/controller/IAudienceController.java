package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Ticket;


public interface IAudienceController {
	
	public abstract ResponseEntity<Audience> getAudienceById(int audienceId);
	//public abstract ResponseEntity<Audience> deleteAudienceById(int audienceId);

	public abstract ResponseEntity<List<Audience>> getAllAudiences();

	public abstract ResponseEntity<Audience> insertAudience(Audience audience);
	public abstract ResponseEntity<Audience> updateAudience(Audience audience);
	
	public abstract ResponseEntity<Match> getMatchByAudienceId(int audienceId);
	
	public abstract ResponseEntity<Ticket> getTicketByAudienceId(int audienceId);
	
	public abstract ResponseEntity<Double> getPaidAmountForAllTickets();
	

}
