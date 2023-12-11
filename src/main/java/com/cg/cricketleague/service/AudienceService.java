package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.AudienceNotFoundException;
import com.cg.cricketleague.exception.NotAuthorizedException;
import com.cg.cricketleague.exception.TicketAlreadyBoughtException;
import com.cg.cricketleague.exception.TicketNotFoundException;
import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Role;
import com.cg.cricketleague.model.Ticket;
import com.cg.cricketleague.repository.AudienceRepository;
import com.cg.cricketleague.repository.MatchRepository;
import com.cg.cricketleague.repository.TicketRepository;

@Service
public class AudienceService implements IAudienceService{

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AudienceRepository audienceRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	MatchRepository matchRepository;
	@Autowired
	CricketLeagueUserService cricketLeagueUserService;
	
	/**
	   * This method is used to find audience by audienceId. 
	   * @param audienceId Only audienceId is needed as a parameter.
	   * @return Audience This returns Audience object.
	   * @see AudienceNotFoundException
	   */
	@Override
	public Audience getAudience(int audienceId) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
		LOG.info("AudienceService getAudience" + audienceId);
		Optional<Audience> audienceOptional = audienceRepository.findById(audienceId);
		if (audienceOptional.isPresent()) {
			return audienceOptional.get();
		} else {
			String exceptionMessage = "Audience with audienceId " + audienceId + " not found";
			LOG.warn(exceptionMessage);
			throw new AudienceNotFoundException(exceptionMessage);
		}}
		else {
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
	   * This method is used to insert audience. 
	   * @param audience Only audience object is needed as a parameter.
	   * @return Audience This returns Audience object.
	   * @see TicketNotFoundException
	   */
	@Override
	public Audience insertAudience(Audience audience) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
		LOG.info("AudienceService insertAudience" + audience.toString());
		List<Audience> audienceList = audienceRepository.findAll();
		int flag=0;
		for(Audience a : audienceList) {
			if(a.getTickets().getTicketId()==audience.getTickets().getTicketId()) {
				flag=1;
				break;
				}
		}
		if(flag==1){
			String exceptionMessage = " Ticket with ticketId " + audience.getTickets().getTicketId()+ " is already bought.";
			LOG.warn(exceptionMessage);			
			throw new TicketAlreadyBoughtException(exceptionMessage);
		}
		else if (audience.getTickets()==null || ticketRepository.findById(audience.getTickets().getTicketId()).isPresent()) {
			return audienceRepository.save(audience);
		} else {
			String exceptionMessage = " Ticket with ticketId " + audience.getTickets().getTicketId()
					+ " does not exist.";
			LOG.warn(exceptionMessage);			
			throw new TicketNotFoundException(exceptionMessage);
		}
			}
			else {
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
	   * This method is used to update audience. 
	   * @param audience Only audience object is needed as a parameter.
	   * @return Audience This returns Audience object.
	   * @see TicketNotFoundException & AudienceNotFoundException
	   */
	@Override
	public Audience updateAudience(Audience audience) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
		LOG.info("AudienceService updateAudience" + audience.toString());
		Optional<Audience> audienceOptional = audienceRepository.findById(audience.getAudienceId());
		if (audienceOptional.isPresent()) {
			if (audience.getTickets() == null
					|| ticketRepository.findById(audience.getTickets().getTicketId()).isPresent()) {
				return audienceRepository.save(audience);
			} else {
				String exceptionMessage = "Ticket with ticketId " + audience.getTickets().getTicketId()
						+ " does not exist.";
				LOG.warn(exceptionMessage);
				throw new TicketNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = "Audience with audienceId " + audience.getAudienceId() + " does not exist.";
			LOG.warn(exceptionMessage);
			throw new AudienceNotFoundException(exceptionMessage);
		}
			}
			else {
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
	public List<Audience> getAllAudiences(){
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
		LOG.info("AudienceService getAllAudiences");
		List<Audience> audienceList = audienceRepository.findAll();
		if (audienceList.isEmpty()) {
			String exceptionMessage = "No Audiences Found";
			LOG.warn(exceptionMessage);
			throw new AudienceNotFoundException(exceptionMessage);
		} else {
			return audienceList;
		}
			}
			else {
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
	public Match getMatch(int audienceId) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN) || cricketLeagueUserService.loggedInUser.getRole().equals(Role.AUDIENCE)) {
		LOG.info("AudienceService getAudience " + audienceId);
		Optional<Audience> audienceOptional = audienceRepository.findById(audienceId);
		if (audienceOptional.isPresent()) {
			return audienceOptional.get().getMatches();
		} else {
			String exceptionMessage = "Audience with audienceId " + audienceId + " not found";
			LOG.warn(exceptionMessage);
			throw new AudienceNotFoundException(exceptionMessage);
		}
			}
			else {
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
	public Ticket getTicket(int audienceId) {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
		LOG.info("AudienceService getTicket " + audienceId);
		Optional<Audience> audienceOptional = audienceRepository.findById(audienceId);
		if (audienceOptional.isPresent()) {
			return audienceOptional.get().getTickets();
		} else {
			String exceptionMessage = "Audience with audienceId " + audienceId + " not found";
			LOG.warn(exceptionMessage);
			throw new AudienceNotFoundException(exceptionMessage);
		}
	}
	else {
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
	public double getPaidAmountForAllTickets() {
		if (cricketLeagueUserService.loggedInUser != null) {
			if (cricketLeagueUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
		LOG.info("AudienceService getPaidAmountForAllTickets");
		List<Ticket> tickets= ticketRepository.findAll();
		if (tickets.isEmpty()) {
			String exceptionMessage = "No Tickets Found";
			LOG.warn(exceptionMessage);
			throw new TicketNotFoundException(exceptionMessage);
		} else {
			double sum=0;
			for(Ticket t: tickets) {
				sum+=t.getTotalAmount()*t.getNoOfSeats();
			}
			return sum;
		}
	}
	else {
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
