package com.cg.cricketleague.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cg.cricketleague.model.Team;
import com.cg.cricketleague.service.TeamService;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "http://localhost:3000/")
public class TeamController implements ITeamController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TeamService service;

	// http://localhost:8088/team/get-team-by-id/{tid}
	@Override
	@GetMapping("/get-team-by-id/{tid}") // 1
	public ResponseEntity<Team> getTeamById(@PathVariable(name = "tid") int teamId) {
		LOG.info(Integer.toString(teamId));
		Team team = service.getTeam(teamId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Team with teamId " + teamId + " was found successfully.");
		ResponseEntity<Team> response = new ResponseEntity<>(team, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/team/get-team-by-name/{tname}
	@Override
	@GetMapping("/get-team-by-name/{tname}") // 1
	public ResponseEntity<Team> getTeamByName(@PathVariable(name = "tname") String teamName) {
		LOG.info(teamName);
		Team team = service.getTeamByName(teamName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Team with teamName " + teamName+ " was found successfully.");
		ResponseEntity<Team> response = new ResponseEntity<>(team, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/team/get-all-teams
	@Override
	@GetMapping("/get-all-teams")
	public ResponseEntity<List<Team>> getAllTeams() {
		LOG.info("get-all-teams");
		List<Team> teamList = service.getAllTeams();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Teams were found successfully.");
		ResponseEntity<List<Team>> response = new ResponseEntity<>(teamList, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/team/add-team
	@Override
	@PostMapping("/add-team")
	public ResponseEntity<Team> addTeam(@RequestBody Team team) {
		LOG.info(team.toString());
		Team tm = service.insertTeam(team);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Team " + tm.getTeamId() + " was created successfully.");
		ResponseEntity<Team> response = new ResponseEntity<>(tm, headers, HttpStatus.CREATED);
		return response;
	}

	// http://localhost:8088/team/update-team
	@Override
	@PutMapping("/update-team")
	public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
		LOG.info(team.toString());
		Team tm = service.updateTeam(team);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Team " + tm.getTeamId() + " was updated successfully.");
		ResponseEntity<Team> response = new ResponseEntity<>(tm, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/team/get-all-players
	@Override
	@GetMapping("/get-all-players")
	public ResponseEntity<List<Player>> getAllPlayers() {
		LOG.info("get-all-players-in-a-team");
		List<Player> playerList = service.getAllPlayers();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Players were found successfully.");
		ResponseEntity<List<Player>> response = new ResponseEntity<>(playerList, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/team/get-all-players-by-team{tid}
	@Override
	@GetMapping("/get-all-players-by-team/{tid}")
	public ResponseEntity<List<Player>> getPlayersByTeam(@PathVariable(name = "tid") int teamId) {
		LOG.info("get-all-players-by-team");
		List<Player> playerList = service.getPlayersByTeam(teamId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Players with teamId" + teamId + " was found successfully.");
		ResponseEntity<List<Player>> response = new ResponseEntity<>(playerList, headers, HttpStatus.OK);
		return response;
	}
	
	// http://localhost:8088/team/delete-team/{tid}
	@Override
	@DeleteMapping("/delete-team/{tid}")
	public ResponseEntity<Team> deleteTeam(@PathVariable(name = "tid") int teamId) {
		LOG.info("Team deleted with teamId= "+ teamId);
		Team team = service.deleteTeam(teamId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Team " + teamId + " was deleted successfully.");
		ResponseEntity<Team> response = new ResponseEntity<>(team, headers, HttpStatus.OK);
		return response;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
