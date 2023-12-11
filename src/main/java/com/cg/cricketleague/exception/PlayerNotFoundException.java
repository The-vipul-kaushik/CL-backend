package com.cg.cricketleague.exception;

public class PlayerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException() {
		super();
	}

	public PlayerNotFoundException(String message) {
		super(message);
	}

}
