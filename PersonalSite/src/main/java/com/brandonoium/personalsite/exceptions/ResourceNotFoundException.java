package com.brandonoium.personalsite.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException (String msg) {
		super(msg);
	}
}
