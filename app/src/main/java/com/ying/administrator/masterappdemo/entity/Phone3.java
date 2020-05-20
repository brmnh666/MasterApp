package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Phone3 implements Serializable {
    private List<Phone> data;
    private String UserId;

    public List<Phone> getData() {
        return data;
    }

    public void setData(List<Phone> data) {
        this.data = data;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
