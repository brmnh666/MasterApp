package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class AccPicResult implements Serializable {


    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Data : {"Item1":true,"Item2":"请求(或处理)成功"}
     */

    private int StatusCode;
    private String Info;
    private DataBean Data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Item1 : true
         * Item2 : 请求(或处理)成功
         */

        private String Item1;
        private String Item2;

        public String getItem1() {
            return Item1;
        }

        public void setItem1(String item1) {
            Item1 = item1;
        }

        public String getItem2() {
            return Item2;
        }

        public void setItem2(String Item2) {
            this.Item2 = Item2;
        }
    }
}
