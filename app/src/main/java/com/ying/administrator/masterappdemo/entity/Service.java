package com.ying.administrator.masterappdemo.entity;

public class Service {

    /**
     * Id : 0
     * FServiceID : 0
     * FServiceName : string
     * FBrandID : 0
     * FCategoryID : 0
     * InitPrice : 0
     * IsUse : string
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private int FServiceID;
    private String FServiceName;
    private int FBrandID;
    private int FCategoryID;
    private int InitPrice;
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

    public int getFServiceID() {
        return FServiceID;
    }

    public void setFServiceID(int FServiceID) {
        this.FServiceID = FServiceID;
    }

    public String getFServiceName() {
        return FServiceName;
    }

    public void setFServiceName(String FServiceName) {
        this.FServiceName = FServiceName;
    }

    public int getFBrandID() {
        return FBrandID;
    }

    public void setFBrandID(int FBrandID) {
        this.FBrandID = FBrandID;
    }

    public int getFCategoryID() {
        return FCategoryID;
    }

    public void setFCategoryID(int FCategoryID) {
        this.FCategoryID = FCategoryID;
    }

    public int getInitPrice() {
        return InitPrice;
    }

    public void setInitPrice(int InitPrice) {
        this.InitPrice = InitPrice;
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
