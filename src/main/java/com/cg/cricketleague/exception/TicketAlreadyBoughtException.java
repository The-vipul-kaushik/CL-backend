package com.cg.cricketleague.exception;

public class TicketAlreadyBoughtException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketAlreadyBoughtException() {
		super();
	}
	public TicketAlreadyBoughtException(String message) {
		super(message);
	}
}
