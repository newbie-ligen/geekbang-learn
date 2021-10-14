package com.sqz.stater.cofig;

import com.sqz.stater.bean.Klass;
import com.sqz.stater.bean.School;
import com.sqz.stater.bean.Student;
import com.sqz.stater.bean.interfaces.ISchool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {


    @Bean(name="student100")
    public Student student() {
        return new Student();
    }

    @Bean
    public Klass klass() {
        return new Klass();
    }

    @Bean
    public ISchool school() {
        return new School();
    }
}
