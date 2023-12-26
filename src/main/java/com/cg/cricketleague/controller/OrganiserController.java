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

import com.cg.cricketleague.model.Organiser;
import com.cg.cricketleague.model.Tournament;
import com.cg.cricketleague.service.OrganiserService;


@RestController
@RequestMapping("/org")
@CrossOrigin(origins = "http://localhost:3000/")
public class OrganiserController implements IOrganiserController{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrganiserService service;
	
	
	/** 
     * This method displays the details of the organiser
     * @param organiserId This is the organiser ID used to search for the organiser
     * @return ResponseEntity<Organiser> This returns the ResponseEntity if organiser is found
     */
//	http://localhost:8088/org/get-organiser-by-id/1	
	@GetMapping("/get-organiser-by-id/{oid}")
	public ResponseEntity<Organiser> getOrganiserById(@PathVariable(name = "oid") int organiserId ) {
		LOG.info("OrganiserController getOrganiserById " + organiserId);
		Organiser org = service.getOrgById(organiserId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Organiser " + organiserId + " was found successfully.");
		ResponseEntity<Organiser> response = new ResponseEntity<>(org, headers, HttpStatus.OK);
		return response;
	}
	
	
	/** 
     * This method returns all the organisers of the tournaments
     * @return ResponseEntityy<List<Organiser>> This returns the ResponseEntity if Organiser is found
     */
//	http://localhost:8088/org/get-all-organisers
	@GetMapping("/get-all-organisers")
	public ResponseEntity<List<Organiser>> getAllOrganisers() {
		LOG.info("OrganiserController getAllOrganisers ");
		List<Organiser> orgList = service.getAllOrgs();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All organisers are found successfully.");
		ResponseEntity<List<Organiser>> response = new ResponseEntity<>(orgList, headers, HttpStatus.OK);
		return response;
	}
	
//	http://localhost:8088/org/insert-org
	@PostMapping("/insert-org")
	public ResponseEntity<Organiser> insertOrganiser(@Valid @RequestBody Organiser organiser) {
		LOG.info("OrganiserController insertOrganiser ");
		Organiser org = service.insertOrg(organiser);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Organiser " + organiser.getOrganiserId() + " was added successfully.");
		ResponseEntity<Organiser> response = new ResponseEntity<>(org, headers, HttpStatus.OK);
		return response;
	}

//	http://localhost:8088/org/update-org
	@PutMapping("/update-org")
	public ResponseEntity<Organiser> updateOrganiser(@Valid @RequestBody Organiser organiser) {
		LOG.info("OrganiserController updateOrganiser ");
		Organiser org = service.updateOrg(organiser);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Organiser " + organiser.getOrganiserId() + " was updated successfully.");
		ResponseEntity<Organiser> response = new ResponseEntity<>(org, headers, HttpStatus.OK);
		return response;
	}
	
//	http://localhost:8088/org/get-tournaments-by-organiserId/1	
	@GetMapping("/get-tournaments-by-organiserId/{oid}")
	public ResponseEntity<List<Tournament>> getTournamentsByOrganiserId(@PathVariable(name = "oid") int organiserId ) {
		LOG.info("OrganiserController getTournamentsByOrganiserId" + organiserId);
		List<Tournament> tourList = service.getToursByOrgId(organiserId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Organiser " + organiserId + " was found successfully.");
		ResponseEntity<List<Tournament>> response = new ResponseEntity<>(tourList, headers, HttpStatus.OK);
		return response;
	}

//	http://localhost:8088/org/delete-organiser-by-organiserId/1	
	@DeleteMapping("/delete-organiser-by-organiserId/{oid}")
	public void deleteByOrganiserId(@PathVariable(name = "oid") int organiserId ) {
		LOG.info("OrganiserController deleteByOrganiserId" + organiserId);
		service.deleteOrgByOrgId(organiserId);
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
