package com.ss.utopia.exceptions;

public class PassengerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public PassengerNotFoundException() {}
	public PassengerNotFoundException(String message) {
		super(message);
	}
}
