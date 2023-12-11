package com.cg.cricketleague.exception;

public class CricketLeagueUserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4862786405787654481L;

	public CricketLeagueUserAlreadyExistsException() {
		super();
	}

	public CricketLeagueUserAlreadyExistsException(String message) {
		super(message);
	}
}

