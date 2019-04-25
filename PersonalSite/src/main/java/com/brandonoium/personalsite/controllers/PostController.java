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

import com.brandonoium.personalsite.exceptions.PostNotFoundException;
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
	
	
	@GetMapping(value="/posts", produces="application/json")
	public Resources<Resource<Post>> all() {
		
		List<Resource<Post>> posts = new ArrayList<Resource<Post>>();
		
		for(Post p : postRepo.findAll()) {
			posts.add(assembler.toResource(p));
		}
		
		return new Resources<Resource<Post>>(posts);
	}
	
	@PostMapping(value="/posts", produces="application/json")
	Resource<Post> newPost(@RequestBody Post newPost) {
		return assembler.toResource(postRepo.save(newPost));
	}
	
	@GetMapping(value="/posts/{id}", produces="application/json")
	public Resource<Post> one(@PathVariable long id) {
		
		Post p = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		
		return assembler.toResource(p);
	}
	
	@DeleteMapping(value="/posts/{id}")
	void deletePost(@PathVariable long id) {
		postRepo.deleteById(id);
	}
}
