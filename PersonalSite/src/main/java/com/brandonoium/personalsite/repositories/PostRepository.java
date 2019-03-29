package com.brandonoium.personalsite.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brandonoium.personalsite.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
