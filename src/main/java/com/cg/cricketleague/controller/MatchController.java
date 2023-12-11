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

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;
import com.cg.cricketleague.service.MatchService;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "http://localhost:3000/")
public class MatchController implements IMatchController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MatchService service;

	// http://localhost:8088/match/get-match-by-id/{mid}
	@Override
	@GetMapping("/get-match-by-id/{mid}")
	public ResponseEntity<Match> getMatchById(@PathVariable(name = "mid") int matchId) {
		LOG.info(Integer.toString(matchId));
		Match match = service.getMat(matchId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match with matchId " + matchId + " was found successfully.");
		ResponseEntity<Match> response = new ResponseEntity<>(match, headers, HttpStatus.OK);
		return response;
	}

	//	http://localhost:8088/match/get-match-by-name/{mname}
	@Override
	@GetMapping("/get-match-by-name/{mname}")
	public ResponseEntity<Match> getMatchByName(@PathVariable(name = "mname") String matchName) {
		LOG.info("Match by matchName "+ matchName);
		Match match = service.getMatchByName(matchName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match with matchName " + matchName + " was found successfully.");
		ResponseEntity<Match> response = new ResponseEntity<>(match, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/match/get-all-matches
	@Override
	@GetMapping("/get-all-matches")
	public ResponseEntity<List<Match>> getAllMatches() {
		LOG.info("get-all-matches");
		List<Match> matchList = service.findAllMat();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Matches were found successfully.");
		ResponseEntity<List<Match>> response = new ResponseEntity<>(matchList, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/match/add-match
	@Override
	@PostMapping("/add-match")
	public ResponseEntity<Match> addMatch(@Valid @RequestBody Match match) {
		LOG.info(match.toString());
		Match mat = service.insertMat(match);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match " + mat.getMatchId() + " was created successfully.");
		ResponseEntity<Match> response = new ResponseEntity<>(mat, headers, HttpStatus.CREATED);
		return response;
	}

	// http://localhost:8088/match/update-match
	@Override
	@PutMapping("/update-match")
	public ResponseEntity<Match> updateMatch(@Valid @RequestBody Match match) {
		LOG.info(match.toString());
		Match mat = service.updateMat(match);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match " + mat.getMatchId() + " was updated successfully.");
		ResponseEntity<Match> response = new ResponseEntity<>(mat, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/match/delete-match/{mid}
	@Override
	@DeleteMapping("/delete-match/{mid}")
	public ResponseEntity<Match> deleteMatch(@PathVariable(name = "mid") int matchId) {
		LOG.info("Match deleted with matchId= "+ matchId);
		Match mat = service.deleteMat(matchId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match " + matchId + " was deleted successfully.");
		ResponseEntity<Match> response = new ResponseEntity<>(mat, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/match/get-tournament-by-matchid/{mid}
	@Override
	@GetMapping("/get-tournament-by-matchid/{mid}")
	public ResponseEntity<Tournament> getTournamentByMatchId(@PathVariable(name = "mid") int matchId) {
		LOG.info(Integer.toString(matchId));
		Tournament tournament = service.getTournamentByMatch(matchId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Tournament with " + matchId + " was found successfully.");
		ResponseEntity<Tournament> response = new ResponseEntity<>(tournament, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/match/get-audience-list
	@Override
	@GetMapping("/get-audience-list")
	public ResponseEntity<List<Audience>> getAllAudience() {
		LOG.info("get-all-audiences");
		List<Audience> audienceList = service.getAllAudience();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Audiences were found successfully.");
		ResponseEntity<List<Audience>> response = new ResponseEntity<>(audienceList, headers, HttpStatus.OK);
		return response;
	}

	// http://localhost:8088/match/get-audiences-by-matchid/{mid}
	@Override
	@GetMapping("/get-audiences-by-matchid/{mid}")
	public ResponseEntity<List<Audience>> getAudiencesByMatchId(@PathVariable(name = "mid") int matchId) {
		LOG.info("Getting audiences by matchId " + matchId);
		List<Audience> audienceList = service.getAudiencesByMatch(matchId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Audiences with matchId " + matchId + " were found successfully.");
		ResponseEntity<List<Audience>> response = new ResponseEntity<>(audienceList, headers, HttpStatus.OK);
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
