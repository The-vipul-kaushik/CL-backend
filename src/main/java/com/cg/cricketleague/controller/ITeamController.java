package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Team;

public interface ITeamController {

	public abstract ResponseEntity<Team> getTeamById(int teamId);
	
	public abstract ResponseEntity<Team> getTeamByName(String teamName);

	public abstract ResponseEntity<List<Team>> getAllTeams();

	public abstract ResponseEntity<Team> addTeam(Team team);

	public abstract ResponseEntity<Team> updateTeam(Team team);

	public abstract ResponseEntity<Team> deleteTeam(int teamId);
	
	public abstract ResponseEntity<List<Player>> getAllPlayers();
	
	public abstract ResponseEntity<List<Player>> getPlayersByTeam(int teamId);
	
}
