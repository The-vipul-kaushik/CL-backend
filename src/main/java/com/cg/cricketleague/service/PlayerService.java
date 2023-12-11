package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.MatchNotFoundException;
import com.cg.cricketleague.exception.OrganiserNotFoundException;
import com.cg.cricketleague.exception.PlayerNotFoundException;
import com.cg.cricketleague.exception.TeamNotFoundException;
import com.cg.cricketleague.exception.TournamentNotFoundException;
import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Skill;
import com.cg.cricketleague.model.Team;
import com.cg.cricketleague.repository.MatchRepository;
import com.cg.cricketleague.repository.OrganiserRepository;
import com.cg.cricketleague.repository.PlayerRepository;
import com.cg.cricketleague.repository.TeamRepository;
import com.cg.cricketleague.repository.TournamentRepository;

@Service
public class PlayerService implements IPlayerService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	OrganiserRepository organiserRepository;
	
	String doesNotExist = " does not exist.";
	String playerWithId = "Player with playerId ";

	/** 
     * This method takes a playerId as input from the user and returns the details of the player
     * @param playerId This is the player ID used to search for the player
     * @return Player This returns the player if found, else throws PlayerNotFoundException
     */
	@Override
	public Player getPlayer(int playerId) {
		log.info("PlayerService getPlayer {}", playerId);
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if (playerOptional.isPresent()) {
			return playerOptional.get();
		} else {
			String exceptionMessage = playerWithId + playerId + doesNotExist;
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		}
	}	

	
	/** 
     * This method returns all the players of the team in the tournament
     * @return List<Player> This returns the list of players if found, else throws PlayerNotFoundException
     */
	@Override
	public List<Player> getAllPlayers() {
		log.info("PlayerService getAllPlayers");
		List<Player> playerList = playerRepository.findAll();
		if (playerList.isEmpty()) {
			String exceptionMessage = "No Players Found";
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		} else {
			return playerList;
		}
	}

	
	/** 
     * This method is used to add players to the teams
     * @param player This is the player object  
     * @return Player This returns the player if added successfully, else throws TeamNotFoundException if 
     * team doesn't exist
     */
	@Override
	public Player insertPlayer(Player player) {
		log.info("PlayerService insertPlayer {}", player.getPlayerId());
		if(!teamRepository.findById(player.getTeam().getTeamId()).isPresent()){
			String exceptionMessage = "Team with teamId " + player.getTeam().getTeamId()
					+ doesNotExist;
			log.warn(exceptionMessage);
			throw new TeamNotFoundException(exceptionMessage);
		}else {
			return playerRepository.save(player);
		}
	}	

	
	/** 
     * This method is used to update details of a players
     * @param player This is the player object  
     * @return Player This returns the player if added successfully, else throws TeamNotFoundException if 
     * team doesn't exist, else throws PlayerNotFoundException if player doesn't exist
     */
	@Override
	public Player updatePlayer(Player player) {
		log.info("PlayerService updatePlayer {}", player.getPlayerId());
		Optional<Player> playerOptional = playerRepository.findById(player.getPlayerId());
		if (playerOptional.isPresent()) {
				return playerRepository.save(player);
			}
		else {
			String exceptionMessage = playerWithId + player.getPlayerId() + doesNotExist;
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method is used to remove an player from the team
     * @param playerId This is the player ID used to search for the player
     * @return Player This returns the player if found, else throws PlayerNotFoundException
     */
	@Override
	public Player deletePlayer(int playerId) {
		log.info("PlayerService deletePlayer {}", playerId);
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if (playerOptional.isPresent()) {
			Player player = playerOptional.get();
			playerRepository.delete(player);
			return player;
		} else {
			String exceptionMessage = playerWithId + playerId + doesNotExist;
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method is used to get the skill of a player
     * @param playerId This is the player ID used to search for the player
     * @return Skill This returns the skill if player is found, else throws PlayerNotFoundException
     */
	@Override
	public Skill getSkill(int playerId) {
		log.info("PlayerService getSkill {}", playerId);
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if (playerOptional.isPresent()) {
			return playerOptional.get().getSkill();
		} else {
			String exceptionMessage = playerWithId + playerId + doesNotExist;
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method is used to get the team a player belongs to
     * @param playerId This is the player ID used to search for the player
     * @return Team This returns the team if player is found, else throws PlayerNotFoundException
     */
	@Override
	public Team getTeam(int playerId) {
		log.info("PlayerService getTeam {}", playerId);
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if (playerOptional.isPresent()) {
			return playerOptional.get().getTeam();
		} else {
			String exceptionMessage = playerWithId + playerId + " not found";
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method is used to get the salary of a player
     * @param playerId This is the player ID used to search for the player
     * @return double This returns the salary if player is found, else throws PlayerNotFoundException
     */
	@Override
	public double getSalary(int playerId) {
		log.info("PlayerService getSalary {}", playerId);
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if (playerOptional.isPresent()) {
			return playerOptional.get().getSalary();
		} else {
			String exceptionMessage = playerWithId + playerId + " not found";
			log.warn(exceptionMessage);
			throw new PlayerNotFoundException(exceptionMessage);
		}
	}

}
