package io.kimmking.rpcfx.demo.consumer;

import com.google.gson.Gson;
import io.kimmking.rpcfx.api.*;
import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user
	//


	static String path = "/dubbo";

	public static void main(String[] args) throws Exception {
		ZkClient zkClient = new ZkClient("82.156.230.125:2181");
		zkClient.addAuthInfo("digest", "root:$UNQZ123:".getBytes());
		String name = UserService.class.getName();
		List<String> children = zkClient.getChildren(path+"/"+name);
		int i = new Random().nextInt(children.size());
		String o = zkClient.readData(path+"/"+name+"/"+children.get(i));
		InterfaceDes interfaceDes = new Gson().fromJson(o, InterfaceDes.class);
		UserService userService = Rpcfx.create(UserService.class, interfaceDes.getUrl(),interfaceDes.getGroup(),interfaceDes.getVersion());
		User user = userService.findById(1);
		System.out.println("find user id=1 from server: " + user.getName());

//		OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
//		Order order = orderService.findOrderById(1992129);
//		System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));
//
//		//
//		UserService userService2 = Rpcfx.createFromRegistry(UserService.class, "localhost:2181", new TagRouter(), new RandomLoadBalancer(), new CuicuiFilter());

//		SpringApplication.run(RpcfxClientApplication.class, args);
	}

	private static class TagRouter implements Router {
		@Override
		public List<String> route(List<String> urls) {
			return urls;
		}
	}

	private static class RandomLoadBalancer implements LoadBalancer {
		@Override
		public String select(List<String> urls) {
			return urls.get(0);
		}
	}

	@Slf4j
	private static class CuicuiFilter implements Filter {
		@Override
		public boolean filter(RpcfxRequest request) {
			log.info("filter {} -> {}", this.getClass().getName(), request.toString());
			return true;
		}
	}
}



