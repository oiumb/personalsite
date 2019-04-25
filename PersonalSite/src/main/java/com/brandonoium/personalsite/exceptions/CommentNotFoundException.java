package com.brandonoium.personalsite.exceptions;

public class CommentNotFoundException extends ResourceNotFoundException {
	
	public CommentNotFoundException(long id) {
		super("Could not find comment " + id);
	}
}
