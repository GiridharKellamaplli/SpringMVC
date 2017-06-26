package com.tek.springmvcums.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Profile("dev")
@Configuration
@PropertySource("classpath:application.properties")
public class MysqlJPAConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		System.out.println("MysqlJPAConfig dataSource() called");
		DriverManagerDataSource dataSource = new DriverManagerDataSource(environment.getProperty("mysql.jdbc.url"),
				environment.getProperty("mysql.jdbc.username"), environment.getProperty("mysql.jdbc.password"));
		dataSource.setDriverClassName(environment.getProperty("mysql.jdbc.driverClassName"));
		return dataSource;
	}

}
