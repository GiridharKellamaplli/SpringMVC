package com.tek.springmvcums.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.tek.springmvcums")
@EnableJpaRepositories("com.tek.springmvcums")
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		System.out.println("MysqlJPAConfig jpaVendorAdapter() called");
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		factoryBean.setPackagesToScan("com.tek.springmvcums.model");
		factoryBean.setJpaProperties(jpaProperties());
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	private Properties jpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		jpaProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.jdbc.dialect"));
		return jpaProperties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
