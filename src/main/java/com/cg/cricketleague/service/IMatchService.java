package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;

public interface IMatchService {

	public abstract Match getMat(int matchId);
	
	public abstract Match getMatchByName(String matchName);
	
	public abstract List<Match> findAllMat();

	public abstract Match insertMat(Match match);

	public abstract Match updateMat(Match match);
	
	public abstract Match deleteMat(int matchId);

	public abstract Tournament getTournamentByMatch(int matchId);

	public abstract List<Audience> getAllAudience();

	public abstract List<Audience> getAudiencesByMatch(int matchId);

}
