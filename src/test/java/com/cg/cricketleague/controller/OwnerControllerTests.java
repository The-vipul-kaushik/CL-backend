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

import com.cg.cricketleague.model.Owner;

@SpringBootTest
class OwnerControllerTests {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OwnerController ownerController;
	
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
	void testgetOwnerByIdHttpStatus() {
		LOG.info("OwnerControllerTests testgetOwnerByIdHttpStatus");
		HttpStatus expected = HttpStatus.FOUND;
		HttpStatus actual = ownerController.getOwnerById(owner.getOwnerId()).getStatusCode();
		assertEquals(expected,actual);
	}
	
	@Test
	void testgetTeamByIdHttpStatus() {
		LOG.info("OwnerControllerTests testgetTeamByIdHttpStatus");
		HttpStatus expected = HttpStatus.FOUND;
		HttpStatus actual = ownerController.getTeamById(owner.getOwnerId()).getStatusCode();
		assertEquals(expected,actual);	
	}
	
}
