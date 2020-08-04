package com.ying.administrator.masterappdemo.v3.bean;

public class UpdateAccessoryResult {
    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"result":false,"msg":"配件名为【门封条】的配件审核通过，不能删除"}
     */

    private int StatusCode;
    private String Info;
    private int Count;
    private DeleteAccessoryResult.DataBean Data;

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

    public DeleteAccessoryResult.DataBean getData() {
        return Data;
    }

    public void setData(DeleteAccessoryResult.DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * status : false
         * msg : 配件名为【门封条】的配件审核通过，不能删除
         */

        private boolean status;
        private String msg;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }



        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
