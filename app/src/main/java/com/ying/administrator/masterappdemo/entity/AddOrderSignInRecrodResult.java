package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class AddOrderSignInRecrodResult implements Serializable {


    /**
     * Data : {"msg":"参数错误","result":false}
     * Count : 0
     * Info : 请求(或处理)成功
     * StatusCode : 200
     */

    private DataBean Data;
    private int Count;
    private String Info;
    private int StatusCode;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public static class DataBean {
        /**
         * msg : 参数错误
         * result : false
         */

        private String msg;
        private boolean result;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }
}
