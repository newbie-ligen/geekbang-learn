package com.sqz.sharddatabasetable.mapper;

import com.sqz.sharddatabasetable.bo.OrderItemBo;
import com.sqz.sharddatabasetable.entry.OrderEntity;
import com.sqz.sharddatabasetable.entry.OrderItemEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    @Select("SELECT id,user_id as userId,order_no as orderNo,product_id as productId,product_name as productName FROM  order_item where order_no=#{orderNo}")
    public List<OrderItemEntity> get(@Param("orderNo") String orderNo);

    @Insert("INSERT INTO order_item(user_id,order_no,product_id,product_name)  values (#{userId},#{orderNo},#{productId},#{productName})")
    public void inser(OrderItemBo orderItem);

    @Select("delete from order_item where order_no = #{orderNo}")
    public void delete(@Param("orderNo") String orderNo);


}
