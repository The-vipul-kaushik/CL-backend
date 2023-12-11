package com.cg.cricketleague.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cricketleague.model.*;

@SpringBootTest
class PlayerServiceTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlayerService playerService;
	
	private static Player player;
	
	@BeforeAll
	public static void setUp() {
		player = new Player(3, "Nitish Rana", 34000000, Skill.ALLROUNDER, null);
	}
	
	@AfterAll
	static void teardown() {
		player = null;
	}

	@Test
	void testgetSkill() {
		LOG.info("PlayerServiceTests testgetSkill");
		Skill expected = player.getSkill();
		Skill actual = playerService.getSkill(player.getPlayerId());
		assertEquals(expected, actual);	
	}
	
	@Test
	void testgetSkillFailure() {
		LOG.info("PlayerServiceTests testgetSkillFailure");
		Skill expected = player.getSkill();
		Skill actual = playerService.getSkill(2);
		assertNotEquals(expected, actual);	
	}
	
	
	@Test
	void testgetSalary() {
		LOG.info("PlayerServiceTests testgetSalary");
		double expected = player.getSalary();
		double actual = playerService.getSalary(player.getPlayerId());
		assertEquals(expected, actual);	
	}
	
	@Test
	void testgetSalaryFailure() {
		LOG.info("PlayerServiceTests testgetSalaryFailure");
		double unexpected = player.getSalary();
		double actual = playerService.getSalary(2);
		assertNotEquals(unexpected, actual);	
	}

}
