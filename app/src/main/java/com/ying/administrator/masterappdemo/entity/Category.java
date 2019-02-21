package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Category implements Serializable {


    /**
     * Id : 1
     * FCategoryID : 1
     * FCategoryName : 洗衣机类
     * ParentID : 999
     * IsUse : Y
     * InitPrice : 50.0
     * Version : 0
     */

    private String Id;
    private String FCategoryID;
    private String FCategoryName;
    private String ParentID;
    private String IsUse;
    private String InitPrice;
    private String Version;

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

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}
