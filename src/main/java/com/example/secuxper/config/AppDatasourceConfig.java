package com.example.secuxper.config;

import com.example.secuxper.data.User;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.secuxper.data", entityManagerFactoryRef = "dataEntityManagerFactory", transactionManagerRef = "dataTransactionManager")
public class AppDatasourceConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.data")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.data.configuration")
	public DataSource dataSource() {
		return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "dataEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean dataEntityManagerfactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()).packages(User.class).build();
	}

	@Bean(name = "dataTransactionManager")
	public PlatformTransactionManager dataTransactionManager(
			final @Qualifier("dataEntityManagerFactory") LocalContainerEntityManagerFactoryBean dataEntityManagerFactory) {
		return new JpaTransactionManager(dataEntityManagerFactory.getObject());
	}

}