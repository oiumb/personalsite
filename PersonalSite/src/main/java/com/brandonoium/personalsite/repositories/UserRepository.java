package com.brandonoium.personalsite.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brandonoium.personalsite.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
