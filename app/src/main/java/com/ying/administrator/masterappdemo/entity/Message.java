package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Message implements Serializable {


    /**
     * Id : 274
     * MessageID : 274
     * UserID : 18892621500
     * Nowtime : 2019-03-22T17:07:54
     * Content : 配件已发货
     * Type : 2
     * SubType : 3
     * IsUse : Y
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private String MessageID;
    private String UserID;
    private String Nowtime;
    private String Content;
    private int Type;
    private int SubType;
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

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String MessageID) {
        this.MessageID = MessageID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getNowtime() {
        return Nowtime;
    }

    public void setNowtime(String Nowtime) {
        this.Nowtime = Nowtime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getSubType() {
        return SubType;
    }

    public void setSubType(int SubType) {
        this.SubType = SubType;
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
