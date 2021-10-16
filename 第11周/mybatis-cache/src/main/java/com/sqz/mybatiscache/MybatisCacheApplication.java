package com.sqz.mybatiscache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(" com.sqz.mybatiscache.mapper")
public class MybatisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisCacheApplication.class, args);
    }

}
