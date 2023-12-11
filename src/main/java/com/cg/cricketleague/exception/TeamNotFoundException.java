package com.cg.cricketleague.exception;

public class TeamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8917700634279684091L;

	
	public TeamNotFoundException() {
		super();
	}

	public TeamNotFoundException(String message) {
		super(message);
	}

}
