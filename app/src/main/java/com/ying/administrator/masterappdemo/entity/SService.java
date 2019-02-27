package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class SService implements Serializable {
    private String OrderID;
    private String OrderServiceStr;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderServiceStr() {
        return OrderServiceStr;
    }

    public void setOrderServiceStr(String orderServiceStr) {
        OrderServiceStr = orderServiceStr;
    }
}
