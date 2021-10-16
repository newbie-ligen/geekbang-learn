package com.sqz.springmultidatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // 创建可读数据源
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read") // application.properteis中对应属性的前缀
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 创建可写数据源
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write") // application.properteis中对应属性的前缀
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

}
