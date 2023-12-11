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

import com.cg.cricketleague.model.Ticket;

@SpringBootTest
public class TicketServiceTests {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TicketService ticketService;
	
	private static Ticket ticket;
	
	@BeforeAll
	public static void setUp() {
		ticket = new Ticket(1, "tk1", 100, 2);
		
	}
	
	@AfterAll
	static void teardown() {
		ticket = null;
	}
	
	@Test
	public void testgetTicket() {
		LOG.info("TicketServiceTests testgetTicket");
		Ticket expected = ticket;
		Ticket actual = ticketService.getTicket(ticket.getTicketId());
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testgetTicketFailure() {
		LOG.info("TicketServiceTests testgetTicketFailure");
		Ticket expected = ticket;
		Ticket actual = ticketService.getTicket(4);
		assertNotEquals(expected, actual);	
	}
	
	@Test
	public void testinsertTicket() {
		LOG.info("TicketServiceTests testinsertTicket");
		Ticket expected = ticket;
		Ticket actual = ticketService.insertTicket(ticket);
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testinsertTicketFailure() {
		LOG.info("TicketServiceTests testinsertTicketFailure");
		Ticket expected = new Ticket(2, "tk2", 100, 2);
		Ticket actual = ticketService.insertTicket(ticket);
		assertNotEquals(expected, actual);	
	}
	
}
