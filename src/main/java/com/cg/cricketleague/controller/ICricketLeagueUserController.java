package com.cg.cricketleague.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.cricketleague.model.CricketLeagueUser;

public interface ICricketLeagueUserController {

	public abstract ResponseEntity<List<CricketLeagueUser>> getAllAppUsers();

	public abstract ResponseEntity<CricketLeagueUser> register(CricketLeagueUser appUser);

	public abstract ResponseEntity<CricketLeagueUser> login(CricketLeagueUser appUser);

	public abstract ResponseEntity<String> logout(String userName);

	public abstract ResponseEntity<CricketLeagueUser> updateAppUser(CricketLeagueUser appUser);
}
