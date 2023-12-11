package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.NotAuthorizedException;
import com.cg.cricketleague.exception.OwnerNotFoundException;
import com.cg.cricketleague.exception.PlayerNotFoundException;
import com.cg.cricketleague.exception.TeamNotFoundException;
import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Role;
import com.cg.cricketleague.model.Team;
import com.cg.cricketleague.repository.OwnerRepository;
import com.cg.cricketleague.repository.PlayerRepository;
import com.cg.cricketleague.repository.TeamRepository;

@Service
public class TeamService implements ITeamService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	private CricketLeagueUserService appUserService;

	@Override
	public Team getTeam(int teamId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("getTeamById " + teamId);
				Optional<Team> teamOptional = teamRepository.findById(teamId);
				if (teamOptional.isPresent()) {
					return teamOptional.get();
				} else {
					String exceptionMessage = "Team with teamId " + teamId + " not found";
					LOG.warn(exceptionMessage);
					throw new TeamNotFoundException(exceptionMessage);
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
	public Team getTeamByName(String teamName) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {

				LOG.info("getTeamByName " + teamName);
				Team team = teamRepository.findByTeamName(teamName);
				if (team != null) {
					return team;
				} else {
					String exceptionMessage = "Team with teamName " + teamName + " not found";
					LOG.warn(exceptionMessage);
					throw new TeamNotFoundException(exceptionMessage);
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
	public List<Team> getAllTeams() {
		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				
				LOG.info("Getting all the teams");
				List<Team> teamList = teamRepository.findAll();
				return teamList;
				
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
	public Team insertTeam(Team team) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				
				LOG.info(team.toString());

				if (team.getOwner() == null || ownerRepository.findById(team.getOwner().getOwnerId()).isPresent()) {
					return teamRepository.save(team);
				} else {
					String exceptionMessage = "Owner with ownerId " + team.getOwner().getOwnerId() + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new OwnerNotFoundException(exceptionMessage);
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
	public Team updateTeam(Team team) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				
				LOG.info(team.toString());
				Optional<Team> teamOptional = teamRepository.findById(team.getTeamId());
				if (teamOptional.isPresent()) {
					if (team.getOwner() == null || ownerRepository.findById(team.getOwner().getOwnerId()).isPresent()) {
						return teamRepository.save(team);
					} else {
						String exceptionMessage = "Owner with ownerId " + team.getOwner().getOwnerId()
								+ " does not exist.";
						LOG.warn(exceptionMessage);
						throw new OwnerNotFoundException(exceptionMessage);
					}
				} else {
					String exceptionMessage = "Team with teamId " + team.getTeamId() + " does not exist.";
					LOG.warn(exceptionMessage);
					throw new TeamNotFoundException(exceptionMessage);
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
	public Team deleteTeam(int teamId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				
				LOG.info(Integer.toString(teamId));
				Optional<Team> teamOptional = teamRepository.findById(teamId);
				if (teamOptional.isPresent()) {
					Team tm = teamOptional.get();
					teamRepository.delete(tm);
					return tm;
				} else {
					throw new TeamNotFoundException("Team with teamId " + teamId + " not found");
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
	public List<Player> getAllPlayers() {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				
				LOG.info("Getting all the players");
				List<Player> playerList = playerRepository.findAll();
				return playerList;
				
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
	public List<Player> getPlayersByTeam(int teamId) {

		if (appUserService.loggedInUser != null) {
			if (appUserService.loggedInUser.getRole().equals(Role.ADMIN)) {
				
				LOG.info("getPlayerByTeamId " + teamId);
				Optional<Team> teamOptional = teamRepository.findById(teamId);
				if (teamOptional.isPresent()) {
					return teamOptional.get().getPlayers();
				} else {
					String exceptionMessage = "Player with teamId " + teamId + " not found";
					LOG.warn(exceptionMessage);
					throw new PlayerNotFoundException(exceptionMessage);
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
