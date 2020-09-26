package com.ying.administrator.masterappdemo.v3.bean;

public class GetSignContractManageResult {
    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"status":true,"data":{"Contract":" ","State":1,"SincerityMoney":0}}
     */

    private int StatusCode;
    private String Info;
    private int Count;
    private DataBeanX Data;

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

    public DataBeanX getData() {
        return Data;
    }

    public void setData(DataBeanX Data) {
        this.Data = Data;
    }

    public static class DataBeanX {
        /**
         * status : true
         * data : {"Contract":" ","State":1,"SincerityMoney":0}
         */

        private boolean status;
        private String msg;
        private DataBean data;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * Contract :
             * State : 1
             * SincerityMoney : 0.0
             */

            private String Contract;
            private int State;
            private double SincerityMoney;

            public String getContract() {
                return Contract;
            }

            public void setContract(String Contract) {
                this.Contract = Contract;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public double getSincerityMoney() {
                return SincerityMoney;
            }

            public void setSincerityMoney(double SincerityMoney) {
                this.SincerityMoney = SincerityMoney;
            }
        }
    }
//    {"StatusCode":200,"Info":"请求(或处理)成功","Count":0,"Data":{"status":true,"data":{"Contract":" ","State":1,"SincerityMoney":0.00}}}
    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"status":true,"data":""}
     */


}
