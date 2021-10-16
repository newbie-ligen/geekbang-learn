package com.sqz.sharddatabasetable.mapper;

import com.sqz.sharddatabasetable.bo.OrderBo;
import com.sqz.sharddatabasetable.entry.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select order_no as orderNo,user_id as userId,shipping_address_id as shippingAddressId,payment,discount,payment_type paymentType,postage,status from  b_order where id=#{id}")
    public List<OrderEntity> get(@Param("id") String id);


    @Select("select id,order_no as orderNo,user_id as userId,shipping_address_id as shippingAddressId,payment,discount,payment_type as paymentType,postage,status from  b_order where user_id=#{userId}")
    public List<OrderEntity> getByUserId(@Param("userId") Long userId);

    @Insert("insert into b_order(order_no,user_id,shipping_address_id,payment,discount,payment_type,postage,status)  values (#{orderNo},#{userId},#{shippingAddressId},#{payment},#{discount},#{paymentType},#{postage},#{status})")
    public void inser(OrderBo order);

    @Delete("delete FROM b_order where id=#{id}")
    public int delete(@Param("id") String id);

    @Update("update b_order set status = #{status} where id=#{id} and user_id=2")
    public void updateStatus(@Param("id") String id,@Param("status") Integer status);

}
