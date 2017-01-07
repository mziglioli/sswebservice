package com.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.model.User;
import com.model.enuns.Authorities;
import com.model.enuns.Status;
import com.repository.UserRepository;

@Service
public class UserService extends ServiceDefault<User, UserRepository> {

	public User findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	public User findUserById(String id) {
		if (id != null && isAllowedPermission(Long.valueOf(id))) {
			return getRepository().findOne(Long.valueOf(id));
		}
		return null;
	}

	public boolean isAllowedPermission(Long id) {
		User user = getAuthenticatedUser();
		if (user != null && user.getId() == id) {
			return true;
		}
		return false;
	}

	public User getAuthenticatedUser() throws AccessDeniedException{
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			if (context != null) {
				Authentication auth = context.getAuthentication();
				if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
					return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
				}
			}
			return null;
		} catch (Exception e) {
			throw new AccessDeniedException("");
		}
	}

	@Override
	protected void beforeInsert(User entity) {
		super.beforeInsert(entity);
		// on save without password, create password after email sent.
		// entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
		Collection<Authorities> roles = new HashSet<>();
		roles.add(Authorities.USER);
		entity.setRoles(roles);
		entity.setStatus(Status.UNACTIVE);
	}
}