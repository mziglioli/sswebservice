package com.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.form.LoginForm;
import com.security.service.TokenAuthenticationService;
import com.security.service.UserDetailsService;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsService userDetailsService;
	
	private GlobalExceptionHandler exceptionHandler;

	public LoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService, UserDetailsService userDetailsService, AuthenticationManager authManager, GlobalExceptionHandler exceptionHandler) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.exceptionHandler = exceptionHandler;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final LoginForm form = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		tokenAuthenticationService.addAuthentication(response, authResult.getName());
		CsrfHeaderFilter.addCsrfCookie(response);
		response.setStatus(HttpStatus.OK.value());
		ObjectMapper mapper = new ObjectMapper();
		response.getOutputStream().println(mapper.writeValueAsString(userDetailsService.loadUserByUsername(authResult.getName())));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		exceptionHandler.handleAccessDenied(request, response, new AccessDeniedException(""));
	}
}