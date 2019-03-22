package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class BankCard implements Serializable {


    /**
     * Id : 0
     * AccountPayID : 0
     * UserID : string
     * PayInfoCode : string
     * PayInfoName : string
     * PayNo : string
     * IsUse : string
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private int AccountPayID;
    private String UserID;
    private String PayInfoCode;
    private String PayInfoName;
    private String PayNo;
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

    public int getAccountPayID() {
        return AccountPayID;
    }

    public void setAccountPayID(int AccountPayID) {
        this.AccountPayID = AccountPayID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getPayInfoCode() {
        return PayInfoCode;
    }

    public void setPayInfoCode(String PayInfoCode) {
        this.PayInfoCode = PayInfoCode;
    }

    public String getPayInfoName() {
        return PayInfoName;
    }

    public void setPayInfoName(String PayInfoName) {
        this.PayInfoName = PayInfoName;
    }

    public String getPayNo() {
        return PayNo;
    }

    public void setPayNo(String PayNo) {
        this.PayNo = PayNo;
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
