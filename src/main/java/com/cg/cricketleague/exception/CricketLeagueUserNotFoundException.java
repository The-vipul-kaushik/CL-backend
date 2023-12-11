package com.cg.cricketleague.exception;

public class CricketLeagueUserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8340802823651555367L;

	public CricketLeagueUserNotFoundException() {
		super();
	}

	public CricketLeagueUserNotFoundException(String message) {
		super(message);
	}
}
