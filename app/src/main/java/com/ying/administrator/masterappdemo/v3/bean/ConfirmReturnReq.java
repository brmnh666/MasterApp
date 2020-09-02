package com.ying.administrator.masterappdemo.v3.bean;

public class ConfirmReturnReq {
    private int AccessoryID;
    private String ReturnExpressNo;
    private String PostMoney;

    public ConfirmReturnReq(int accessoryID, String returnExpressNo, String postMoney) {
        AccessoryID = accessoryID;
        ReturnExpressNo = returnExpressNo;
        PostMoney = postMoney;
    }

    public int getAccessoryID() {
        return AccessoryID;
    }

    public void setAccessoryID(int accessoryID) {
        AccessoryID = accessoryID;
    }

    public String getReturnExpressNo() {
        return ReturnExpressNo;
    }

    public void setReturnExpressNo(String returnExpressNo) {
        ReturnExpressNo = returnExpressNo;
    }

    public String getPostMoney() {
        return PostMoney;
    }

    public void setPostMoney(String postMoney) {
        PostMoney = postMoney;
    }
}
