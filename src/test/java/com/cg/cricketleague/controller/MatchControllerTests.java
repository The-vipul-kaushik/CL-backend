package com.cg.cricketleague.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Tournament;

@SpringBootTest
class MatchControllerTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MatchController matchController;

	private static Match match, match2;

	@BeforeAll
	public static void setUp() {
		match = new Match(1, "CSK VS MI", "MUM",new Date(2020-01-21),null, null);
		match2 = new Match();
	}

	@AfterAll
	static void teardown() {
		match = null;
		match2 = null;
	}

	@Test
	void testGetMatchByIdHttpStatus() {
		LOG.info("MatchControllerTests testGetMatchByIdHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = matchController.getMatchById(match.getMatchId()).getStatusCode();
		assertEquals(expected, actual);
	}
	

	@Test
	void testGetMatchByIdHeaders() {
		LOG.info("MatchControllerTests testgetMacthByIdHeaders");
		String expected = "[Match with matchId " + match.getMatchId() + " was found successfully.]";
		String actual = matchController.getMatchById(match.getMatchId()).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}

	@Test
	void testAddMatch() {
		LOG.info("MatchControllerTests testAddMatch");
		HttpStatus expected = HttpStatus.CREATED;
		match2.setMatchName("KKR VS SRH");
		match2.setTournament(new Tournament(1, null, null, null));
		HttpStatus actual = matchController.addMatch(match2).getStatusCode();
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetMatchByIdHttpStatusFailure() {
		LOG.info("MatchControllerTests testGetMatchByIdHttpStatus");
		HttpStatus unexpected = HttpStatus.NOT_FOUND;
		HttpStatus actual = matchController.getMatchById(match.getMatchId()).getStatusCode();
		assertNotEquals(unexpected, actual);
	}

}