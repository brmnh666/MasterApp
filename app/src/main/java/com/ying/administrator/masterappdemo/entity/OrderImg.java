package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class OrderImg implements Serializable {

    /**
     * Id : 11
     * OrderFinishID : 11
     * Url : b34e7ff3fe6c45c7834f552be3db7f0c.jpg
     * OrderID : 2000000185
     * CreateTime : 2019-03-09T15:39:52
     * IsUse : Y
     * Type : 1
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int OrderFinishID;
    private String Url;
    private int OrderID;
    private String CreateTime;
    private String IsUse;
    private String Type;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getOrderFinishID() {
        return OrderFinishID;
    }

    public void setOrderFinishID(int OrderFinishID) {
        this.OrderFinishID = OrderFinishID;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
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

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
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
