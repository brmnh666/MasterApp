package com.ying.administrator.masterappdemo.v3.bean;

public class ConfirmReceiptResult {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":0,"msg":"success","count":0,"data":"确认成功","errcode":null,"errorId":null,"IsActionExceted":false}
     */

    private int StatusCode;
    private String Info;
    private int Count;
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

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * code : 0
         * msg : success
         * count : 0
         * data : 确认成功
         * errcode : null
         * errorId : null
         * IsActionExceted : false
         */

        private int code;
        private String msg;
        private int count;
        private String data;
        private Object errcode;
        private Object errorId;
        private boolean IsActionExceted;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Object getErrcode() {
            return errcode;
        }

        public void setErrcode(Object errcode) {
            this.errcode = errcode;
        }

        public Object getErrorId() {
            return errorId;
        }

        public void setErrorId(Object errorId) {
            this.errorId = errorId;
        }

        public boolean isIsActionExceted() {
            return IsActionExceted;
        }

        public void setIsActionExceted(boolean IsActionExceted) {
            this.IsActionExceted = IsActionExceted;
        }
    }
}
