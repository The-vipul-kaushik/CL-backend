package com.cg.cricketleague.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cricketleague.exception.MatchNotFoundException;
import com.cg.cricketleague.exception.OrganiserNotFoundException;
import com.cg.cricketleague.exception.OwnerNotFoundException;
import com.cg.cricketleague.exception.PlayerNotFoundException;
import com.cg.cricketleague.exception.TeamNotFoundException;
import com.cg.cricketleague.exception.TournamentNotFoundException;
import com.cg.cricketleague.model.Owner;
import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Team;
import com.cg.cricketleague.repository.MatchRepository;
import com.cg.cricketleague.repository.OrganiserRepository;
import com.cg.cricketleague.repository.OwnerRepository;
import com.cg.cricketleague.repository.PlayerRepository;
import com.cg.cricketleague.repository.TeamRepository;
import com.cg.cricketleague.repository.TournamentRepository;

@Service
public class OwnerService implements IOwnerService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 

	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	OrganiserRepository organiserRepository;
	
	String doesNotExist = " does not exist.";
	String notFound = " not found";
	String ownerWithId = "Owner with ownerId ";

	/** 
     * This method takes an ownerId as input from the user and returns the details of the owner
     * @param ownerId This is the owner ID used to search for the owner
     * @return Owner This returns the owner if found, else throws OwnerNotFoundException
     */ 
	@Override
	public Owner getOwner(int ownerId) {
		log.info("OwnerService getOwner {}", ownerId);
		Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);
		if (ownerOptional.isPresent()) {
			return ownerOptional.get();
		} else {
			String exceptionMessage = ownerWithId + ownerId + doesNotExist;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method returns all the owners of the team in the tournament
     * @return List<Owner> This returns the list of owners if found, else throws OwnerNotFoundException
     */
	@Override
	public List<Owner> getAllOwners() {
		log.info("OwnerService getAllOwners");
		List<Owner> ownerList = ownerRepository.findAll();
		if (ownerList.isEmpty()) {
			String exceptionMessage = "No Owners Found";
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		} else {
			return ownerList;
		}
	}

	
	/** 
     * This method is used to add owners to the teams
     * @param owner This is the owner object  
     * @return Owner This returns the owner if added successfully, else throws TeamNotFoundException if 
     * team doesn't exist
     */
	@Override
	public Owner insertOwner(Owner owner) {
		log.info("OwnerService insertOwner {}", owner.getOwnerId());
		if(!teamRepository.findById(owner.getTeam().getTeamId()).isPresent()){
			String exceptionMessage = "Team with teamId " + owner.getTeam().getTeamId()
					+ doesNotExist;
			log.warn(exceptionMessage);
			throw new TeamNotFoundException(exceptionMessage);
		}else {
			return ownerRepository.save(owner);
		}
	}

	
	/** 
     * This method is used to update details of a owner
     * @param owner This is the owner object
     * @return Owner This returns the owner if added successfully, else throws TeamNotFoundException if 
     * team doesn't exist, else throws OwnerNotFoundException if owner doesn't exist
     */	
	@Override
	public Owner updateOwner(Owner owner) {
		log.info("OwnerService updateOwner {}", owner.getOwnerId());
		Optional<Owner> ownerOptional = ownerRepository.findById(owner.getOwnerId());
		if (ownerOptional.isPresent()) {
			if(!teamRepository.findById(owner.getTeam().getTeamId()).isPresent()){
				String exceptionMessage = "Team with teamId " + owner.getTeam().getTeamId()
						+ doesNotExist;
				log.warn(exceptionMessage);
				throw new TeamNotFoundException(exceptionMessage);
			}else {
				return ownerRepository.save(owner);
			}
		} else {
			String exceptionMessage = ownerWithId + owner.getOwnerId() + doesNotExist;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
	}
	

	/** 
     * This method is used to get the team that the owner owns
     * @param ownerId This is the owner ID used to search for the owner
     * @return Team This returns the team if found, else throws OwnerNotFoundException
     */ 
	@Override
	public Team getTeam(int ownerId) {
		log.info("OwnerService getTeam {}", ownerId);
		Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);
		if (ownerOptional.isPresent()) {
			return ownerOptional.get().getTeam();
		} else {
			String exceptionMessage = ownerWithId + ownerId + notFound;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
	}
	
	
	/** 
     * This method is used to remove an owner from the team
     * @param ownerId This is the owner ID used to search for the owner
     * @return Team This returns the team if found, else throws OwnerNotFoundException
     */ 
	@Override
	public Owner deleteOwner(int ownerId) {
		log.info("OwnerService deleteOwner {}", ownerId);
		Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);
		if (ownerOptional.isPresent()) {
			Owner owner = ownerOptional.get();
			ownerRepository.delete(owner);
			return owner;
		} else {
			String exceptionMessage = ownerWithId + ownerId + doesNotExist;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
		
	}

	
	/** 
     * This method is used to to get the budget of a team the owner owns
     * @param ownerId This is the owner ID used to search for the owner
     * @return Team This returns the team if found, else throws OwnerNotFoundException
     */ 
	@Override
	public double getBudget(int ownerId) {
		log.info("OwnerService getBudget {}", ownerId);
		Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);
		if (ownerOptional.isPresent()) {
			return ownerOptional.get().getBudget();
		} else {
			String exceptionMessage = ownerWithId + ownerId + notFound;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method is used to to get the budget of a team the owner owns
     * @param ownerId This is the owner ID used to search for the owner
     * @param playerId This is the player ID used to search for the player
     * @param salary This is the salary that is to be added
     * @return double This returns the salary if paid successfully, else throws OwnerNotFoundException
     *  if the ownerId does not exist and if the playerId does not exist then it throws PlayerNotFoundException
     */
	@Override
	public double paySalary(int ownerId, int playerId, double salary) {
		log.info("OwnerService paySalary {}", playerId);
		Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);
		if (ownerOptional.isPresent()) {
			Optional<Player>playerOptional = playerRepository.findById(playerId);
			if (playerOptional.isPresent()) {
					playerOptional.get().setSalary(salary);
					playerRepository.save(playerOptional.get());
					return salary;
			}
			else {
				String exceptionMessage = "Player with playerId " + playerId + notFound;
				log.warn(exceptionMessage);
				throw new PlayerNotFoundException(exceptionMessage);
			}
		} else {
			String exceptionMessage = ownerWithId + ownerId + notFound;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
	}

	
	/** 
     * This method is used to get the sum of salaries of all the players of the team of the owner
     * @param ownerId This is the owner ID used to search for the owner
     * @return double This returns the salary, else if the owner doesn't exist throws OwnerNotFoundException
     */ 
	@Override
	public double getTotalSalary(int ownerId) {
		log.info("OwnerService getTotalSalary {}", ownerId);
		Optional<Owner> ownerOptional = ownerRepository.findById(ownerId);
		if (ownerOptional.isPresent()) {
			double sumSalary = 0;
			List<Player> playerList = ownerOptional.get().getTeam().getPlayers();
			for(Player p : playerList) {
				sumSalary = sumSalary + p.getSalary();
			}
			return sumSalary;
		} else {
			String exceptionMessage = ownerWithId + ownerId + notFound;
			log.warn(exceptionMessage);
			throw new OwnerNotFoundException(exceptionMessage);
		}
	}
}
