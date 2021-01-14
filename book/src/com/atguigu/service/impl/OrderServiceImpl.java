package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderImpl;
import com.atguigu.dao.impl.OrderItemImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;
import com.google.gson.internal.$Gson$Preconditions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderImpl();
    OrderItemDao orderItemDao = new OrderItemImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号
        String orderId = System.currentTimeMillis()+""+userId;
        //购物车商品转换成订单商品
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            //每个购物车商品转换订单商品
            OrderItem orderItem = new OrderItem(null, entry.getValue().getName(), entry.getValue().getCount(), entry.getValue().getPrice(), entry.getValue().getTotalPrice(), orderId);
            //每个商品保存到数据库
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBookDao(book);
        }
        cart.clear();
       
        return orderId;
    }

    @Override
    public List<Order> orderAll() {
        //全部查询
        return orderDao.queryOrder();
    }

    @Override
    public Integer alterOrder(String OrderId, Integer status) {
        //修改状态
        return orderDao.changeOrderStatus(OrderId, status);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        //通过id查询商品信息
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> OrdersByUserId(Integer userId) {
        //通过用户id查询订单
        return orderDao.OrdersByUserId(userId);
    }


}
