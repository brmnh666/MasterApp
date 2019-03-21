package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class OrderBeyondImg implements Serializable {


    /**
     * Id : 1
     * OrderBeyondImgID : 1
     * Url : 123.jpg
     * OrderID : 2000000197
     * CreateTime : 2019-03-12T09:26:57
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String OrderBeyondImgID;
    private String Url;
    private String OrderID;
    private String CreateTime;
    private String IsUse;
    private String page;
    private String limit;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getOrderBeyondImgID() {
        return OrderBeyondImgID;
    }

    public void setOrderBeyondImgID(String OrderBeyondImgID) {
        this.OrderBeyondImgID = OrderBeyondImgID;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}
