package com.brandonoium.personalsite.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.brandonoium.personalsite.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String resourceNotFoundHandler(ResourceNotFoundException e) {
		return e.getMessage();
	}
}
