package com.mbox.oms.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Order {

    @JsonProperty("orderId")
    protected int id;
    protected int customerId;
    protected List<OrderItem> orderItemsList;
    protected String placementDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getOrderItemsList() {
        if(orderItemsList ==null){
            orderItemsList = new ArrayList<OrderItem>();
        }
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItem> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public String getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(String placementDate) {
        this.placementDate = placementDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (getCustomerId() != order.getCustomerId()) return false;
        if (!getOrderItemsList().equals(order.getOrderItemsList())) return false;
        return getPlacementDate().equals(order.getPlacementDate());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getCustomerId();
        result = 31 * result + getOrderItemsList().hashCode();
        result = 31 * result + getPlacementDate().hashCode();
        return result;
    }
}
