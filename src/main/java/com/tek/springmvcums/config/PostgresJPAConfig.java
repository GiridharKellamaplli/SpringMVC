package com.tek.springmvcums.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("production")
@PropertySource("classpath:application.properties")
@Configuration
public class PostgresJPAConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		System.out.println("Spring Config dataSource() called");
		DriverManagerDataSource dataSource = new DriverManagerDataSource(environment.getProperty("postgres.jdbc.url"),
				environment.getProperty("postgres.jdbc.username"), environment.getProperty("postgres.jdbc.password"));
		dataSource.setDriverClassName(environment.getProperty("postgres.jdbc.driverClassName"));
		return dataSource;
	}
	
}
