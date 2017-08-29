package com.mbox.oms.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("ItemId")
    protected int Id;
    protected double itemPrice;
    protected String itemName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getId() != item.getId()) return false;
        if (Double.compare(item.getItemPrice(), getItemPrice()) != 0) return false;
        return getItemName() != null ? getItemName().equals(item.getItemName()) : item.getItemName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        temp = Double.doubleToLongBits(getItemPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getItemName() != null ? getItemName().hashCode() : 0);
        return result;
    }
}
