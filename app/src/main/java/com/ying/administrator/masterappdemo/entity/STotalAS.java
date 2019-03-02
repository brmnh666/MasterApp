package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

/*配件和服务都提交的情况和返件*/
public class STotalAS implements Serializable {
    private String OrderID;
    private String AccessorySequency;
    private String OrderAccessoryStr;
    private String OrderServiceStr;
    private String ReturnAccessoryMsg;//返件

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getAccessorySequency() {
        return AccessorySequency;
    }

    public void setAccessorySequency(String accessorySequency) {
        AccessorySequency = accessorySequency;
    }

    public String getOrderAccessoryStr() {
        return OrderAccessoryStr;
    }

    public void setOrderAccessoryStr(String orderAccessoryStr) {
        OrderAccessoryStr = orderAccessoryStr;
    }

    public String getOrderServiceStr() {
        return OrderServiceStr;
    }

    public void setOrderServiceStr(String orderServiceStr) {
        OrderServiceStr = orderServiceStr;
    }

    public String getReturnAccessoryMsg() {
        return ReturnAccessoryMsg;
    }

    public void setReturnAccessoryMsg(String returnAccessoryMsg) {
        ReturnAccessoryMsg = returnAccessoryMsg;
    }
}
