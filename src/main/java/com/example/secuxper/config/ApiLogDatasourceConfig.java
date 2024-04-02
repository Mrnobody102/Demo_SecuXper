package com.example.secuxper.config;

import javax.sql.DataSource;

import com.example.secuxper.apilog.ApiLog;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.secuxper.apilog", entityManagerFactoryRef = "apilogEntityManagerFactory", transactionManagerRef = "apilogTransactionManager")
public class ApiLogDatasourceConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.apilog")
	public DataSourceProperties serverDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.apilog.configuration")
	public DataSource apilogDataSource() {
		return serverDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "apilogEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean apilogEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(apilogDataSource()).packages(ApiLog.class).build();
	}

	@Bean(name = "apilogTransactionManager")
	public PlatformTransactionManager apilogTransactionManager(
			final @Qualifier("apilogEntityManagerFactory") LocalContainerEntityManagerFactoryBean apilogEntityManagerFactory) {
		return new JpaTransactionManager(apilogEntityManagerFactory.getObject());
	}

	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);
		return new EntityManagerFactoryBuilder(vendorAdapter, new HashMap<>(), null);
	}

}