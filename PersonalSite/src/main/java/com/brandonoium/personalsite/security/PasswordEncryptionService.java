package com.brandonoium.personalsite.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PasswordEncryptionService {
	
	private PasswordEncoder encoder;
	
	public PasswordEncryptionService() {
		encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	public String encode(String pw) {
		return encoder.encode(pw);
	}
	
	public boolean matches(String rawPw, String pwHash) {
		return encoder.matches(rawPw, pwHash);
	}
}
