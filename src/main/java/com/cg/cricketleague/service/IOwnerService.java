package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Owner;
import com.cg.cricketleague.model.Team;

public interface IOwnerService {
	
	Owner getOwner(int ownerId);
	
	List<Owner> getAllOwners();
	
	Owner insertOwner(Owner owner);
	
	Owner updateOwner(Owner owner);
	
	Owner deleteOwner(int ownerId);
	
	Team getTeam(int ownerId);
	
	double paySalary(int ownerId, int playerId, double salary);
	
	double getBudget(int ownerId);
	
	double getTotalSalary(int ownerId);

}
