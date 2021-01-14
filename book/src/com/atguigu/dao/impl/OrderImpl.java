package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

import java.util.List;

public class OrderImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql =  "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    /**
     * 获取全部订单信息
     */
    public List<Order> queryOrder() {
        String sql = "select order_id orderId, create_time createTime, price, status, user_id userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    /**
     * 修改订单状态
     */
    public Integer changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set status=? where order_id = ?";
        return update(sql, status, orderId);
    }

    @Override
    /**
     *通过用户名id查询数据库的值
     */
    public List<Order> OrdersByUserId(Integer userId) {
        String sql = "select order_id orderId, create_time createTime, price, status from t_order where user_id = ?;";
        return queryForList(Order.class, sql, userId);
    }



}
