package com.security.voter;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.util.StaticValue;

public class UserRoleVoter extends RoleVoter {

	public UserRoleVoter() {
		setRolePrefix(StaticValue.ROLE_PREFIX);
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		if (authentication == null) {
			return ACCESS_DENIED;
		}
		final AtomicInteger result = new AtomicInteger(ACCESS_ABSTAIN);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		attributes.forEach(a -> {
			if (this.supports(a)) {
				result.set(ACCESS_DENIED);
				authorities.forEach(auth -> {
					if (auth.getAuthority().equals(StaticValue.ROLE_ADMIN)
							|| a.getAttribute().equals(auth.getAuthority())) {
						result.set(ACCESS_GRANTED);
					}
				});
			}
		});
		return result.get();
	}
}
