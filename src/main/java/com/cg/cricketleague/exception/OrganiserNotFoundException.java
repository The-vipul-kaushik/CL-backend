package com.cg.cricketleague.exception;

public class OrganiserNotFoundException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public OrganiserNotFoundException() {
		super();
	}

	public OrganiserNotFoundException(String message) {
		super(message);
	}
}
