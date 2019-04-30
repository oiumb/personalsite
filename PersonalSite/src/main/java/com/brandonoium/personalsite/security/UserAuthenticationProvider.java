package com.brandonoium.personalsite.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.brandonoium.personalsite.model.User;
import com.brandonoium.personalsite.repositories.UserRepository;

public class UserAuthenticationProvider implements AuthenticationProvider {
	
	private UserRepository userRepo;
	
	public UserAuthenticationProvider(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
	      String username = authentication.getName();
	      String password = authentication.getCredentials().toString();
	      
	      User u = userRepo.findByUsername(username).iterator().next();

	      if (u != null && u.getPasswdHash() == password) {

	            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());

	       } else {

	            throw new BadCredentialsException("Authentication failed");

	       }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
