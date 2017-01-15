package com.repository;

import com.model.User;

public interface UserRepository extends DefaultRepository<User> {

	public User findByUsername(String username);

}