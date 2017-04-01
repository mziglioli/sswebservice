package com.config.database;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.modelsecond.Test1;
import com.repositorysecond.Test1Repository;
import com.util.StaticDB;

@Configuration
@EnableJpaRepositories(
		basePackageClasses = { Test1Repository.class }, 
		entityManagerFactoryRef = "entityManagerFactorySecond", 
		transactionManagerRef = "transactionManagerSecond")
public class PersistenceConfigSecond {

	@Bean
	public DataSource dataSourceSecond() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(StaticDB.DB_DRIVER);
		dataSource.setUrl(StaticDB.DB_URL_SECOND);
		dataSource.setUsername(StaticDB.DB_USERNAME);
		dataSource.setPassword(StaticDB.DB_PASSWORD);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManagerSecond() {
		EntityManagerFactory factory = entityManagerFactorySecond().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslatorSecond() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecond() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);

		Properties jpaProperties = new Properties();
		jpaProperties.put(StaticDB.DB_DDL, StaticDB.DB_DDL_VALUE);
		jpaProperties.put(StaticDB.DB_DIALECT, StaticDB.DB_DIALECT_VALUE);
		jpaProperties.put(StaticDB.DB_SHOW_SQL, StaticDB.DB_SHOW_SQL_VALUE);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSourceSecond());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(Test1.class.getPackage().getName());
		factory.setJpaProperties(jpaProperties);

		return factory;
	}
}