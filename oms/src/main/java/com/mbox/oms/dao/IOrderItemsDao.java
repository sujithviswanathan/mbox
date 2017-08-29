package com.mbox.oms.dao;

import com.mbox.oms.bean.OrderItem;

import java.util.List;

public interface IOrderItemsDao {
    public List<OrderItem> getByOrderId(int orderId);

}
