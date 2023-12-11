package com.cg.cricketleague.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cricketleague.model.Organiser;
import com.cg.cricketleague.model.Tournament;

@SpringBootTest
public class TournamentServiceTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TournamentService tournamentService;
	
	private static Tournament tournament;
	
	private static Organiser organiser;
	
	@BeforeAll
	public static void setUp() {

		tournament = new Tournament(1, "IPL", organiser, null);
		List<Tournament> l = new ArrayList<Tournament>();
		l.add(tournament);
		organiser = new Organiser(1, "BCCI", "vipul@gmail.com", "9090909090", 100.0, 100.0, l);
	}

	@AfterAll
	public static void tearDown() {
		tournament = null;
		organiser = null;
	}
	
	@Test
	void testinsertTour() {
		LOG.info(tournament.toString());
		Tournament expected = tournament;
		Tournament actual = tournamentService.insertTour(tournament);
		assertEquals(expected, actual);
	}

//	@Disabled
	@Test
	 void testinsertTourFailure() {
		LOG.info(tournament.toString());
		Tournament unexpected = tournament;
		Tournament actual = tournamentService.insertTour(new Tournament(1, "IPL", organiser, null));
		assertNotEquals(unexpected, actual);
	}

}
