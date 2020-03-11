package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class WXOfferQuery implements Serializable {

    /**
     * Id : 4
     * OrderofferId : 4
     * SenUserId : 13806652840
     * price : 50.8
     * OrderId : 2000004039
     * Reason : 唉
     * IsUse : Y
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private int OrderofferId;
    private String SenUserId;
    private double price;
    private int OrderId;
    private String Reason;
    private String IsUse;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getOrderofferId() {
        return OrderofferId;
    }

    public void setOrderofferId(int OrderofferId) {
        this.OrderofferId = OrderofferId;
    }

    public String getSenUserId() {
        return SenUserId;
    }

    public void setSenUserId(String SenUserId) {
        this.SenUserId = SenUserId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
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
