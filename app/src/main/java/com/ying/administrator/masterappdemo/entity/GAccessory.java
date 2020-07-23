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
     *
     *
     * "Id":737,"
     * AccessoryID":737,
     * "FAccessoryID":22,
     * "FAccessoryName":"更换门体",
     * "SendState":"Y",
     * "Quantity":1,
     * "OrderID":2000001107,
     * "CreateTime":2019-05-10T15:03:50",
     * "Relation":"af8c7a57-39ec-425c-9459-60bb2e22f41e",
     * "Price":66.00,
     * "DiscountPrice":66.00,
     * "IsUse":"Y",
     * "IsPay":"Y",
     * "ExpressNo":"123456789",
     * "State":"1",
     * "AccessoryState":"0",
     * "TypeID":"1",
     * "ApplyNum":1,
     * "QApplyNum":0,
     * "Version":0
     * "Photo1":"46a346686b2e402992b1935c748addf8.jpg",
     * "Photo2":"0bb425647ecf43548927cc6766b0a8ef.jpg"
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
    private Double Price;
    private Double DiscountPrice;
    private String IsUse;
    private String ExpressNo;
    private String State;
    private String AccessoryState;
    private String TypeID;
    private String ApplyNum;
    private String QApplyNum;
    private int Version;
    private String Photo1;
    private String Photo2;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

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

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(Double DiscountPrice) {
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

    public String getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(String accessoryState) {
        AccessoryState = accessoryState;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getApplyNum() {
        return ApplyNum;
    }

    public void setApplyNum(String applyNum) {
        ApplyNum = applyNum;
    }

    public String getQApplyNum() {
        return QApplyNum;
    }

    public void setQApplyNum(String QApplyNum) {
        this.QApplyNum = QApplyNum;
    }

    public String getPhoto1() {
        return Photo1;
    }

    public void setPhoto1(String photo1) {
        Photo1 = photo1;
    }

    public String getPhoto2() {
        return Photo2;
    }

    public void setPhoto2(String photo2) {
        Photo2 = photo2;
    }
}
