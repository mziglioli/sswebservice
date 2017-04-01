package com.security.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.model.User;
import com.security.token.TokenHandler;
import com.security.user.UserAuthentication;
import com.util.StaticValue;

@Service
public class TokenAuthenticationService {

	@Autowired
	private TokenHandler tokenHandler;

	public void addAuthentication(HttpServletResponse response, String username) {
		String jwt = tokenHandler.createTokenForUser(username);

		Cookie cookie = new Cookie(StaticValue.COOKIE_AUTH_NAME, jwt);
		cookie.setPath(StaticValue.COOKIE_PATH);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(StaticValue.COOKIE_TIME);
		response.addCookie(cookie);
		response.addHeader(StaticValue.COOKIE_AUTH_NAME, jwt);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, StaticValue.COOKIE_AUTH_NAME);
		if (cookie != null) {
			String token = cookie.getValue();
			if (token != null) {
				final User user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return new UserAuthentication(user);
				}
			}
		}
		return null;
	}
}