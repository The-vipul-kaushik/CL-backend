package com.cg.cricketleague.exception;

public class TournamentMappedWithMatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TournamentMappedWithMatchException() {
		super();
	}
	public TournamentMappedWithMatchException(String message) {
		super(message);
	}
}
