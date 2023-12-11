package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;

public interface ITournamentController {

	public abstract ResponseEntity<Tournament> getTournament(int tournamentId);
	
	public abstract ResponseEntity<List<Tournament>> getAllTournaments();
	
	public abstract ResponseEntity<Tournament> insertTournament(Tournament tournament);
	
	public abstract ResponseEntity<Tournament> updateTournament(Tournament tournament);
	
	public abstract ResponseEntity<List<Match>> getAllMatches(int tournamentId);
	
	public abstract ResponseEntity<Match> getMatchByTournamentIdAndMatchId(int tournamentId, int matchId);
	
	public abstract ResponseEntity<Tournament> getTournamentByMatch(Match match);
}
