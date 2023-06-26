package com.ss.oa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class DataSourceConfiguration {

	
	@Value("${appdb.jdbc.driverClassName}")     private String driverClassName;
	@Value("${appdb.jdbc.url}")                 private String url;
	@Value("${appdb.jdbc.username}")             private String username;
	@Value("${appdb.jdbc.password}")             private String password;
	

	@Value("${mdmsdb.jdbc.driverClassName}")     private String mdmsDriverClassName;
	@Value("${mdmsdb.jdbc.url}")                 private String mdmsUrl;
	@Value("${mdmsdb.jdbc.username}")             private String mdmsUsername;
	@Value("${mdmsdb.jdbc.password}")             private String mdmsPassword;
	
	@Value("${samastdb.jdbc.driverClassName}")     private String samastDriverClassName;
	@Value("${samastdb.jdbc.url}")                 private String samastUrl;
	@Value("${samastdb.jdbc.username}")             private String samastUsername;
	@Value("${samastdb.jdbc.password}")             private String samastPassword;
	
	@Value("${zendb.jdbc.driverClassName}")     private String zenDriverClassName;
	@Value("${zendb.jdbc.url}")                 private String zenUrl;
	@Value("${zendb.jdbc.username}")             private String zenUsername;
	@Value("${zendb.jdbc.password}")             private String zenPassword;
	
	
	@Primary      
	@Bean(name = "appDs")
    public DataSource dataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);         
        return ds;
    }
	
	@Primary
	@Bean(name = "appJdbc")
	public JdbcOperations jdbcOperations() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
  
	@Bean(name = "mdmsDs")
    public DataSource mdmsDataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(mdmsDriverClassName);
        ds.setUrl(mdmsUrl);
        ds.setUsername(mdmsUsername);
        ds.setPassword(mdmsPassword);         
        return ds;
    }
	
	@Bean(name = "zenJdbc")
	public JdbcOperations zenJdbcOperations() {
		return new JdbcTemplate(zenDataSource());
	}
	
	
	@Bean(name = "zenDs")
    public DataSource zenDataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(zenDriverClassName);
        ds.setUrl(zenUrl);
        ds.setUsername(zenUsername);
        ds.setPassword(zenPassword);         
        return ds;
    }
	
	@Bean(name = "mdmsJdbc")
	public JdbcOperations mdmsJdbcOperations() {
		return new JdbcTemplate(mdmsDataSource());
	}

	@Bean(name = "samastDs")
    public DataSource samastDataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(samastDriverClassName);
        ds.setUrl(samastUrl);
        ds.setUsername(samastUsername);
        ds.setPassword(samastPassword);         
        return ds;
    }
	
	@Bean(name = "samastJdbc")
	public JdbcOperations samastJdbcOperations() {
		return new JdbcTemplate(samastDataSource());
	}


}
