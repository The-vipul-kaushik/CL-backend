package com.cg.cricketleague.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Skill;
import com.cg.cricketleague.model.Team;
import com.cg.cricketleague.service.PlayerService;

@RestController
@RequestMapping("/player")	
@CrossOrigin(origins = "http://localhost:3000/")
public class PlayerController {
	
	@Autowired		
	private PlayerService service;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	
	String playerText = "Player ";
	String msg = "message";
	String foundSuccessfully = " was found successfully.";
	
	
	/** 
     * This method displays the details of the player
     * @param playerId This is the player ID used to search for the player
     * @return ResponseEntity<Player> This returns the ResponseEntity if player is found
     */
	@GetMapping("/get-player-by-id/{pid}")
	public ResponseEntity<Player> getPlayerById(@PathVariable(name = "pid") int playerId) {
		log.info("PlayerController getPlayerById: {}", playerId);
		Player player = service.getPlayer(playerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, playerText + playerId + foundSuccessfully);
		return new ResponseEntity<>(player, headers, HttpStatus.FOUND);
	}

	
	/** 
     * This method returns all the players of the teams
     * @return ResponseEntityy<List<Player>> This returns the ResponseEntity if player is found
     */
	@GetMapping("/get-all-players")
	public ResponseEntity<List<Player>> getAllPlayers() {
		log.info("PlayerController getAllPlayers");
		List<Player> playerList = service.getAllPlayers();
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, "Players were found successfully.");
		return new ResponseEntity<>(playerList, headers, HttpStatus.FOUND);
	}

	
	/** 
     * This method adds players to the team
     * @param player This is the player object
     * @return ResponseEntity<Player> This returns the ResponseEntity if player is added successfully
     */
	@PostMapping("/add-players")
	public ResponseEntity<Player> insertPlayer(@Valid @RequestBody Player player) {
		log.info("PlayerController insertPlayer: {}", player.getPlayerId());
		Player p = service.insertPlayer(player);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, player.toString() + " was added successfully.");
		return  new ResponseEntity<>(p, headers, HttpStatus.CREATED);
	}

	
	/** 
     * This method updates a particular player's details 
     * @param player This is the player object
     * @return ResponseEntity<Player> This returns the ResponseEntity if player is updated successfully
     */
	@PutMapping("/update-players")
	public ResponseEntity<Player> updatePlayer(@Valid @RequestBody Player player) {
		log.info("PlayerController updatePlayer: {}", player.getPlayerId());
		Player p = service.updatePlayer(player);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, player.toString() + " was updated successfully.");
		return new ResponseEntity<>(p, headers, HttpStatus.OK);
	}

	
	/** 
     * This method is used to remove a player from the team 
     * @param playerId This is the player ID used to search for the player
     * @return ResponseEntity<Player> This returns the ResponseEntity if player is removed successfully
     */
	@DeleteMapping("/delete-player/{pid}")
	public ResponseEntity<Player> deletePlayer(@PathVariable(name = "pid") int playerId) {
		log.info("PlayerController deletePlayer: {}", playerId);
		Player player = service.deletePlayer(playerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, playerText+ playerId + " was deleted successfully.");
		return new ResponseEntity<>(player, headers, HttpStatus.OK);
	}

	
	/** 
     * This method displays the skill of the player
     * @param playerId This is the player ID used to search for the player
     * @return ResponseEntity<Skill> This returns the ResponseEntity if skill of the player is found
     */
	@GetMapping("/get-skill-by-id/{pid}")
	public ResponseEntity<Skill> getSkillById(@PathVariable(name = "pid") int playerId) {
		log.info("PlayerController getSkillById: {}", playerId);
		Skill skill = service.getPlayer(playerId).getSkill();
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, playerText + playerId + foundSuccessfully);
		return new ResponseEntity<>(skill, headers, HttpStatus.FOUND);
	}


	/** 
     * This method displays the team of the player
     * @param playerId This is the player ID used to search for the player
     * @return ResponseEntity<Team> This returns the ResponseEntity if team of the player is found
     */
	@GetMapping("/get-team-by-id/{pid}")
	public ResponseEntity<Team> getTeamById(@PathVariable(name = "pid") int playerId) {
		log.info("PlayerController getTeamById: {}", playerId);
		Team team = service.getTeam(playerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, playerText + playerId + foundSuccessfully);
		return new ResponseEntity<>(team, headers, HttpStatus.FOUND);
	}

	
	/** 
     * This method displays the salary of the player
     * @param playerId This is the player ID used to search for the player
     * @return ResponseEntity<Double> This returns the ResponseEntity if salary of the player is found
     */
	@GetMapping("/get-salary-by-id/{pid}")
	public ResponseEntity<Double> getSalaryById(@PathVariable(name = "pid") int playerId) {
		log.info("PlayerController getSalaryById: {}", playerId);
		Double salary = service.getPlayer(playerId).getSalary();
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, playerText + playerId + foundSuccessfully);
		return new ResponseEntity<>(salary, headers, HttpStatus.FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach(error -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
