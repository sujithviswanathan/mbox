package com.mbox.oms.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderItem {

    @JsonProperty("orderItemId")
    protected int id;
    protected int orderId;
    protected int itemId;
    protected int itemCount;
    protected Item item;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (orderId != orderItem.orderId) return false;
        if (itemId != orderItem.itemId) return false;
        if (itemCount != orderItem.itemCount) return false;
        return item.equals(orderItem.item);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + itemId;
        result = 31 * result + itemCount;
        result = 31 * result + item.hashCode();
        return result;
    }
}
