package com.brandonoium.personalsite.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brandonoium.personalsite.exceptions.UserNotFoundException;
import com.brandonoium.personalsite.model.User;
import com.brandonoium.personalsite.repositories.UserRepository;
import com.brandonoium.personalsite.resourceassemblers.UserResourceAssembler;
import com.brandonoium.personalsite.security.PasswordEncryptionService;

@RestController
public class UserController {

	private UserRepository userRepo;
	private UserResourceAssembler assembler;
	private PasswordEncryptionService encoder;
	
	public UserController(UserRepository userRepo, UserResourceAssembler assembler, PasswordEncryptionService encoder) {
		super();
		this.userRepo = userRepo;
		this.assembler = assembler;
		this.encoder = encoder;
	}
	
	
	@GetMapping(value="/users", produces="application/json")
	public Resources<Resource<User>> all() {
		
		List<Resource<User>> users = new ArrayList<Resource<User>>();
		
		for(User u : userRepo.findAll()) {
			users.add(assembler.toResource(u));
		}
		
		return new Resources<Resource<User>>(users);
	}
	
	@PostMapping(value="/users", produces="application/json")
	Resource<User> newUser(@RequestBody User newUser) {
		User u = new User();
		
		u.setUsername(newUser.getUsername());
		u.setPasswdHash(encoder.encode(newUser.getPasswdHash()));
		u.setRole("USER");
		
		return assembler.toResource(userRepo.save(u));
	}
	
	@GetMapping(value="/users/{id}", produces="application/json")
	public Resource<User> one(@PathVariable long id) {
		
		User u = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		return assembler.toResource(u);
	}
	
	@DeleteMapping(value="/users/{id}")
	void deleteUser(@PathVariable long id) {
		
		userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		userRepo.deleteById(id);
	}
}
