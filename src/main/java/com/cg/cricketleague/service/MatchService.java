package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.AudienceNotFoundException;
import com.cg.cricketleague.exception.MatchNotFoundException;
import com.cg.cricketleague.exception.NotAuthorizedException;
import com.cg.cricketleague.exception.TournamentNotFoundException;
import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Role;
import com.cg.cricketleague.model.Tournament;
import com.cg.cricketleague.repository.AudienceRepository;
import com.cg.cricketleague.repository.MatchRepository;
import com.cg.cricketleague.repository.TournamentRepository;

@Service
public class MatchService implements IMatchService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	TournamentRepository tournamentRepository;

	@Autowired
	AudienceRepository audienceRepository;

	@Autowired
	private CricketLeagueUserService appUserService;

	/**
	 * This method is used to find match by matchId.
	 * 
	 * @param matchId This is the only parameter to getMat method.
	 * @return Match This returns Match object.
	 * @see MatchNotFoundException
	 */
	@Override
	public Match getMat(int matchId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("getMatchById " + matchId);
				Optional<Match> matchOptional = matchRepository.findById(matchId);
				if (matchOptional.isPresent()) {
					return matchOptional.get();
				} else {
					String exceptionMessage = "Match with matchId " + matchId + " not found";
					LOG.warn(exceptionMessage);
					throw new MatchNotFoundException(exceptionMessage);
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
	public Match getMatchByName(String matchName) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("getMachByName " + matchName);
				Match match = matchRepository.findByMatchName(matchName);
				if (match != null) {
					return match;
				} else {
					String exceptionMessage = "Match with matchName " + matchName + " not found";
					LOG.warn(exceptionMessage);
					throw new MatchNotFoundException(exceptionMessage);
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
	 * This method is used to find all the matches
	 * 
	 * @param
	 * @return List<Match> list of all matches
	 * @see MatchNotFoundException
	 */
	@Override
	public List<Match> findAllMat() {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("Getting all the matches");
				List<Match> matchList = matchRepository.findAll();
				return matchList;

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
	public Match insertMat(Match match) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info(match.toString());

				if (match.getTournament() == null
						|| tournamentRepository.findById(match.getTournament().getTournamentId()).isPresent()) {
					return matchRepository.save(match);
				} else {
					String exceptionMessage = "Tournament with tournamentId " + match.getTournament().getTournamentId()
							+ " does not exist.";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
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
	public Match updateMat(Match match) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info(match.toString());
				Optional<Match> matchOptional = matchRepository.findById(match.getMatchId());
				if (matchOptional.isPresent()) {
					if (match.getTournament() == null
							|| tournamentRepository.findById(match.getTournament().getTournamentId()).isPresent()) {
						return matchRepository.save(match);
					} else {
						String exceptionMessage = "Tournamnet with tournamentId "
								+ match.getTournament().getTournamentId() + " does not exist.";
						LOG.warn(exceptionMessage);
						throw new TournamentNotFoundException(exceptionMessage);
					}
				} else {
					String exceptionMessage = "Match with matchId " + match.getMatchId() + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new MatchNotFoundException(exceptionMessage);
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
	public Match deleteMat(int matchId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("Match removed");
				Optional<Match> matchOptional = matchRepository.findById(matchId);
				if (matchOptional.isPresent()) {
					Match match = matchOptional.get();
					matchRepository.delete(match);
					return match;
				} else {
					throw new MatchNotFoundException("Team with matchId " + matchId + " not found");
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
	public Tournament getTournamentByMatch(int matchId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("getTournament By matchId " + matchId);
				Optional<Match> matchOptional = matchRepository.findById(matchId);
				if (matchOptional.isPresent()) {
					return matchOptional.get().getTournament();
				} else {
					String exceptionMessage = "Tournament with matchId " + matchId + " not found";
					LOG.warn(exceptionMessage);
					throw new TournamentNotFoundException(exceptionMessage);
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
	public List<Audience> getAllAudience() {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("Getting all the audiences");
				List<Audience> audienceList = audienceRepository.findAll();
				return audienceList;

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
	public List<Audience> getAudiencesByMatch(int matchId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("getAudience By matchId " + matchId);
				Optional<Match> matchOptional = matchRepository.findById(matchId);
				if (matchOptional.isPresent()) {
					return matchOptional.get().getAudiences();
				} else {
					String exceptionMessage = "Audience with matchId " + matchId + " not found";
					LOG.warn(exceptionMessage);
					throw new AudienceNotFoundException(exceptionMessage);
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
