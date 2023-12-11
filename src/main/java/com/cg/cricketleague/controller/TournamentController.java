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

import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;
import com.cg.cricketleague.service.TournamentService;

@RestController
@RequestMapping("/tour")
@CrossOrigin(origins = "http://localhost:3000/")
public class TournamentController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TournamentService service;
	
	/** 
     * This method displays the details of the tournament
     * @param tournamentId This is the tournament ID used to search for the tournament
     * @return ResponseEntity<Tournament> This returns the ResponseEntity if tournament is found
     */
//	http://localhost:8088/tour/get-tournament-by-id/1
	@GetMapping("/get-tournament-by-id/{tid}")
	public ResponseEntity<Tournament> getTournamentById(@PathVariable(name = "tid") int tournamentId) {
		LOG.info("TournamentController getTournamentById " + tournamentId);
		Tournament t = service.getTourById(tournamentId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Tournament " + tournamentId + " was found successfully.");
		ResponseEntity<Tournament> response = new ResponseEntity<>(t, headers, HttpStatus.OK);
		return response;
	}
	
	
	/** 
     * This method returns all the tournaments 
     * @return ResponseEntityy<List<Tournament>> This returns the ResponseEntity if Tournament is found
     */
//	http://localhost:8088/tour/get-all-tournaments
	@GetMapping("/get-all-tournaments")
	public ResponseEntity<List<Tournament>> getAllTournaments() {
		LOG.info("TournamentController getAllTournaments");
		List<Tournament> tourList = service.getAllTours();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All Tournaments are found successfully.");
		ResponseEntity<List<Tournament>> response = new ResponseEntity<>(tourList, headers, HttpStatus.OK);
		return response;
	}

//	http://localhost:8088/tour/insert-tournament
	@PostMapping("/insert-tournament")
	public ResponseEntity<Tournament> insertTournament(@Valid @RequestBody Tournament tournament) {
		Tournament tour = service.insertTour(tournament);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Tournament " + tournament.getTournamentId() + " was added successfully.");
		ResponseEntity<Tournament> response = new ResponseEntity<>(tour, headers, HttpStatus.OK);
		return response;
	}

//	http://localhost:8088/tour/update-tournament
	@PutMapping("/update-tournament")
	public ResponseEntity<Tournament> updateTournament(@Valid @RequestBody Tournament tournament) {
		Tournament tour = service.updateTour(tournament);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Tournament " + tournament.getTournamentId() + " was updated successfully.");
		ResponseEntity<Tournament> response = new ResponseEntity<>(tour, headers, HttpStatus.OK);
		return response;
	}


//	http://localhost:8088/tour/get-all-matches-by-tournamentId/1
	@GetMapping("/get-all-matches-by-tournamentId/{tid}")
	public ResponseEntity<List<Match>> getAllMatches(@PathVariable(name = "tid") int tournamentId) {
		List<Match> matList = service.getAllMats(tournamentId);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(matList, headers, HttpStatus.OK);
	}


//	http://localhost:8088/tour/get-all-matches-by-tournamentId-and-matchId/1
	@GetMapping("/get-all-matches-by-tournamentId-and-matchId/{tid}/{mid}")
	public ResponseEntity<Match> getAllMatches(@PathVariable(name = "tid") int tournamentId, @PathVariable(name = "mid") int matchId) {
		Match matList = service.getMatchByTourIdAndMatchId(tournamentId, matchId);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(matList, headers, HttpStatus.OK);
	}
	
//	http://localhost:8088/tour/get-tournament-by-matchId/1
	@PutMapping("/get-tournament-by-matchId/{mid}")
	public ResponseEntity<Tournament> getTournamentByMatch(@PathVariable(name = "mid") int matchId) {
		Tournament tour = service.getTourByMatchId(matchId);
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Tournament> response = new ResponseEntity<>(tour, headers, HttpStatus.OK);
		return response;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
