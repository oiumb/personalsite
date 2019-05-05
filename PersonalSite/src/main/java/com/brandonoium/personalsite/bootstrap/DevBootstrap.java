package com.brandonoium.personalsite.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.brandonoium.personalsite.model.User;
import com.brandonoium.personalsite.repositories.CommentRepository;
import com.brandonoium.personalsite.repositories.PostRepository;
import com.brandonoium.personalsite.repositories.UserRepository;
import com.brandonoium.personalsite.security.PasswordEncryptionService;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private UserRepository userRepo;
	private PostRepository postRepo;
	private CommentRepository commentRepo;
	private PasswordEncryptionService encoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		System.out.println("Running bootstrap...");
		
		User tmp = new User(1, "Pyro", encoder.encode("pass"), "ADMIN");
		userRepo.save(tmp);
		
		tmp = new User(2, "B-Rand", encoder.encode("pass"), "ADMIN");
		userRepo.save(tmp);
	}

	public DevBootstrap(UserRepository userRepo, PostRepository postRepo, CommentRepository commentRepo, PasswordEncryptionService encoder) {
		this.userRepo = userRepo;
		this.postRepo = postRepo;
		this.commentRepo = commentRepo;
		this.encoder = encoder;
	}

}
