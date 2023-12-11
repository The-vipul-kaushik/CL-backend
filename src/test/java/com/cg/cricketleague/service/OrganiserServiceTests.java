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

import com.cg.cricketleague.model.Organiser;

@SpringBootTest
public class OrganiserServiceTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrganiserService organiserService;
	
	private static Organiser organiser;
	
	@BeforeAll
	public static void setUp() {
		organiser = new Organiser(1, "BCCI", "vipul@gmail.com", "9090909090", 100.0, 100.0, null);
	}

	@AfterAll
	public static void tearDown() {
		organiser = null;
	}
	
	@Test
	void testinsertOrg() {
		LOG.info(organiser.toString());
		Organiser expected = organiser;
		Organiser actual = organiserService.insertOrg(organiser);
		assertEquals(expected, actual);
	}
	
	@Test
	void testinsertOrgFailure() {
		LOG.info(organiser.toString());
		Organiser unexpected = organiser;
		Organiser actual = organiserService.insertOrg(new Organiser(2, "BCCI", "vipul@gmail.com", "9090909090", 100.0, 100.0, null));
		assertNotEquals(unexpected, actual);
	}
	
}
