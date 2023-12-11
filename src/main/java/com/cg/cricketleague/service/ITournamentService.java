package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;

public interface ITournamentService {

	public abstract Tournament getTourById(int tournamentId);
	
	public abstract List<Tournament> getAllTours();
	
	public abstract Tournament insertTour(Tournament tournament);
	
	public abstract Tournament updateTour(Tournament tournament);
	
	public abstract List<Match> getAllMats(int tournamentId);
	
	public abstract Match getMatchByTourIdAndMatchId(int tournamentId, int matchId);
	
	public abstract Tournament getTourByMatchId(int matchId);
	
	
}
