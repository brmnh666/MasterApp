package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class GService implements Serializable {


    /**
     * Id : 34
     * OrderServiceID : 34
     * BrandID : 0
     * CategoryID : 0
     * ServiceName : 加氟
     * ServiceID : 2
     * Price : 50.0
     * DiscountPrice : 50.0
     * IsUse : Y
     * Relation : b3b069b2-1c3a-452b-a696-117b3f7597f7
     * OrderID : 2000000102
     * CreateTime : 2019-03-07T15:08:15
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int OrderServiceID;
    private int BrandID;
    private int CategoryID;
    private String ServiceName;
    private int ServiceID;
    private double Price;
    private double DiscountPrice;
    private String IsUse;
    private String Relation;
    private int OrderID;
    private String CreateTime;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getOrderServiceID() {
        return OrderServiceID;
    }

    public void setOrderServiceID(int OrderServiceID) {
        this.OrderServiceID = OrderServiceID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int ServiceID) {
        this.ServiceID = ServiceID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(double DiscountPrice) {
        this.DiscountPrice = DiscountPrice;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
