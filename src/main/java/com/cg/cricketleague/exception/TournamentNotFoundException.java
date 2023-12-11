package com.cg.cricketleague.exception;

public class TournamentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4214716043226419131L;

	public TournamentNotFoundException() {
		super();
	}

	public TournamentNotFoundException(String message) {
		super(message);
	}
}

