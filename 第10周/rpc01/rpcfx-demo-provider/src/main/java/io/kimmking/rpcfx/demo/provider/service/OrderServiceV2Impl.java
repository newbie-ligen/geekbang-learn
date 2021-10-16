package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.provider.anno.DubboService;

@DubboService(version = "0.0.2",group = "g2")
public class OrderServiceV2Impl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "sunqingzhong" + System.currentTimeMillis(), 9.9f);
    }
}
