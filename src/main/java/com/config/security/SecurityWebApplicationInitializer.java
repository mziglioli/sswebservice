package com.config.security;

import javax.servlet.ServletContext;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
	}
	
	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		// TODO Auto-generated method stub
		super.afterSpringSecurityFilterChain(servletContext);
	}
}
