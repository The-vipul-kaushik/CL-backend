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

import com.cg.cricketleague.model.Owner;
import com.cg.cricketleague.model.Team;
import com.cg.cricketleague.service.OwnerService;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "http://localhost:3000/")
public class OwnerController {
	
	@Autowired		
	private OwnerService service;
	
	String foundSuccessfully = " was found successfully.";
	String ownerText = "Owner ";
	String msg = "message";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	/** 
     * This method displays the details of the owner
     * @param ownerId This is the owner ID used to search for the owner
     * @return ResponseEntity<Owner> This returns the ResponseEntity if owner is found
     */
	@GetMapping("/get-owner-by-id/{oid}")
	public ResponseEntity<Owner> getOwnerById(@PathVariable(name = "oid") int ownerId) {
		log.info("OwnerController getOwnerById: {}", ownerId);
		Owner owner = service.getOwner(ownerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, ownerText + ownerId + foundSuccessfully);
		return new ResponseEntity<>(owner, headers, HttpStatus.FOUND);
	}


	/** 
     * This method displays all the owners
     * @return ResponseEntity<List<Owner>> This returns the ResponseEntity if owners are found
     */
	@GetMapping("/get-all-owners")
	public ResponseEntity<List<Owner>> getAllOwners() {
		log.info("OwnerController getAllOwners");
		List<Owner> ownerList = service.getAllOwners();
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, "Owners were found successfully.");
		return new ResponseEntity<>(ownerList, headers, HttpStatus.FOUND);
	}


	/** 
     * This method adds owners to the team
     * @param owner This is the owner object
     * @return ResponseEntity<Owner> This returns the ResponseEntity if owner is added successfully
     */
	@PostMapping("/add-owners")
	public ResponseEntity<Owner> insertOwner(@Valid @RequestBody Owner owner) {
		log.info("OwnerController insertOwner: {}", owner.getOwnerId());
		Owner o = service.insertOwner(owner);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, owner.toString() + " was added successfully");
		return new ResponseEntity<>(o, headers, HttpStatus.CREATED);
	}


	/** 
     * This method updates a particular owner's details 
     * @param owner This is the owner object
     * @return ResponseEntity<Owner> This returns the ResponseEntity if owner is updated successfully
     */
	@PutMapping("/update-owners")
	public ResponseEntity<Owner> updateOwner(@Valid @RequestBody Owner owner) {
		log.info("OwnerController updateOwner: {}", owner.getOwnerId());
		Owner o = service.updateOwner(owner);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, owner.toString() + " was updated successfully");
		return new ResponseEntity<>(o, headers, HttpStatus.OK);
	}
	

	/** 
     * This method is used to remove an owner from the team 
     * @param ownerId This is the owner ID used to search for the owner
     * @return ResponseEntity<Owner> This returns the ResponseEntity if owner is removed successfully
     */
	@DeleteMapping("/delete-owner/{oid}")
	public ResponseEntity<Owner> deleteOwner(@PathVariable(name = "oid") int ownerId) {
		log.info("PlayerController deletePlayer: {}", ownerId);
		Owner owner = service.deleteOwner(ownerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, ownerText + ownerId + " was deleted successfully");
		return new ResponseEntity<>(owner, headers, HttpStatus.OK);
	}


	/** 
     * This method displays the team that the owner owns 
     * @param ownerId This is the owner ID used to search for the owner
     * @return ResponseEntity<Team> This returns the ResponseEntity if team of the owner is found
     */
	@GetMapping("/get-team-by-id/{oid}")
	public ResponseEntity<Team> getTeamById(@PathVariable(name = "oid") int ownerId) {
		log.info("OwnerController getTeamById: {}", ownerId);
		Team team = service.getTeam(ownerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, ownerText + ownerId + foundSuccessfully);
		return new ResponseEntity<>(team, headers, HttpStatus.FOUND);
	}


	/** 
     * This method displays the budget of the team that the owner owns 
     * @param ownerId This is the owner ID used to search for the owner
     * @return ResponseEntity<Double> This returns the ResponseEntity if budget of the owner is found
     */
	@GetMapping("/get-budget-by-id/{oid}")
	public ResponseEntity<Double> getBudgetById(@PathVariable(name = "oid") int ownerId) {
		log.info("OwnerController getBudgetById: {}", ownerId);
		Double budget = service.getOwner(ownerId).getBudget();
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, ownerText + ownerId + foundSuccessfully);
		return new ResponseEntity<>(budget, headers, HttpStatus.FOUND);
	}


	/** 
     * This method assigns/pays the new salary to the respective player
     * @param ownerId This is the owner ID used to search for the owner
     * @param playerId This is the player ID used to search for the player
     * @param salary This is the salary which will be paid to the player
     * @return ResponseEntity<Double> This returns the ResponseEntity if salary is paid successfully
     */
	@PutMapping("/pay-salary-to-player/{oid}/{pid}/{sal}")
	public ResponseEntity<Double> paySalarytoPlayer(@PathVariable(name = "oid") int ownerId, 
			@PathVariable(name = "pid") int playerId, @PathVariable(name = "sal") double salary) {
		log.info("OwnerController paySalarytoPlayer: {}", playerId);
		Double paidSalary = service.paySalary(ownerId, playerId, salary);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, "Player " + playerId + foundSuccessfully);
		return new ResponseEntity<>(paidSalary, headers, HttpStatus.OK);
	}
	

	/** 
     * This method calculates and displays the sum of salaries of all the players of the team
     * @param ownerId This is the owner ID used to search for the owner
     * @return ResponseEntity<Double> This returns the ResponseEntity if total salary is found
     */
	@GetMapping("/get-total-salary-of-team/{oid}")
	public ResponseEntity<Double> getTotalSalaryOfAllPlayers(@PathVariable(name = "oid") int ownerId) {
		log.info("OwnerController getTotalSalaryOfAllPlayers: {}", ownerId);
		Double salary = service.getTotalSalary(ownerId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(msg, ownerText + ownerId + foundSuccessfully);
		return new ResponseEntity<>(salary, headers, HttpStatus.OK);
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
