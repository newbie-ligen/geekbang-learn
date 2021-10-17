package io.kimmking.rpcfx.demo.provider.listener;

import com.google.gson.Gson;
import io.kimmking.rpcfx.api.InterfaceDes;
import io.kimmking.rpcfx.demo.provider.anno.DubboService;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.UUID;

@Component
public class InitBeanListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    ZkClient zkClient;

    String path = "/dubbo";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        zkClient = new ZkClient("82.156.230.125:2181");
        zkClient.addAuthInfo("digest", "root:$UNQZ123:".getBytes());
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(DubboService.class);
        Arrays.stream(beanNames).forEach(beanName->{
            Object bean = applicationContext.getBean(beanName);
            DubboService annotation = bean.getClass().getAnnotation(DubboService.class);
            String version = annotation.version();
            String group = annotation.group();
            String name = bean.getClass().getInterfaces()[0].getName();
            String url = "http://localhost:8080";
            InterfaceDes interfaceDes = new InterfaceDes(url,name,version, group);
            String desc = new Gson().toJson(interfaceDes);
            boolean exists = zkClient.exists(path+"/"+name);
            if (!exists){
                zkClient.createPersistent(path+"/"+name,true);
                //注册监听
                zkClient.subscribeDataChanges(path+"/"+name, new IZkDataListener() {
                    @Override
                    public void handleDataChange(String path, Object data) throws
                            Exception {
                        System.out.println(path+"该节点内容被更新，更新后的内容"+data);
                    }
                    @Override
                    public void handleDataDeleted(String s) throws Exception {
                        System.out.println(s+" 该节点被删除");
                    }
                });
            }
            String b = UUID.randomUUID().toString().replace("-", "");
            zkClient.createEphemeral(path+"/"+name+"/"+b, desc);
        });
    }

    @PreDestroy
    public void destory() {
        zkClient.delete(path);
        zkClient.close();
    }
}
