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

import com.cg.cricketleague.model.Organiser;
import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Skill;

@SpringBootTest
public class OrganiserControllerTests {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrganiserController organiserController;

	private static Organiser organiser;
	
	@BeforeAll
	public static void setUp() {
		organiser = new Organiser(1, "BCCI", "vipul@gmail.com", "9090909090", 100.0, 100.0, null);
	}
	
	@AfterAll
	static void teardown() {
		organiser = null;
	}
	
	@Test
	void testinsertOrganiserHttpStatus() {
		LOG.info("OrganiserControllerTests testinsertOrganiserHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = organiserController.insertOrganiser(organiser).getStatusCode();
		assertEquals(expected,actual);	
	}
	
	@Test
	void testupdateOrganiserHttpStatus() {
		LOG.info("OrganiserControllerTests testupdateOrganiserHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = organiserController.updateOrganiser(organiser).getStatusCode();
		assertEquals(expected,actual);	
	}
}
