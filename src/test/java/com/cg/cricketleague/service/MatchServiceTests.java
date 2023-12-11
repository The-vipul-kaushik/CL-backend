package com.cg.cricketleague.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cricketleague.model.Match;

@SpringBootTest
public class MatchServiceTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MatchService matchService;

	private static Match match;

	@BeforeAll
	public static void setUp() {
		match = new Match(4, "KXIP VS GT", "MUM",new Date(2020-01-21), null, null);
	}

	@AfterAll
	public static void tearDown() {
		match = null;
	}

	@Test
	void testGetMatchId() {
		LOG.info("MatchService testGetMatchId");
		Match expected = match;
		Match actual = matchService.getMat(match.getMatchId());
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	void testGetMatchIdFailure() {
		LOG.info("MatchService testGetMatchIdFailure");
		Match unexpected = match;
		Match actual = matchService.getMat(2);
		assertNotEquals(unexpected, actual);
	}
	

	@Test
	void testGetMatchName() {
		LOG.info("MatchService testGetMatchName");
		Match expected = match;
		Match actual = matchService.getMatchByName(match.getMatchName());
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	void testGetMatchNameFailure() {
		LOG.info("MatchService testGetMatchNameFailure");
		Match unexpected = match;
		Match actual = matchService.getMatchByName("CSK VS MI");
		assertNotEquals(unexpected, actual);
	}

}
