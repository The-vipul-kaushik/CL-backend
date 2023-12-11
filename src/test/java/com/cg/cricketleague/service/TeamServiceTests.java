package com.cg.cricketleague.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cricketleague.model.Team;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

@SpringBootTest
public class TeamServiceTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TeamService teamService;

	private static Team team;

	@BeforeAll
	public static void setUp() {
		team = new Team(3, "DC", "DCC","RP", null, null);
	}

	@AfterAll
	public static void tearDown() {
		team = null;
	}

	@Test
	void testGetTeam() {
		LOG.info("TeamService testGetTeamId");
		Team expected = team;
		Team actual = teamService.getTeam(team.getTeamId());
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	void testGetTeamIdFailure() {
		LOG.info("TeamService testGetTeamIdFailure");
		Team unexpected = team;
		Team actual = teamService.getTeam(2);
		assertNotEquals(unexpected, actual);
	}
	
	@Test
	void testInsertTeam() {
		LOG.info("TeamServiceTests testInsertAudience");
		Team expected = team;
		Team actual = teamService.insertTeam(team);
		assertEquals(expected, actual);
	}
	
	@Test
	void testInsertTeamFailure() {
		LOG.info("TeamServiceTests testInsertTeamFailure");
		Team unexpected = new Team(5, "GM", "DF","ls", null, null);
		Team actual = teamService.insertTeam(team);
		assertNotEquals(unexpected, actual);
	}

}
