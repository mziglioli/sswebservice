package com.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.model.EntityJpa;
import com.repository.RepositoryPackage;
import com.util.StaticDB;

@Configuration
@EntityScan(basePackageClasses = { EntityJpa.class })
@EnableJpaRepositories(basePackageClasses = { RepositoryPackage.class })
public class PersistenceConfigTest {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(StaticDB.DB_DRIVER);
		dataSource.setUrl(StaticDB.DB_URL_TEST);
		dataSource.setUsername(StaticDB.DB_USERNAME);
		dataSource.setPassword(StaticDB.DB_PASSWORD);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Autowired
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);

		Properties jpaProperties = new Properties();
		jpaProperties.put(StaticDB.DB_DDL, StaticDB.DB_DDL_VALUE);
		jpaProperties.put(StaticDB.DB_DIALECT, StaticDB.DB_DIALECT_VALUE);
		jpaProperties.put(StaticDB.DB_SHOW_SQL, StaticDB.DB_SHOW_SQL_VALUE);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(EntityJpa.class.getPackage().getName());
		factory.setJpaProperties(jpaProperties);

		return factory;
	}
}