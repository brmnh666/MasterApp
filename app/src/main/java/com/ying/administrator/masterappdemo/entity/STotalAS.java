package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

/*配件和服务都提交的情况和返件*/
public class STotalAS implements Serializable {
    private String OrderID;
    private String AccessorySequency;
    private String OrderAccessoryStr;
    private String OrderServiceStr;
    private String ReturnAccessoryMsg;//返件

    private String SendRepairState;//是否送修YN
    private String UpdateDate;//上门时间
    private double BeyondMoney;//远程费
    private String BeyondDistance;//远程距离

    public String getSendRepairState() {
        return SendRepairState;
    }

    public void setSendRepairState(String sendRepairState) {
        SendRepairState = sendRepairState;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public double getBeyondMoney() {
        return BeyondMoney;
    }

    public void setBeyondMoney(double beyondMoney) {
        BeyondMoney = beyondMoney;
    }

    public String getBeyondDistance() {
        return BeyondDistance;
    }

    public void setBeyondDistance(String beyondDistance) {
        BeyondDistance = beyondDistance;
    }

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
