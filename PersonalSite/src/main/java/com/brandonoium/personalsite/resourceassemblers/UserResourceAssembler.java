package com.brandonoium.personalsite.resourceassemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Controller;

import com.brandonoium.personalsite.model.User;

@Controller
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

	@Override
	public Resource<User> toResource(User entity) {
		return new Resource<User>(entity);
	}

}
