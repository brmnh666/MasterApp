package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class SignsBean implements Serializable {

    /**
     * IsUse : Y
     * Version : 0
     * SignId : 140512
     * Type : 1
     * OrderId : 2000018776
     * UserId : 18767773654
     * CaeateData : 0001-01-01T00:00:00
     * Id : 140512
     */

    private String IsUse;
    private int Version;
    private int SignId;
    private String Type;
    private int OrderId;
    private String UserId;
    private String CaeateData;
    private int Id;

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public int getSignId() {
        return SignId;
    }

    public void setSignId(int SignId) {
        this.SignId = SignId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getCaeateData() {
        return CaeateData;
    }

    public void setCaeateData(String CaeateData) {
        this.CaeateData = CaeateData;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
