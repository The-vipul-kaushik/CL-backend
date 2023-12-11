package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Team;

public interface ITeamService {

	public abstract Team getTeam(int teamId);
	
	public abstract Team getTeamByName(String teamName);

	public abstract List<Team> getAllTeams();

	public abstract Team insertTeam(Team team);

	public abstract Team updateTeam(Team team);

	public abstract Team deleteTeam(int teamId);

	public abstract List<Player> getAllPlayers();

	public abstract List<Player> getPlayersByTeam(int teamId);

}
