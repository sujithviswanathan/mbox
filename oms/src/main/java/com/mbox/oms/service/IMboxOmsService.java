package com.mbox.oms.service;

import com.mbox.oms.bean.Order;

import java.util.List;

public interface IMboxOmsService {

    public List<Order>lookupAllOrders();
    public Order findOrderById(int orderId);
    public double getTotalCostForOrder(int orderId);
}
