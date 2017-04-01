package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.config.database.PersistenceConfig;
import com.config.security.SecurityConfig;
import com.config.servlet.ServletContextConfig;
import com.exception.ExceptionPackage;

@SpringBootApplication(
		scanBasePackageClasses = { PersistenceConfig.class, SecurityConfig.class, ServletContextConfig.class, ExceptionPackage.class }, 
		exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
@EnableCaching
public class BuildApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildApplication.class, args);
	}
}