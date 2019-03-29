package com.brandonoium.personalsite.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brandonoium.personalsite.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
