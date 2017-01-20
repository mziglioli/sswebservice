package com.util;

public class StaticURL {

	public static final String CLIENTS = "http://localhost:4200";

	public static final String SERVER = "http://127.0.0.1:8080";
	public static final String ROOT = "/";

	// favicon
	public static final String FAVICON_ICO = "/favicon.ico";

	// PUBLIC
	public static final String PUBLIC_ALL = "/public/**";
	public static final String PUBLIC_ROOT = "/public/";
	public static final String PUBLIC = "/public";
	public static final String LOGIN = "/public/login";
	public static final String LOGOUT = "/public/logout";
	public static final String SUCCESS_LOGOUT = "/successlogout";
	public static final String PUBLIC_SUCCESS_LOGOUT = "/public/successlogout";

	// CRUD
	public static final String FIND_BY_ID = "/{id}";
	public static final String FIND_ALL = "/";
	public static final String FIND_BY_PAGE = "/page";
	public static final String SAVE = "/";
	public static final String UPDATE = "/{id}";
	public static final String DELETE = "/{id}";

	public static final String EDIT = "/{id}";
	public static final String NEW = "/new";

	// SPECIFIC
	public static final String LOGGED = "/logged";
	public static final String SIGNUP = "/signUp";
	public static final String USER = "/user";
	public static final String TEST = "/test";
	public static final String ADMIN = "/admin";
	public static final String CATEGORY = "/category";
	public static final String ARTICLE = "/article";

	// TEST

	// URI
	public static final String SIGNUP_TEST = SERVER + "/public/signUp";
}