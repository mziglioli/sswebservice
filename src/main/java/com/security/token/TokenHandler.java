package com.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.User;
import com.security.service.UserDetailsService;
import com.util.StaticValue;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandler {

	@Autowired
	private UserDetailsService userDetailsService;

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(StaticValue.JWTS_SECRET).parseClaimsJws(token).getBody()
				.getSubject();
		return userDetailsService.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, StaticValue.JWTS_SECRET)
				.compact();
	}
}
