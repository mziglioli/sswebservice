package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.config.database.PersistenceConfig;
import com.config.security.SecurityConfig;
import com.config.servlet.ServletContextConfig;
import com.exception.ExceptionPackage;

@SpringBootApplication(scanBasePackageClasses = { PersistenceConfig.class, SecurityConfig.class,
		ServletContextConfig.class, ExceptionPackage.class })
public class BuildApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildApplication.class, args);
	}
}