package com.brandonoium.personalsite.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brandonoium.personalsite.model.User;
import com.brandonoium.personalsite.repositories.UserRepository;

@RestController
public class TutRestEndpoint {

	private UserRepository userRepo;
	
	public TutRestEndpoint(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/msg", method=RequestMethod.GET, produces="application/json")
	public ArrayList<String> getMsgHandler()
	{
		ArrayList<String> l = new ArrayList<String>();
		
		Iterator<User> i = userRepo.findAll().iterator();
		
		while(i.hasNext())
		{
			l.add(i.next().getUsername());
		}
		
		if(l.isEmpty())
			l.add("No messages today!");
		
		return l;
	}
	
}
