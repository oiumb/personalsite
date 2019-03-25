package com.brandonoium.personalsite.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TutRestEndpoint {
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/msg", method=RequestMethod.GET, produces="application/json")
	public ArrayList<String> getMsgHandler()
	{
		ArrayList<String> l = new ArrayList<String>();
		
		l.add("The message of the day is...");
		l.add("Balla balla balla, don't forget to holla.");
		
		
		return l;
	}
	
}
