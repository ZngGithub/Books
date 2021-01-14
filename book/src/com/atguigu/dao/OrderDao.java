package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单方法
     * @return
     */
    int saveOrder(Order order);

    /**
     * 查询所有订单
     */
    List<Order> queryOrder();

    /**
     * 修改订单的状态
     *
     * @param orderId
     * @param status
     * @return
     */
    Integer changeOrderStatus(String orderId, Integer status);

    /**
     * 通过用户id查询订单信息
     * @param userId
     * @return
     */
    List<Order> OrdersByUserId(Integer userId);
}
