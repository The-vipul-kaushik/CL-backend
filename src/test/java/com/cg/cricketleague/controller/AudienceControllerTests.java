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

import com.cg.cricketleague.model.Audience;

@SpringBootTest
public class AudienceControllerTests {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AudienceController audienceController;
	
	private static Audience audience;

	@BeforeAll
	public static void setUp() {

		audience = new Audience(4, "Sonu", null, null);
	}
	
	@AfterAll
	static void teardown() {
		audience = null;
	}
	
	@Test
	public void testgetAudienceByIdHttpStatus() {
		LOG.info("AudienceControllerTests testgetAudienceByIdHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = audienceController.getAudienceById(audience.getAudienceId()).getStatusCode();
		assertEquals(expected,actual);	
	}
	
	
	@Test
	public void testgetAudienceByIdHeaders() {
		LOG.info("AudienceControllerTests testgetAudienceByIdHeaders");
		String expected = "[Audience with audienceId" + audience.getAudienceId() + " was found successfully.]";
		String actual = audienceController.getAudienceById(audience.getAudienceId()).getHeaders().get("message").toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testgetMatchByAudienceIdHttpStatus() {
		LOG.info("AudienceControllerTests testgetMatchByAudienceIdHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = audienceController.getMatchByAudienceId(audience.getAudienceId()).getStatusCode();
		assertEquals(expected,actual);	
	}
	
	
	@Test
	public void getTicketByAudienceIdHttpStatus() {
		LOG.info("AudienceControllerTests testgetTicketByAudienceIdHttpStatus");
		HttpStatus expected = HttpStatus.OK;
		HttpStatus actual = audienceController.getTicketByAudienceId(audience.getAudienceId()).getStatusCode();
		assertEquals(expected,actual);	
	}
}
