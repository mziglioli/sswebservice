package com.conf;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.config.security.SecurityConfig;
import com.config.servlet.ServletContextConfig;
import com.exception.ExceptionPackage;

@Configuration
@ComponentScan(basePackageClasses = { SecurityConfig.class, ServletContextConfig.class, ExceptionPackage.class })
@EnableTransactionManagement
@Transactional
public class TestConfiguration {
	@Bean
	public TestRestTemplate testRestTemplate() {
		return new TestRestTemplate();
	}
}