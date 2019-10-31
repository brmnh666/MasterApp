package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Category implements Serializable {


    /**
     * Id : 251
     * FCategoryID : 251
     * FCategoryName : 单门 容积X≤100
     * ParentID : 250
     * ParentName : 冰箱
     * BrandID : 65
     * BrandName : 格力
     * IsUse : Y
     * InitPrice : 42.0
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String FCategoryID;
    private String FCategoryName;
    private String ParentID;
    private String ParentName;
    private String BrandID;
    private String BrandName;
    private String IsUse;
    private String InitPrice;
    private String page;
    private String limit;
    private String Version;
    private boolean selected=false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFCategoryID() {
        return FCategoryID;
    }

    public void setFCategoryID(String FCategoryID) {
        this.FCategoryID = FCategoryID;
    }

    public String getFCategoryName() {
        return FCategoryName;
    }

    public void setFCategoryName(String FCategoryName) {
        this.FCategoryName = FCategoryName;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String ParentName) {
        this.ParentName = ParentName;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getInitPrice() {
        return InitPrice;
    }

    public void setInitPrice(String InitPrice) {
        this.InitPrice = InitPrice;
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
