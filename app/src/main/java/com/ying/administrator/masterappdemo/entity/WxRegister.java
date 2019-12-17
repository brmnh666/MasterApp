package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class WxRegister implements Serializable {

    /**
     * UserID :
     * data : 未绑定手机号
     */

    private String UserID;
    private String data;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
