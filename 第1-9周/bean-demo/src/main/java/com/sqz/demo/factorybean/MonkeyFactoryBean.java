package com.sqz.demo.factorybean;

import com.sqz.demo.pojo.Monkey;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MonkeyFactoryBean implements FactoryBean<Monkey> {

    public Monkey getObject() throws Exception {
        return new Monkey();
    }

    public Class<?> getObjectType() {
        return Monkey.class;
    }
}
