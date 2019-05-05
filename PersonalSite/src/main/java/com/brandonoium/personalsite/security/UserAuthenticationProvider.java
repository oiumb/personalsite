package com.brandonoium.personalsite.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.brandonoium.personalsite.model.User;
import com.brandonoium.personalsite.repositories.UserRepository;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	private UserRepository userRepo;
	private PasswordEncryptionService encoder;

	public UserAuthenticationProvider(UserRepository userRepo, PasswordEncryptionService encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User u;
		
		//System.out.println("Trying username: " + username);
		//System.out.println("Trying password: " + password);

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		try {
			u = userRepo.findByUsername(username).iterator().next();
			//System.out.println("Found user " + u.getUsername());
			
		} catch(Exception e) {
			//System.out.println("User Not Found.");
			throw new BadCredentialsException("Authentication failed");
		}
		
		if (u != null && encoder.matches(password, u.getPasswdHash())) {

			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));// + u.getRole()));
			return new UsernamePasswordAuthenticationToken(username, password, authorities);

		} else {

			//System.out.println("User Not Authenticated.");
			throw new BadCredentialsException("Authentication failed");
		}


	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
