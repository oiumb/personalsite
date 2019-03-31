package com.brandonoium.personalsite.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.brandonoium.personalsite.model.Post;
import com.brandonoium.personalsite.repositories.PostRepository;
import com.brandonoium.personalsite.resourceassemblers.PostResourceAssembler;

@RestController
public class PostController {
	
	private PostRepository postRepo;
	private PostResourceAssembler assembler;
	
	public PostController(PostRepository postRepo, PostResourceAssembler assembler) {
		super();
		this.postRepo = postRepo;
		this.assembler = assembler;
	}
	
	
	@GetMapping("/posts")
	public Resources<Resource<Post>> all() {
		
		List<Resource<Post>> posts = new ArrayList<Resource<Post>>();
		
		for(Post p : postRepo.findAll()) {
			posts.add(assembler.toResource(p));
		}
		
		return new Resources<Resource<Post>>(posts);
	}
	
	@GetMapping("/posts/{id}")
	public Resource<Post> one(@PathVariable long id) {
		
		Post p = postRepo.findById(id).get();
		
		return assembler.toResource(p);
	}
}
