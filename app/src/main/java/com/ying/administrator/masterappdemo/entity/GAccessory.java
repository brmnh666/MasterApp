package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class GAccessory implements Serializable {



    /*继续添加配件时获取可以已经添加的配件*/
    /**
     * Id : 60
     * AccessoryID : 60
     * FAccessoryID : 1
     * FAccessoryName : PC管
     * SendState : N
     * Quantity : 5
     * OrderID : 2000000110
     * CreateTime : 2019-03-06T11:17:22
     * Relation : 96535e17-cf71-4956-b586-3c8fa4c1aca3
     * Price : 35
     * DiscountPrice : 35
     * IsUse : Y
     * Version : 0
     */

    private int Id;
    private int AccessoryID;
    private int FAccessoryID;
    private String FAccessoryName;
    private String SendState;
    private int Quantity;
    private int OrderID;
    private String CreateTime;
    private String Relation;
    private int Price;
    private int DiscountPrice;
    private String IsUse;
    private String ExpressNo;

    private int Version;


    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String expressNo) {
        ExpressNo = expressNo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getAccessoryID() {
        return AccessoryID;
    }

    public void setAccessoryID(int AccessoryID) {
        this.AccessoryID = AccessoryID;
    }

    public int getFAccessoryID() {
        return FAccessoryID;
    }

    public void setFAccessoryID(int FAccessoryID) {
        this.FAccessoryID = FAccessoryID;
    }

    public String getFAccessoryName() {
        return FAccessoryName;
    }

    public void setFAccessoryName(String FAccessoryName) {
        this.FAccessoryName = FAccessoryName;
    }

    public String getSendState() {
        return SendState;
    }

    public void setSendState(String SendState) {
        this.SendState = SendState;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(int DiscountPrice) {
        this.DiscountPrice = DiscountPrice;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
