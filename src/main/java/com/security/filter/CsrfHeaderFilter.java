package com.security.filter;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.util.StaticURL;
import com.util.StaticValue;

public class CsrfHeaderFilter extends OncePerRequestFilter {

	private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	private final AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Cookie cookie = WebUtils.getCookie(request, StaticValue.COOKIE_XSRF_TOKEN);
//		if (csrfTokenValue == null || cookie == null || !csrfTokenValue.equals(cookie.getValue())) {
		if(cookie == null){
			accessDeniedHandler.handle(request, response, new AccessDeniedException(StaticValue.EXCEPTION_ACCESS_DENIED));
			return;
		}
		addCsrfCookie(response);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return allowedMethods.matcher(request.getMethod()).matches()
				|| request.getServletPath().startsWith(StaticURL.PUBLIC_ROOT);
	}

	public static void addCsrfCookie(HttpServletResponse response) {
		String xsrf = UUID.randomUUID().toString();
		Cookie cookie = new Cookie(StaticValue.COOKIE_XSRF_TOKEN, xsrf);
		cookie.setPath(StaticValue.COOKIE_PATH);
		cookie.setMaxAge(StaticValue.COOKIE_TIME);
		response.addCookie(cookie);
		response.addHeader(StaticValue.COOKIE_XSRF_TOKEN, xsrf);
	}
}