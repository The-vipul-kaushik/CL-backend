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

import com.cg.cricketleague.model.Ticket;
@SpringBootTest
public class TicketControllerTests {
	
		Logger LOG = LoggerFactory.getLogger(this.getClass());
		
		@Autowired
		private TicketController ticketController;
		
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
		public void testgetTicketByIdHttpStatus() {
			LOG.info("TicketControllerTests testgetTicketByIdHttpStatus");
			HttpStatus expected = HttpStatus.OK;
			HttpStatus actual = ticketController.getTicketById(ticket.getTicketId()).getStatusCode();
			assertEquals(expected,actual);
		}
		@Test
		public void testgetTicketByIdHeaders() {
			LOG.info("TicketControllerTests testgetTicketByIdHeaders");
			String expected = "[Ticket with ticketId " + ticket.getTicketId() + " was found successfully.]";
			String actual = ticketController.getTicketById(ticket.getTicketId()).getHeaders().get("message").toString();
			assertEquals(expected, actual);
		}
		
		@Test
		public void testinsertTicketHttpStatus() {
			LOG.info("TicketControllerTests testinsertTicketHttpStatus");
			HttpStatus expected = HttpStatus.OK;
			HttpStatus actual = ticketController.insertTicket(ticket).getStatusCode();
			assertEquals(expected,actual);
		}
		
		@Test
		public void testupdateTicketHttpStatus() {
			LOG.info("TicketControllerTests testupdateTicketHttpStatus");
			HttpStatus expected = HttpStatus.OK;
			HttpStatus actual = ticketController.updateTicket(ticket).getStatusCode();
			assertEquals(expected,actual);
		}

		
		@Test
		public void testcalculateBillHttpStatus() {
			LOG.info("TicketControllerTests testcalculateBillHttpStatus");
			HttpStatus expected = HttpStatus.OK;
			HttpStatus actual = ticketController.calculateBill(ticket.getTicketId()).getStatusCode();
			assertEquals(expected,actual);
		}
		
}
