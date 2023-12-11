package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.NotAuthorizedException;
import com.cg.cricketleague.exception.TicketMappedWithAudienceException;
import com.cg.cricketleague.exception.TicketNotFoundException;
import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Role;
import com.cg.cricketleague.model.Ticket;
import com.cg.cricketleague.repository.AudienceRepository;
import com.cg.cricketleague.repository.TicketRepository;

@Service
public class TicketService implements ITicketService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	AudienceRepository audienceRepository;
	@Autowired
	CricketLeagueUserService cricketLeagueUserService;
	Audience audience;
	/**
	   * This method is used to find ticket by ticketId. 
	   * @param ticketId Only ticketId is needed as a parameter.
	   * @return Ticket This returns Ticket object.
	   * @see TicketNotFoundException
	   */
	@Override
	public Ticket getTicket(int ticketId) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		LOG.info("TicketService getTicket" + ticketId);
		Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
		if (ticketOptional.isPresent()) {
			return ticketOptional.get();
		} else {
			String exceptionMessage = "Ticket with ticketId " + ticketId + " not found";
			LOG.warn(exceptionMessage);
			throw new TicketNotFoundException(exceptionMessage);
		}
			} else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	/**
	   * This method is used to insert ticket. 
	   * @param ticket Only ticket object is needed as a parameter.
	   * @return Ticket This returns Ticket object.
	   */
	@Override
	public Ticket insertTicket(Ticket ticket) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		LOG.info("TicketService insertTicket" + ticket.toString());
		
			return ticketRepository.save(ticket);
	} else {
		String exceptionMessage = "You are not authorised to access this resource!";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
	}
} else {
	String exceptionMessage = "You are not logged in.";
	LOG.warn(exceptionMessage);
	throw new NotAuthorizedException(exceptionMessage);
}
	}
	
	@Override
	public Ticket updateTicket(Ticket ticket) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		LOG.info("TicketService updateTicket" + ticket.toString());
		Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getTicketId());
		if (ticketOptional.isPresent()){
				return ticketRepository.save(ticket);
			} 
			
			else {
				String exceptionMessage = "Ticket with ticketId " + ticket.getTicketId()
						+ " does not exist.";
				LOG.warn(exceptionMessage);
				throw new TicketNotFoundException(exceptionMessage);
			}
	} else {
		String exceptionMessage = "You are not authorised to access this resource!";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
	}
} else {
	String exceptionMessage = "You are not logged in.";
	LOG.warn(exceptionMessage);
	throw new NotAuthorizedException(exceptionMessage);
}
	}
	
	@Override
	public Ticket cancelTicket(int ticketId) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
		List<Audience> audienceList = audienceRepository.findAll();
		int flag=0;
		for(Audience a :audienceList) {
			if(a.getTickets().getTicketId()==ticketId)
				flag=1;
		}
		if (ticketOptional.isPresent() && flag==1 ) {
			String exceptionMessage = "Ticket with ticketId " + ticketId + " has an association with Audience and hence can't be deleted";
			LOG.error(exceptionMessage);
			throw new TicketMappedWithAudienceException(exceptionMessage);}
		else if(ticketOptional.isPresent()) {
			LOG.info(ticketOptional.get().toString());
			ticketRepository.deleteById(ticketId);
			return ticketOptional.get();
		} else {
			String exceptionMessage = "Ticket with ticketId " + ticketId + " not found!";
			LOG.warn(exceptionMessage);
			throw new TicketNotFoundException(exceptionMessage);
		}
	} else {
		String exceptionMessage = "You are not authorised to access this resource!";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
	}
} else {
	String exceptionMessage = "You are not logged in.";
	LOG.warn(exceptionMessage);
	throw new NotAuthorizedException(exceptionMessage);
}
	}
	
	@Override
	public List<Ticket> getAllTickets(){
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		LOG.info("TicketService getAllTickets");
		List<Ticket> ticketList = ticketRepository.findAll();
		if (ticketList.isEmpty()) {
			String exceptionMessage = "No Tickets Found";
			LOG.warn(exceptionMessage);
			throw new TicketNotFoundException(exceptionMessage);
		} else {
			return ticketList;
		}
	} else {
		String exceptionMessage = "You are not authorised to access this resource!";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
	}
} else {
	String exceptionMessage = "You are not logged in.";
	LOG.warn(exceptionMessage);
	throw new NotAuthorizedException(exceptionMessage);
}
	}

	
	
	@Override
	public double calculateBill(int ticketId) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
		if (ticketOptional.isPresent()) {
			LOG.info(ticketOptional.get().toString());
			double sum = ticketOptional.get().getTotalAmount()* ticketOptional.get().getNoOfSeats();
			return sum;
		} else {
			String exceptionMessage = "Ticket with ticketId " + ticketId + " not found!";
			LOG.warn(exceptionMessage);
			throw new TicketNotFoundException(exceptionMessage);
		}
	} else {
		String exceptionMessage = "You are not authorised to access this resource!";
		LOG.warn(exceptionMessage);
		throw new NotAuthorizedException(exceptionMessage);
	}
} else {
	String exceptionMessage = "You are not logged in.";
	LOG.warn(exceptionMessage);
	throw new NotAuthorizedException(exceptionMessage);
}
	}
}
