package com.sqz.activemqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class ActivemqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqDemoApplication.class, args);
    }

}
