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
import com.cg.cricketleague.model.Ticket;
import com.cg.cricketleague.service.AudienceService;

@RestController
@RequestMapping("/audience")	
@CrossOrigin(origins = "http://localhost:3000/")
public class AudienceController implements IAudienceController{
	
	@Autowired		
	private AudienceService  service;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass()); 

//	http://localhost:8088/audience/get-audience-by-id/{aid}
	@Override
	@GetMapping("/get-audience-by-id/{aid}")
	public ResponseEntity<Audience> getAudienceById(@PathVariable(name = "aid") int audienceId) {
		LOG.info("AudienceController getAudienceById: " + audienceId);
		Audience audience = service.getAudience(audienceId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Audience with audienceId" + audienceId + " was found successfully.");
		return new ResponseEntity<>( audience, headers, HttpStatus.OK);
	}
	
//	http://localhost:8088/audience/get-all-audiences/
	@Override
	@GetMapping("/get-all-audiences")
	public ResponseEntity<List<Audience>> getAllAudiences() {
		LOG.info("AudienceController getAllAudiences");
		List<Audience> audienceList = service.getAllAudiences();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Audiences were found successfully.");
		return new ResponseEntity<>(audienceList, headers, HttpStatus.OK);
	}
	
	@Override
	@PostMapping("/add-audience")
	public ResponseEntity<Audience> insertAudience(@Valid @RequestBody Audience audience) {
		LOG.info("AudienceController insertAudience" + audience.toString());
		Audience a = service.insertAudience(audience);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", audience.toString() + " was added successfully.");
		return new ResponseEntity<>(a, headers, HttpStatus.OK);
	}
	@Override
	@PutMapping("/update-audience")
	public ResponseEntity<Audience> updateAudience(@Valid @RequestBody Audience audience) {
		LOG.info("AudienceController updateAudience");
		Audience p = service.updateAudience(audience);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", audience.toString() + " was updated successfully.");
		return new ResponseEntity<>(p, headers, HttpStatus.OK);
	}
	
	
	@Override
	@GetMapping("/get-match-by-audienceId/{id}")
	public ResponseEntity<Match> getMatchByAudienceId(@PathVariable(name = "id") int audienceId){
		LOG.info("AudienceController getMatchByAudienceId: " + audienceId);
		Match m = service.getAudience(audienceId).getMatches();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match with audienceId " + audienceId + " was found successfully.");
		return new ResponseEntity<>(m, headers, HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/get-ticket-by-audienceId/{id}")
	public ResponseEntity<Ticket> getTicketByAudienceId(@PathVariable(name = "id") int audienceId){
		LOG.info("AudienceController getTicketByAudienceId: " + audienceId);
		Ticket m = service.getAudience(audienceId).getTickets();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Match with audienceId " + audienceId + " was found successfully.");
		return new ResponseEntity<>(m, headers, HttpStatus.OK);
	}
	@Override
	@GetMapping("/get-total-amount-for-all-tickets")
	public ResponseEntity<Double> getPaidAmountForAllTickets(){
		LOG.info("AudienceController getPaidAmountForAllTickets");
		Double d = service.getPaidAmountForAllTickets();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Total amount for all tickets has been calculated successfully.");
		return new ResponseEntity<>(d, headers, HttpStatus.OK);
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
