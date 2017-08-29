package com.mbox.oms.service.impl;

import com.mbox.oms.bean.Item;
import com.mbox.oms.bean.Order;
import com.mbox.oms.bean.OrderItem;
import com.mbox.oms.dao.IItemDao;
import com.mbox.oms.dao.IOrderDao;
import com.mbox.oms.dao.IOrderItemsDao;
import com.mbox.oms.service.IMboxOmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class OmsServiceImpl implements IMboxOmsService {
    @Autowired
    IOrderDao orderDao;
    @Autowired
    IOrderItemsDao orderItemsDao;
    @Autowired
    IItemDao itemDao;

    @Override
    public List<Order> lookupAllOrders() {
        List<Order> orderList = orderDao.getAllOrders();
        for(Order order:orderList){
            List<OrderItem> orderItems =orderItemsDao.getByOrderId(order.getId());
            if(orderItems!=null && !orderItems.isEmpty()) {
                for(OrderItem orderItem:orderItems) {
                    orderItem.setItem(itemDao.getById(orderItem.getItemId()));
                }
                order.getOrderItemsList().addAll(orderItems);
            }
        }

        return orderList;
    }

    @Override
    public Order findOrderById(int orderId) {

        Order order = orderDao.getOrderById(orderId);
       if(order!=null){
           order.getOrderItemsList().addAll(getItemDetails(orderId));
        }
        return order;
    }

    @Override
    public double getTotalCostForOrder(int orderId) {
        Order order = orderDao.getOrderById(orderId);
        double totalCost = 0.0;
        if (order != null) {
            List<OrderItem> orderItems = getItemDetails(orderId);
            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    Item item = orderItem.getItem();
                    totalCost = totalCost +(orderItem.getItemCount()*item.getItemPrice());
                }
            }

        }
        return totalCost;
    }
    private List<OrderItem> getItemDetails(int orderId){
        List<OrderItem> orderItems =orderItemsDao.getByOrderId(orderId);
        if(orderItems!=null && !orderItems.isEmpty()) {
            for(OrderItem orderItem:orderItems) {
                orderItem.setItem(itemDao.getById(orderItem.getItemId()));
            }

        }
        return orderItems;
    }

}
