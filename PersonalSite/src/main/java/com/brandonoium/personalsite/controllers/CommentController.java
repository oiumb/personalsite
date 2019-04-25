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

import com.brandonoium.personalsite.exceptions.CommentNotFoundException;
import com.brandonoium.personalsite.model.Comment;
import com.brandonoium.personalsite.repositories.CommentRepository;
import com.brandonoium.personalsite.resourceassemblers.CommentResourceAssembler;

@RestController
public class CommentController {
	
	private CommentRepository commentRepo;
	private CommentResourceAssembler assembler;
	
	public CommentController(CommentRepository commentRepo, CommentResourceAssembler assembler) {
		super();
		this.commentRepo = commentRepo;
		this.assembler = assembler;
	}
	
	
	@GetMapping(value="/comments", produces="application/json")
	public Resources<Resource<Comment>> all()
	{
		List<Resource<Comment>> comments = new ArrayList<Resource<Comment>>();
		
		for(Comment c : commentRepo.findAll()) {
			comments.add(assembler.toResource(c));
		}
		
		return new Resources<Resource<Comment>>(comments);
	}
	
	@PostMapping(value="/comments", produces="application/json")
	Resource<Comment> newComment(@RequestBody Comment newComment) {
		return assembler.toResource(commentRepo.save(newComment));
	}
	
	@GetMapping(value="/comments/{id}", produces="application/json")
	public Resource<Comment> one(@PathVariable long id) {
		
		Comment c = commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
		
		return assembler.toResource(c);
	}
	
	@DeleteMapping(value="/comments/{id}")
	void deleteComment(@PathVariable long id) {
		commentRepo.deleteById(id);
	}
}
