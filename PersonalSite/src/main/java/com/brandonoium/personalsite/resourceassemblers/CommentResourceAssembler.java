package com.brandonoium.personalsite.resourceassemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Controller;

import com.brandonoium.personalsite.model.Comment;

@Controller
public class CommentResourceAssembler implements ResourceAssembler<Comment, Resource<Comment>> {

	@Override
	public Resource<Comment> toResource(Comment entity) {
		return new Resource<Comment>(entity);
	}

}
