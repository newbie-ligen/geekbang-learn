package com.sqz.sharddatabasetable.service;


import com.sqz.sharddatabasetable.bo.OrderBo;
import com.sqz.sharddatabasetable.entry.OrderEntity;
import com.sqz.sharddatabasetable.entry.OrderItemEntity;
import com.sqz.sharddatabasetable.mapper.OrderItemMapper;
import com.sqz.sharddatabasetable.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderEntity> get(Long userId) {

        List<OrderEntity> orderEntities = orderMapper.getByUserId(userId);
        if(!CollectionUtils.isEmpty(orderEntities)) {
            orderEntities.forEach(orderEntity -> {
                List<OrderItemEntity> items = orderItemMapper.get(orderEntity.getOrderNo());
                orderEntity.setItems(items);
            });
        }
        return orderEntities;
    }

    public void insert(OrderBo orderBo){
        orderBo.setStatus(1);
        orderMapper.inser(orderBo);
        if(!CollectionUtils.isEmpty(orderBo.getItems())) {
            orderBo.getItems().forEach(orderItemBo -> {
                orderItemMapper.inser(orderItemBo);
            });
        }
    }

    public void update(String id ,Integer status){
        orderMapper.updateStatus(id,status);
    }

    public void delete(String orderId ){

       List<OrderEntity> orderEntities = orderMapper.get(orderId);
       int count = orderMapper.delete(orderId);
       if(count>0) {
           orderItemMapper.delete(orderEntities.get(0).getOrderNo());
       }
    }
}
