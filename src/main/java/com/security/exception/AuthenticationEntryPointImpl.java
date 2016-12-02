package com.security.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.exception.GlobalExceptionHandler;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	private GlobalExceptionHandler exceptionHandler;

	public AuthenticationEntryPointImpl() {
		exceptionHandler = new GlobalExceptionHandler();
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {
		exceptionHandler.handleAccessDenied(request, response, authenticationException);
	}
}