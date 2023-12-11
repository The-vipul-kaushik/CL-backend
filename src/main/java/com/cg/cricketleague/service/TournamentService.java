package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.MatchNotFoundException;
import com.cg.cricketleague.exception.NotAuthorizedException;
import com.cg.cricketleague.exception.OrganiserNotFoundException;
import com.cg.cricketleague.exception.TournamentNotFoundException;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Role;
import com.cg.cricketleague.model.Tournament;
import com.cg.cricketleague.repository.MatchRepository;
import com.cg.cricketleague.repository.OrganiserRepository;
import com.cg.cricketleague.repository.TournamentRepository;

@Service
public class TournamentService implements ITournamentService{

private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	OrganiserRepository organiserRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	private CricketLeagueUserService appUserService;
	
	
	/**
	   * This method is used to find tournament by tournamentId. 
	   * @param tournamentId This is the only parameter to getTourById method.
	   * @return Tournament This returns Tournament object.
	   * @see TournamentNotFoundException
	   */
	@Override
	public Tournament getTourById(int tournamentId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService getTourById");
				Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
				if (tournamentOptional.isPresent()) {
					return tournamentOptional.get();
				} else {
					String exceptionMessage = "tournament with tournamentId " + tournamentId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
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
	   * This method is used to find all the tournaments 
	   * @param 
	   * @return List<Tournament> list of all tournaments
	   * @see TournamentNotFoundException
	   */
	@Override
	public List<Tournament> getAllTours() {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService getAllTournaments");
				List<Tournament> tournamentList = tournamentRepository.findAll();
				if (tournamentList.isEmpty()) {
					String exceptionMessage = "No tournaments Found";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
				} else {
					return tournamentList;
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
	public Tournament insertTour(Tournament tournament) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService insertTour");
				if (tournament.getOrganiser() == null || organiserRepository.findById(tournament.getOrganiser().getOrganiserId()).isPresent()) {
					return tournamentRepository.save(tournament);
				} else {
					String exceptionMessage = "Organiser with OrganiserId " + tournament.getOrganiser().getOrganiserId() + " does not exist.";
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
	
	@Override
	public Tournament updateTour(Tournament tournament) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService updateTour");
				Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournament.getTournamentId());
				if (tournamentOptional.isPresent()) {
					if (tournament.getOrganiser() == null || organiserRepository.findById(tournament.getOrganiser().getOrganiserId()).isPresent()) {
						return tournamentRepository.save(tournament);
					} else {
						String exceptionMessage = "Organiser with organiserId " + tournament.getOrganiser().getOrganiserId() + " does not exist.";
						LOG.warn(exceptionMessage);
						throw new OrganiserNotFoundException(exceptionMessage);
					}
				} else {
					String exceptionMessage = "tournament with tournamentId " + tournament.getTournamentId() + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
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
	public List<Match> getAllMats(int tournamentId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService getAllMats");
				Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
				if (tournamentOptional.isPresent()) {
					return tournamentOptional.get().getMatches();
				} else {
					String exceptionMessage = "tournament with tournamentId " + tournamentId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
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
	public Match getMatchByTourIdAndMatchId(int tournamentId, int matchId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService getMatchByTourIdAndMatchId");
				Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
				if (tournamentOptional.isPresent()) {
					Optional<Match> matchOptional = matchRepository.findById(matchId);
					if (matchOptional.isPresent()) {
						return matchOptional.get();
					}
					else {
						String exceptionMessage = "match with matchId " + matchId + " does not exist.";
						LOG.warn(exceptionMessage);
						throw new MatchNotFoundException();
					}
				} else {
					String exceptionMessage = "tournament with tournamentId " + tournamentId + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
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
	public Tournament getTourByMatchId(int matchId) {
		
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				LOG.info("TournamentService getTourByMatch");
				return matchRepository.getById(matchId).getTournament();
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
