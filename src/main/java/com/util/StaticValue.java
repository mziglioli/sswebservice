package com.util;

public class StaticValue {

	// server configuration
	public static final String SERVER = "http://localhost";

	// ROLES
	public static final String ROLE_PREFIX = "ROLE_";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ANONIMOUS = "ROLE_ANONIMOUS";
	public static final String HAS_ROLE_USER = "hasRole('" + ROLE_USER + "')";
	public static final String HAS_ROLE_ADMIN = "hasRole('" + ROLE_ADMIN + "')";

	// jwt
	public static final String JWTS_SECRET = "MY_SECRET_123";

	// COOKIE
	public static final String COOKIE_XSRF_TOKEN = "XSRF-TOKEN";
	public static final String COOKIE_X_XSRF_TOKEN = "X-XSRF-TOKEN";
	//seconds, minutes, hs, days
	public static final int COOKIE_TIME = (60 * 60 * 24 * 1);
	public static final String COOKIE_PATH = "/";
	public static final String COOKIE_AUTH_NAME = "X-AUTH-TOKEN";
	public static final String COOKIE_USER_ID = "USER-ID";

	public static final String COOKIE_MISSING_NOT_MATCHING = "Missing or non-matching XSRF-TOKEN";

	// exception;
	public static final String EXCEPTION_USER_NOT_ACTIVE = "exception.userNotActive";
	public static final String EXCEPTION_USER_NOT_FOUND = "exception.userNotFound";
	public static final String EXCEPTION_ACCESS_DENIED = "exception.xsfrToken"; // "Missing or non-matching XSRF-TOKEN";

	// log
	public static final String LOG_SEPARATOR = " -> ";

}
