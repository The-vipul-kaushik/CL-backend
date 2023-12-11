package com.cg.cricketleague.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import com.cg.cricketleague.model.Owner;
import com.cg.cricketleague.model.Team;

@SpringBootTest
class OwnerServiceTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OwnerService ownerService;
	
	private static Owner owner;
	
	@BeforeAll
	public static void setUp() {
		owner = new Owner(1, "Shah Rukh Khan", 180000000, null);
	}
	
	@AfterAll
	static void teardown() {
		owner = null;
	}

	@Test
	void testgetBudget() {
		LOG.info("OwnerServiceTests testgetBudget");
		double expected = owner.getBudget();
		double actual = ownerService.getBudget(owner.getOwnerId());
		assertEquals(expected, actual);	
	}
	
	@Test
	void testgetBudgetFailure() {
		LOG.info("PlayerServiceTests testgetBudgetFailure");
		double unexpected = owner.getBudget();
		double actual = ownerService.getBudget(2);
		assertNotEquals(unexpected, actual);	
	}
	
	@Test
	void testgetTeamFailure() {
		LOG.info("OwnerServiceTests testgetTeamFailure");
		Team unexpected = owner.getTeam();
		Team actual = ownerService.getTeam(2);
		assertNotEquals(unexpected, actual);	
	}

}
