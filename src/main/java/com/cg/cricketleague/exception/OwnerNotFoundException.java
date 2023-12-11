package com.cg.cricketleague.exception;

public class OwnerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -394503854854916245L;
	
	public OwnerNotFoundException() {
		super();
	}

	public OwnerNotFoundException(String message) {
		super(message);
	}

}
