package com.cg.cricketleague.exception;

public class MatchNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2307972298200483544L;
	
	public MatchNotFoundException() {
		super();
	}

	public MatchNotFoundException(String message) {
		super(message);
	}

}
