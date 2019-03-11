package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class ReturnaccessoryImg implements Serializable {

    /**
     * Id : 29
     * ReturnAccessoryID : 29
     * OrderID : 2000000176
     * CeateTime : 2019-03-09T15:00:23
     * Url : 61b67cae620d46ae909176fbf6fc5661.jpg
     * Relation : null
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int ReturnAccessoryID;
    private int OrderID;
    private String CeateTime;
    private String Url;
    private Object Relation;
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

    public int getReturnAccessoryID() {
        return ReturnAccessoryID;
    }

    public void setReturnAccessoryID(int ReturnAccessoryID) {
        this.ReturnAccessoryID = ReturnAccessoryID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getCeateTime() {
        return CeateTime;
    }

    public void setCeateTime(String CeateTime) {
        this.CeateTime = CeateTime;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public Object getRelation() {
        return Relation;
    }

    public void setRelation(Object Relation) {
        this.Relation = Relation;
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
