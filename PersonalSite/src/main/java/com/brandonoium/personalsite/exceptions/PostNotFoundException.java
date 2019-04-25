package com.brandonoium.personalsite.exceptions;

public class PostNotFoundException extends ResourceNotFoundException {
	
	public PostNotFoundException(long id) {
		super("Could not find post " + id);
	}
}
