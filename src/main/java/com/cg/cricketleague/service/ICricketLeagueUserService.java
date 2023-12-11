package com.cg.cricketleague.service;

import java.util.List;

import com.cg.cricketleague.model.CricketLeagueUser;

public interface ICricketLeagueUserService {

	public abstract List<CricketLeagueUser> getAllUsers();

	public abstract CricketLeagueUser registerUser(CricketLeagueUser appUser);

	public abstract CricketLeagueUser loginUser(CricketLeagueUser appUser);

	public abstract String logoutUser(String userName);

	public abstract CricketLeagueUser updateUser(CricketLeagueUser appUser);

}