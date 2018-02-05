package com.ds.db2db.example.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * Created by ds on 11/12/2017.
 */
@Configuration
public class DatasourceConfiguration {
    @Bean

    @ConfigurationProperties(prefix="spring.datasource")
    public javax.sql.DataSource dataSource() throws Exception{

    return DataSourceBuilder.create()
           .build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.targetdatasource")
    public javax.sql.DataSource secondaryDataSource() {
             return DataSourceBuilder.create().build();
    }



}
