package com.brandonoium.personalsite.resourceassemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Controller;

import com.brandonoium.personalsite.model.Post;

@Controller
public class PostResourceAssembler implements ResourceAssembler<Post, Resource<Post>>{

	@Override
	public Resource<Post> toResource(Post entity) {
		return new Resource<Post>(entity);
	}

}
