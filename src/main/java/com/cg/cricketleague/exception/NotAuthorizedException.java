package com.cg.cricketleague.exception;

public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -3898230272884339778L;

	public NotAuthorizedException() {
		super();
	}

	public NotAuthorizedException(String message) {
		super(message);
	}
}
