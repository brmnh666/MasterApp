package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Track implements Serializable {

    /*
    * "Id":2106,
    * "RecordID":2106,
    * "CreateDate":"2019-04-11T10:15:02",
    * "OrderID":2000000731,
    * "State":"1",
    * "StateHtml":"已审核派单中",
    * "IsUse":"Y",
    * "StateName":"已审核派单中",
    * "Version":0
    * */

    private String Id;
    private String RecordID;
    private String CreateDate;
    private String OrderID;
    private String State;
    private String StateHtml;
    private String IsUse;
    private String StateName;
    private String Version;
    private String UserID;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStateHtml() {
        return StateHtml;
    }

    public void setStateHtml(String stateHtml) {
        StateHtml = stateHtml;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String isUse) {
        IsUse = isUse;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
