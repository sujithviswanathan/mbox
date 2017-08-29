package com.mbox.oms.dao;

import com.mbox.oms.bean.Order;

import java.util.List;

public interface IOrderDao {
    public Order getOrderById(int orderId);
    public List<Order> getAllOrders();
}
