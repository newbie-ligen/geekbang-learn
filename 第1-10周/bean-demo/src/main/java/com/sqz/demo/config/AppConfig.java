package com.sqz.demo.config;

import com.sqz.demo.Application;
import com.sqz.demo.imports.MyImportBeanDefinitionRegistrar;
import com.sqz.demo.imports.MyImportSelector;
import com.sqz.demo.pojo.Cow;
import com.sqz.demo.pojo.Tiger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.sqz.demo.factorybean","com.sqz.demo.beanfactory"})
@Import({MyImportSelector.class, MyImportBeanDefinitionRegistrar.class, Tiger.class})
@ImportResource("classpath:application.xml")
public class AppConfig {

    @Bean
    public Cow Cow() {
        return  new Cow();
    }


}
