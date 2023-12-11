package com.cg.cricketleague.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(OwnerNotFoundException.class)
	public ResponseEntity<Object> handleOwnerNotFoundException(OwnerNotFoundException o) {
		String exceptionMessage = o.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<Object> handlePlayerNotFoundException(PlayerNotFoundException p) {
		String exceptionMessage = p.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TeamNotFoundException.class)
	public ResponseEntity<Object> handleTeamNotFoundException(TeamNotFoundException t) {
		String exceptionMessage = t.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MatchNotFoundException.class)
	public ResponseEntity<Object> handleMatchNotFoundException(MatchNotFoundException t) {
		String exceptionMessage = t.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TournamentNotFoundException.class)
	public ResponseEntity<Object> handleTournamentNotFoundException(TournamentNotFoundException t) {
		String exceptionMessage = t.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrganiserNotFoundException.class)
	public ResponseEntity<Object> handleOrganiserNotFoundException(OrganiserNotFoundException t) {
		String exceptionMessage = t.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(AudienceNotFoundException.class)
	public ResponseEntity<Object> handleAudienceNotFoundException(AudienceNotFoundException a) {
		String exceptionMessage = a.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<Object> handleTicketNotFoundException(TicketNotFoundException tk) {
		String exceptionMessage = tk.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullMatchException.class)
	public ResponseEntity<Object> handleNullMatchException(NullMatchException nm) {
		String exceptionMessage = nm.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.NO_CONTENT);
	}
	@ExceptionHandler(TicketMappedWithAudienceException.class)
	public ResponseEntity<Object> TicketMappedWithAudienceException(TicketMappedWithAudienceException t) {
		String exceptionMessage = t.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(TicketAlreadyBoughtException.class)
	public ResponseEntity<Object> TicketAlreadyBoughtException(TicketAlreadyBoughtException t) {
		String exceptionMessage = t.getMessage();
		LOG.info(exceptionMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", exceptionMessage);
		return new ResponseEntity<Object>(null, headers, HttpStatus.CONFLICT);
	}
}
