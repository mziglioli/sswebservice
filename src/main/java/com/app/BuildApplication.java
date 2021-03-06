package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.repository.InMemoryMetricRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.config.database.PersistenceConfig;
import com.config.database.PersistenceConfigSecond;
import com.config.security.SecurityConfig;
import com.config.servlet.HazelcastConfiguration;
import com.config.servlet.ServletContextConfig;
import com.config.servlet.SwaggerConfig;
import com.exception.ExceptionPackage;

@SpringBootApplication(
		scanBasePackageClasses = {HazelcastConfiguration.class, PersistenceConfig.class, PersistenceConfigSecond.class, SecurityConfig.class, ServletContextConfig.class, SwaggerConfig.class, ExceptionPackage.class }, 
		exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class BuildApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildApplication.class, args);
	}
	
	@Bean
	public InMemoryMetricRepository inMemoryMetricRepository(){
		return new InMemoryMetricRepository();
	}
}