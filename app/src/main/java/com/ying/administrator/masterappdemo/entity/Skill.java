package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Skill implements Serializable {

    /**
     * Id : 568
     * AccountSkillID : 568
     * UserID : 18767773654
     * CategoryID : 51
     * IsUse : Y
     * ParentID : 15
     * page : 1
     * limit : 999
     * Version : 0
     */

    private String Id;
    private String AccountSkillID;
    private String UserID;
    private String CategoryID;
    private String IsUse;
    private String ParentID;
    private String page;
    private String limit;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAccountSkillID() {
        return AccountSkillID;
    }

    public void setAccountSkillID(String AccountSkillID) {
        this.AccountSkillID = AccountSkillID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
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
