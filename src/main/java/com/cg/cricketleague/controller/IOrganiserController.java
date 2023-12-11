package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.Organiser;
import com.cg.cricketleague.model.Tournament;

public interface IOrganiserController {

	public abstract ResponseEntity<Organiser> getOrganiserById(int organiserId);
	
	public abstract ResponseEntity<List<Organiser>> getAllOrganisers();
	
	public abstract ResponseEntity<Organiser> insertOrganiser(Organiser organiser);
	
	public abstract ResponseEntity<Organiser> updateOrganiser(Organiser organiser);
	
	public abstract ResponseEntity<List<Tournament>> getTournamentsByOrganiserId(int organiserId);
	

}
