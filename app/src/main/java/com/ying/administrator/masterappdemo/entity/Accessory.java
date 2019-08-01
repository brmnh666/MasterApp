package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Accessory implements Serializable {
   // private List<DataBean> data;


        private boolean ischeck;
        private int checkedcount;
    /**
     * Id : 22
     * FAccessoryID : 22
     * AccessoryName : 更换门体
     * AccessoryPrice : 66.0
     * ServicePrice : 66.0
     * Sate : N
     * FCategoryID : 251
     * FCategoryName : 单门 容积X≤100
     * IsUse : Y
     * ParentName : 冰箱
     * ParentID : 250
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private String FAccessoryID;
    private String AccessoryName;
    private double AccessoryPrice;
    private double ServicePrice;
    private String Sate;
    private int FCategoryID;
    private String FCategoryName;
    private String IsUse;
    private String ParentName;
    private String SizeID;
    private int count=1;
    private int ParentID;
    private int page;
    private int limit;
    private int Version;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSizeID() {
        return SizeID;
    }

    public void setSizeID(String sizeID) {
        SizeID = sizeID;
    }

    public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
           this.ischeck = ischeck;
        }

    public int getCheckedcount() {
        return checkedcount;
    }

    public void setCheckedcount(int checkedcount) {
        this.checkedcount = checkedcount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFAccessoryID() {
        return FAccessoryID;
    }

    public void setFAccessoryID(String FAccessoryID) {
        this.FAccessoryID = FAccessoryID;
    }

    public String getAccessoryName() {
        return AccessoryName;
    }

    public void setAccessoryName(String AccessoryName) {
        this.AccessoryName = AccessoryName;
    }

    public double getAccessoryPrice() {
        return AccessoryPrice;
    }

    public void setAccessoryPrice(double AccessoryPrice) {
        this.AccessoryPrice = AccessoryPrice;
    }

    public double getServicePrice() {
        return ServicePrice;
    }

    public void setServicePrice(double ServicePrice) {
        this.ServicePrice = ServicePrice;
    }

    public String getSate() {
        return Sate;
    }

    public void setSate(String Sate) {
        this.Sate = Sate;
    }

    public int getFCategoryID() {
        return FCategoryID;
    }

    public void setFCategoryID(int FCategoryID) {
        this.FCategoryID = FCategoryID;
    }

    public String getFCategoryName() {
        return FCategoryName;
    }

    public void setFCategoryName(String FCategoryName) {
        this.FCategoryName = FCategoryName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String ParentName) {
        this.ParentName = ParentName;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int ParentID) {
        this.ParentID = ParentID;
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
