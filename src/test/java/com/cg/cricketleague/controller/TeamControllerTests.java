package com.cg.cricketleague.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Team;

@SpringBootTest
public class TeamControllerTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TeamController teamController;
	
	private static Team team;

	@BeforeAll
	public static void setUp() {
		team = new Team(3, "DC", "DCC","RP", null, null);
	}
	
	@AfterAll
	static void teardown() {
		team = null;
	}
	
	@Test
	void testGetTeamByIdHttpStatus() {
		LOG.info("TeamControllerTests testGetTeamByIdHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = teamController.getTeamById(team.getTeamId()).getStatusCode();
		assertEquals(expected,actual);	
	}
	
	
	@Test
	void testGetTeamByIdHeaders() {
		LOG.info("TeamControllerTests testGetTeamByIdHeaders");
		String expected = "[Team with teamId " + team.getTeamId() + " was found successfully.]";
		String actual = teamController.getTeamById(team.getTeamId()).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}

	@Test
	void testGetTeamByNameHttpStatus() {
		LOG.info("TeamControllerTests testGetTeamByNameHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = teamController.getTeamByName(team.getTeamName()).getStatusCode();
		assertEquals(expected,actual);	
	}
	
	
	@Test
	void testGetTeamByNameHeaders() {
		LOG.info("TeamControllerTests testGetTeamByNameHeaders");
		String expected = "[Team with teamName " + team.getTeamName() + " was found successfully.]";
		String actual = teamController.getTeamByName(team.getTeamName()).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}
}