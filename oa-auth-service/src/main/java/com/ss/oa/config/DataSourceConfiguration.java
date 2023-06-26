package com.ss.oa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean; 
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

public class DataSourceConfiguration {

	
//	@Value("${appdb.jdbc.driverClassName}")     private String driverClassName;
//	@Value("${appdb.jdbc.url}")                 private String url;
//	@Value("${appdb.jdbc.username}")             private String username;
//	@Value("${appdb.jdbc.password}")             private String password;
	
	@Value("${spring.datasource.driverClassName}")     private String driverClassName;
	@Value("${spring.datasource.url}")                 private String url;
	@Value("${spring.datasource.username}")             private String username;
	@Value("${spring.datasource.password}")             private String password;
      
	@Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);         
        return ds;
    }
	
	@Bean
	public JdbcOperations jdbcOperations() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}


}
