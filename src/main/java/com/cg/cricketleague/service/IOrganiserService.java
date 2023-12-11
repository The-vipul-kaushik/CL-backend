package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.Organiser;
import com.cg.cricketleague.model.Tournament;

public interface IOrganiserService {

	public abstract Organiser getOrgById(int organiserId);
	
	public abstract List<Organiser> getAllOrgs();
	
	public abstract Organiser insertOrg(Organiser organiser);
	
	public abstract Organiser updateOrg(Organiser organiser);
	
	public abstract List<Tournament> getToursByOrgId(int organiserId);

}
