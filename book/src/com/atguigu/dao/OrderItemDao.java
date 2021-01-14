package com.atguigu.dao;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao{
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
