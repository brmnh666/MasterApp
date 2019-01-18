package com.ying.administrator.masterappdemo.base;

/**
 */
public class BaseResult<B> {
    private int StatusCode;
    private String Info;
    private B Data;


    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public B getData() {
        return Data;
    }

    public void setData(B data) {
        Data = data;
    }
}
