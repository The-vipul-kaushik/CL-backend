package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;

public interface IMatchController {
	public abstract ResponseEntity<Match> getMatchById(int matchId);
	
	public abstract ResponseEntity<Match> getMatchByName(String matchName);

	public abstract ResponseEntity<List<Match>> getAllMatches();

	public abstract ResponseEntity<Match> addMatch(Match match);

	public abstract ResponseEntity<Match> updateMatch(Match match);

	public abstract ResponseEntity<Match> deleteMatch(int matchId);
	
	public abstract ResponseEntity<Tournament> getTournamentByMatchId(int matchId);
	
	public abstract ResponseEntity<List<Audience>> getAllAudience();
	
	public abstract ResponseEntity<List<Audience>> getAudiencesByMatchId(int match);
	

}
