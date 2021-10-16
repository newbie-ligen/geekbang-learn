package io.kimmking.rpcfx.demo.provider.resolver;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.demo.provider.anno.DubboService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass,String group,String version) {
        //final Object bean = this.applicationContext.getBean(serviceClass);
       // applicationContext.get
        Object bean = null;
        try {
            Class<?> aClass = Class.forName(serviceClass);
            String[] beanNamesForType = applicationContext.getBeanNamesForType(aClass);

            for(String beanName : beanNamesForType) {
                Object obj = applicationContext.getBean(beanName);
                DubboService annotation = obj.getClass().getAnnotation(DubboService.class);
                String pgroup = annotation.group();
                String pversion = annotation.version();

                if(StringUtils.equals(group,pgroup) && StringUtils.equals(version,pversion)) {
                    bean = obj;
                }
             }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(bean==null) {
            throw new RuntimeException("接口不存在");
        }
        return bean;
    }
}
