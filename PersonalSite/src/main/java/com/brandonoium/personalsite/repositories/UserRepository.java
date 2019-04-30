package com.brandonoium.personalsite.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.brandonoium.personalsite.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username=:username")
    public Iterable<User> findByUsername(@Param("username") String Username);
}
