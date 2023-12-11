package com.cg.cricketleague.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cricketleague.model.Audience;
import com.cg.cricketleague.model.Match;
import com.cg.cricketleague.model.Ticket;

@SpringBootTest
public class AudienceServiceTests {
Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AudienceService audienceService;
	
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
	public void testgetAudience() {
		LOG.info("AudienceServiceTests testgetAudience");
		Audience expected = audience;
		Audience actual = audienceService.getAudience(audience.getAudienceId());
		assertEquals(expected, actual);
	}
	
	@Test
	public void testgetAudiencefailure() {
		LOG.info("AudienceServiceTests testgetAudienceFailure");
		Audience expected = audience;
		Audience actual = audienceService.getAudience(1);
		assertNotEquals(expected, actual);
	}
	
}
