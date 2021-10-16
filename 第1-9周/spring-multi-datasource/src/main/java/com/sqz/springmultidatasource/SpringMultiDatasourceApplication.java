package com.sqz.springmultidatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sqz.springmultidatasource.mapper")
public class SpringMultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMultiDatasourceApplication.class, args);
    }

}
