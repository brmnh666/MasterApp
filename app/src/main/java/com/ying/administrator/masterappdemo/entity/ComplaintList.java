package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class ComplaintList implements Serializable {


    /**
     * Id : 67
     * ComplaintID : 67
     * Type : F
     * OrderID : 2000003458
     * Content : 垃圾
     * UserID : 13806652840
     * CreateTime : 2019-12-13T16:04:33
     * ComplaintUser : 13806652840
     * ComplaintType : W
     * Photo : c22ac96faa154d19a8bdbf6bfc4e6a23.jpeg
     * IsUse : Y
     * Order : null
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int ComplaintID;
    private String Type;
    private int OrderID;
    private String Content;
    private String UserID;
    private String CreateTime;
    private String ComplaintUser;
    private String ComplaintType;
    private String Photo;
    private String IsUse;
    private Object Order;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getComplaintID() {
        return ComplaintID;
    }

    public void setComplaintID(int ComplaintID) {
        this.ComplaintID = ComplaintID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getComplaintUser() {
        return ComplaintUser;
    }

    public void setComplaintUser(String ComplaintUser) {
        this.ComplaintUser = ComplaintUser;
    }

    public String getComplaintType() {
        return ComplaintType;
    }

    public void setComplaintType(String ComplaintType) {
        this.ComplaintType = ComplaintType;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public Object getOrder() {
        return Order;
    }

    public void setOrder(Object Order) {
        this.Order = Order;
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
