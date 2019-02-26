package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class SAccessory implements Serializable {


    /**
     * OrderID : 123
     * AccessorySequency : 0
     * OrderAccessoryStr : {"OrderAccessory":[{"FAccessoryID":"1","FAccessoryName":"PC管","Quantity":"2","Price":"1","DiscountPrice":"1"},{"FAccessoryID":"1","FAccessoryName":"PC管","Quantity":"2","Price":"1","DiscountPrice":"1"}]}
     */

    private String OrderID;
    private String AccessorySequency;
    private String OrderAccessoryStr;

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
