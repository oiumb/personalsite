package com.brandonoium.personalsite.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {
	
	public UserNotFoundException(long id) {
		super("Could not find user " + id);
	}
}
