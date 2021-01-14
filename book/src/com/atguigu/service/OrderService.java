package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    String createOrder(Cart cart, Integer userId);

    List<Order> orderAll();

    Integer alterOrder(String OrderId, Integer status);

    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    List<Order> OrdersByUserId(Integer userId);

}
