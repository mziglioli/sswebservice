package com.util;

public class StaticDB {
	// DB configuration
	public static final String DB_NAME = "sswebservice";
	public static final String DB_NAME_TEST = "sswebservicetest";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "marcelo12";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//	public static final String DB_URL = "jdbc:mysql:replication://localhost:3306,localhost:${mysql.slave.port}/" + DB_NAME + "?useUnicode=true";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true";
	public static final String DB_URL_SECOND = "jdbc:mysql://localhost:3306/ccs?useUnicode=true";
	public static final String DB_URL_TEST = "jdbc:mysql://localhost:3306/" + DB_NAME_TEST + "?useUnicode=true";

	// hibernate confi
	public static final String DB_DDL = "hibernate.hbm2ddl.auto";
	public static final String DB_DDL_VALUE = "update";
	public static final String DB_DIALECT = "hibernate.dialect";
	public static final String DB_DIALECT_VALUE = "org.hibernate.dialect.MySQL5Dialect";
	public static final String DB_SHOW_SQL = "show_sql";
	public static final String DB_SHOW_SQL_VALUE = "true";

	// TABLES NAME
	public static final String TABLE_ARTICLE = "helparticles";
	public static final String TABLE_CATEGORY = "helparticle_category";
	public static final String TABLE_ARTICLE_CATEGORY = "helparticle_category_category";
	public static final String TABLE_USER = "user";
	
	public static final String TABLE_ACTION = "action";
	public static final String TABLE_ACTION_PARM = "action_parm";
	public static final String TABLE_ACTION_PARM_PROMPT = "action_parm_prompt";
	public static final String TABLE_BUTTON = "button";
	public static final String TABLE_CHAT_LOG = "chat_bot";
	public static final String TABLE_ENTITY = "entity";
	public static final String TABLE_ENTITY_SYNONYM = "entity_synonym";
	public static final String TABLE_ENTITY_TEXT = "entity_text";
	public static final String TABLE_INTENT = "intent";
	public static final String TABLE_INTENT_CONTEXT = "intent_context";
	public static final String TABLE_NODE = "node";
	public static final String TABLE_NODE_CONNECTION = "node_connection";
	public static final String TABLE_QUICK_BUTTON = "quick_button";
}