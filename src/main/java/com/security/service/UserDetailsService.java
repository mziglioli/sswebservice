package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.model.User;
import com.repository.UserRepository;
import com.util.StaticValue;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private final AccountStatusUserDetailsChecker detailsChecker;

	public UserDetailsService() {
		this.detailsChecker = new AccountStatusUserDetailsChecker();
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(StaticValue.EXCEPTION_USER_NOT_FOUND);
		}
		detailsChecker.check(user);
		return user;
	}
}