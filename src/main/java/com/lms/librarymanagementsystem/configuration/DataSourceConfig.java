package com.lms.librarymanagementsystem.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${db.url}")
    private String dbUrl ;
    @Value("${db.username}")
    private String dbUsername ;
    @Value("${db.password}")
    private String dbPassword ;
    @Value("${db.driver}")
    private  String dbDriver ;
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriver);
        return dataSource;
    }
    @Bean
    public JdbcTemplate beanJDBCTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
