package com.sqz.demo;

import com.sqz.demo.config.AppConfig;
import com.sqz.demo.pojo.Camel;
import com.sqz.demo.pojo.Cat;
import com.sqz.demo.pojo.Hourse;
import com.sqz.demo.pojo.User;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        anno();
        xml();
    }

    public static void xml() {
        String xmlPath = "classpath:application.xml";
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(xmlPath);
        User user = applicationContext.getBean(User.class);

    }

    public static void anno() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        RootBeanDefinition hourse = new RootBeanDefinition(Hourse.class);
        applicationContext.registerBeanDefinition("hourse",hourse);
        Camel camel = applicationContext.getBean(Camel.class);
        applicationContext.close();
    }
}
