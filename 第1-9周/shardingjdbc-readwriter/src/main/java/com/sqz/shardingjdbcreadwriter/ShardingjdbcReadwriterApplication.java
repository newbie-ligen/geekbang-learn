package com.sqz.shardingjdbcreadwriter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.sqz.shardingjdbcreadwriter.mapper")

@SpringBootApplication
public class ShardingjdbcReadwriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingjdbcReadwriterApplication.class, args);
    }

}
