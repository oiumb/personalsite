package com.brandonoium.personalsite.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.brandonoium.personalsite.model.User;
import com.brandonoium.personalsite.repositories.UserRepository;
import com.brandonoium.personalsite.resourceassemblers.UserResourceAssembler;

@RestController
public class UserController {

	private UserRepository userRepo;
	private UserResourceAssembler assembler;
	
	public UserController(UserRepository userRepo, UserResourceAssembler assembler) {
		super();
		this.userRepo = userRepo;
		this.assembler = assembler;
	}
	
	
	@GetMapping("/users")
	public Resources<Resource<User>> all() {
		
		List<Resource<User>> users = new ArrayList<Resource<User>>();
		
		for(User u : userRepo.findAll()) {
			users.add(assembler.toResource(u));
		}
		
		return new Resources<Resource<User>>(users);
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> one(@PathVariable long id) {
		
		User u = userRepo.findById(id).get();
		
		return assembler.toResource(u);
	}
}
