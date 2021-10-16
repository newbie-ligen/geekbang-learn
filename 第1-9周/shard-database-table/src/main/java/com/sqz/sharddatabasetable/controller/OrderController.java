package com.sqz.sharddatabasetable.controller;


import com.sqz.sharddatabasetable.bo.OrderBo;
import com.sqz.sharddatabasetable.entry.OrderEntity;
import com.sqz.sharddatabasetable.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/order/add")
    public  String insert(@RequestBody  OrderBo orderBo) {
        orderService.insert(orderBo);
        return "success";
    }

    @RequestMapping("/get/order/{userId}")
    public List<OrderEntity> get(@PathVariable("userId") Long userId) {
        return  orderService.get(userId);
    }

    @RequestMapping("/order/del/{orderId}")
    public  String del(@PathVariable("orderId") String orderId) {
        orderService.delete(orderId);
        return "success";
    }

    @RequestMapping("/order/edit")
    public  String edit( String orderId,Integer status) {
        orderService.update(orderId,status);
        return "success";
    }

}
