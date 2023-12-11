package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Skill;
import com.cg.cricketleague.model.Team;

public interface IPlayerService {
	
	Player getPlayer(int playerId);
	
	List<Player> getAllPlayers();
	
	Player insertPlayer(Player player);
	
	Player updatePlayer(Player player);
	
	Player deletePlayer(int playerId);
	
	Skill getSkill(int playerId);
	
	Team getTeam(int playerId);
	
	double getSalary(int playerId);
}
