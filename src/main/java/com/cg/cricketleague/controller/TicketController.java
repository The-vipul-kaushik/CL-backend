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
import com.cg.cricketleague.model.Ticket;
import com.cg.cricketleague.service.TicketService;

@RestController
@RequestMapping("/ticket")	
@CrossOrigin(origins = "http://localhost:3000/")
public class TicketController implements ITicketController {
	
	@Autowired		
	private TicketService  service;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
//	http://localhost:8088/ticket/get-ticket-by-id/{tid}
	@Override
	@GetMapping("/get-ticket-by-id/{tid}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable(name = "tid") int ticketId) {
		LOG.info("TicketController getTicketById: " + ticketId);
		Ticket ticket = service.getTicket(ticketId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Ticket with ticketId " + ticketId + " was found successfully.");
		ResponseEntity<Ticket> response = new ResponseEntity<>( ticket, headers, HttpStatus.OK);
		return response;
	}
	
//	http://localhost:8088/tickets/get-all-tickets/
	@Override
	@GetMapping("/get-all-tickets")
	public ResponseEntity<List<Ticket>> getAllTickets(){
		LOG.info("TicketController getAllTickets");
		List<Ticket> t = service.getAllTickets();
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Tickets were found successfully.");
		ResponseEntity <List<Ticket>> response = new ResponseEntity<>( t, headers, HttpStatus.OK);
		return response;
	}
	
	@Override
	@PostMapping("/add-ticket")
	public ResponseEntity<Ticket> insertTicket(@Valid @RequestBody Ticket ticket){
		LOG.info("TicketController insertTicket "+ ticket.toString());
		Ticket tk = service.insertTicket(ticket);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", ticket.toString() + " was added successfully.");
		ResponseEntity<Ticket> response = new ResponseEntity<>( tk, headers, HttpStatus.OK);
		return response;
	}
	
	@Override
	@PutMapping("/update-ticket")
	public ResponseEntity<Ticket> updateTicket(@Valid @RequestBody Ticket ticket){
		LOG.info("TicketController updateTicket "+ ticket.toString());
		Ticket tk = service.updateTicket(ticket);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", ticket.toString() + " was update successfully.");
		ResponseEntity<Ticket> response = new ResponseEntity<>( tk, headers, HttpStatus.OK);
		return response;
	}
	
	@Override
	@DeleteMapping("/delete-ticket/{id}")
	public ResponseEntity<Ticket> cancelTicketById(@PathVariable(name = "id") int ticketId) {
		LOG.info("TicketController cancelTicketById: " + ticketId);
		Ticket ticket = service.cancelTicket(ticketId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Ticket with ticketId " + ticketId + " was deleted successfully.");
		ResponseEntity<Ticket> response = new ResponseEntity<>( ticket, headers, HttpStatus.OK);
		return response;
	}
	
	@Override
	@GetMapping("/calculate-bill")
	public ResponseEntity<Double> calculateBill(int ticketId){
		LOG.info("TicketController calculateBill for ticketId: " + ticketId);
		Double tk = service.calculateBill(ticketId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Total Bill for ticketId " + ticketId + " was calculated successfully.");
		ResponseEntity<Double> response = new ResponseEntity<>( tk, headers, HttpStatus.OK);
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
