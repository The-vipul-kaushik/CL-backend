package com.cg.cricketleague.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.cg.cricketleague.model.Player;
import com.cg.cricketleague.model.Skill;

@SpringBootTest
class PlayerControllerTests {

Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlayerController playerController;
	
	private static Player player;
	
	@BeforeAll
	public static void setUp() {
		player = new Player(3,"Nitish Rana",34000000,Skill.ALLROUNDER,null);
	}
	
	@AfterAll
	static void teardown() {
		player = null;
	}
	
	@Test
	void testgetPlayerByIdHttpStatus() {
		LOG.info("OwnerControllerTests testgetPlayerByIdHttpStatus");
		HttpStatus expected = HttpStatus.FOUND;
		HttpStatus actual = playerController.getPlayerById(player.getPlayerId()).getStatusCode();
		assertEquals(expected,actual);
	}
	
	@Test
	void testgetTeamByIdHttpStatus() {
		LOG.info("OwnerControllerTests testgetTeamByIdHttpStatus");
		HttpStatus expected = HttpStatus.FOUND;
		HttpStatus actual = playerController.getTeamById(player.getPlayerId()).getStatusCode();
		assertEquals(expected,actual);	
	}
}
