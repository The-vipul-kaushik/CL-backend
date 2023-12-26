package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.NotAuthorizedException;
import com.cg.cricketleague.exception.OrganiserNotFoundException;
import com.cg.cricketleague.model.Organiser;
import com.cg.cricketleague.model.Role;
import com.cg.cricketleague.model.Tournament;
import com.cg.cricketleague.repository.OrganiserRepository;

@Service
public class OrganiserService implements IOrganiserService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrganiserRepository organiserRepository;
	
	@Autowired
	private CricketLeagueUserService appUserService;
	
	/**
	   * This method is used to find organiser by organiserId. 
	   * @param organiserId This is the only parameter to getMat method.
	   * @return Organiser This returns Organiser object.
	   * @see organiserNotFoundException
	   */
	@Override
	public Organiser getOrgById(int organiserId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("OrganiserService getOrgById");
				Optional<Organiser> organiserOptional = organiserRepository.findById(organiserId);
				if (organiserOptional.isPresent()) {
					return organiserOptional.get();
				} else {
					String exceptionMessage = "Organiser with OrganiserId " + organiserId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new OrganiserNotFoundException(exceptionMessage);
				}
			}
			else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		} 
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	
	
	/**
	   * This method is used to find all the organisers 
	   * @param 
	   * @return List<Organiser> list of all organisers
	   * @see OrganiserNotFoundException
	   */
	@Override
	public List<Organiser> getAllOrgs() {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("OrganiserService getAllOrgs");
				List<Organiser> organiserList = organiserRepository.findAll();
				if (organiserList.isEmpty()) {
					String exceptionMessage = "No Organisers Found";
					LOG.warn(exceptionMessage);
					throw new OrganiserNotFoundException(exceptionMessage);
				} else {
					return organiserList;
				}
			}
			else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	
	@Override
	public Organiser insertOrg(Organiser organiser) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				return organiserRepository.save(organiser);
			}
			else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	
	@Override
	public Organiser updateOrg(Organiser organiser) {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				return organiserRepository.save(organiser);
			}
			else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	
	@Override
	public List<Tournament> getToursByOrgId(int organiserId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("OrganiserService getToursByOrgId");
				Optional<Organiser> organiserOptional = organiserRepository.findById(organiserId);
				if (organiserOptional.isPresent()) {
					return organiserOptional.get().getTournaments();
				} else {
					String exceptionMessage = "Organiser with OrganiserId " + organiserId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new OrganiserNotFoundException(exceptionMessage);
				}
			}
			else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
	
	public void deleteOrgByOrgId(int organiserId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("OrganiserService deleteOrgByOrgId");
				Organiser org = organiserRepository.getById(organiserId);
				if (org==null) {
					LOG.info("Can't find record to delete");
				}
				else {
					organiserRepository.deleteById(organiserId);
				}
			}
			else {
				String exceptionMessage = "You are not authorised to access this resource!";
				LOG.warn(exceptionMessage);
				throw new NotAuthorizedException(exceptionMessage);
			}
		}
		else {
			String exceptionMessage = "You are not logged in.";
			LOG.warn(exceptionMessage);
			throw new NotAuthorizedException(exceptionMessage);
		}
	}
}
