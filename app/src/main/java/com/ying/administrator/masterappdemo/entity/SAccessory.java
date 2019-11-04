package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class SAccessory implements Serializable {


    /**
     * OrderID : 123
     * AccessorySequency : 0
     * OrderAccessoryStr : {"OrderAccessory":[{"FAccessoryID":"1","FAccessoryName":"PC管","Quantity":"2","Price":"1","DiscountPrice":"1"},{"FAccessoryID":"1","FAccessoryName":"PC管","Quantity":"2","Price":"1","DiscountPrice":"1"}]}
     */

    private String OrderID;
    private String Money;
    private String SizeID;
    private String AccessorySequency;
    private String OrderAccessoryStr;
    private String OrderServiceStr;
    private String IsAorS;

    public String getSizeID() {
        return SizeID;
    }

    public void setSizeID(String sizeID) {
        SizeID = sizeID;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public String getIsAorS() {
        return IsAorS;
    }

    public void setIsAorS(String isAorS) {
        IsAorS = isAorS;
    }

    public String getOrderServiceStr() {
        return OrderServiceStr;
    }

    public void setOrderServiceStr(String orderServiceStr) {
        OrderServiceStr = orderServiceStr;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getAccessorySequency() {
        return AccessorySequency;
    }

    public void setAccessorySequency(String AccessorySequency) {
        this.AccessorySequency = AccessorySequency;
    }

    public String getOrderAccessoryStr() {
        return OrderAccessoryStr;
    }

    public void setOrderAccessoryStr(String OrderAccessoryStr) {
        this.OrderAccessoryStr = OrderAccessoryStr;
    }
}
