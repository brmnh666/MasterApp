package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable {

    /**
     * code : 0
     * msg : success
     * count : 1
     * data : [{"Id":71,"FinancialID":71,"PayTypeCode":"Alipay","PayTypeName":"支付宝","PayMoney":1,"ThirdPartyNo":null,"OutTradeNo":null,"CreateTime":"2019-03-19T13:23:35","State":"2","StateName":"支付","OrderID":0,"IsUse":"Y","UserID":"18892621500","BuyerAccount":null,"page":1,"limit":999,"Version":0}]
     */

    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 71
         * FinancialID : 71
         * PayTypeCode : Alipay
         * PayTypeName : 支付宝
         * PayMoney : 1.0
         * ThirdPartyNo : null
         * OutTradeNo : null
         * CreateTime : 2019-03-19T13:23:35
         * State : 2
         * StateName : 支付
         * OrderID : 0
         * IsUse : Y
         * UserID : 18892621500
         * BuyerAccount : null
         * page : 1
         * limit : 999
         * Version : 0
         */

        private int Id;
        private int FinancialID;
        private String PayTypeCode;
        private String PayTypeName;
        private double PayMoney;
        private Object ThirdPartyNo;
        private Object OutTradeNo;
        private String CreateTime;
        private String State;
        private String StateName;
        private int OrderID;
        private String IsUse;
        private String UserID;
        private Object BuyerAccount;
        private int page;
        private int limit;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getFinancialID() {
            return FinancialID;
        }

        public void setFinancialID(int FinancialID) {
            this.FinancialID = FinancialID;
        }

        public String getPayTypeCode() {
            return PayTypeCode;
        }

        public void setPayTypeCode(String PayTypeCode) {
            this.PayTypeCode = PayTypeCode;
        }

        public String getPayTypeName() {
            return PayTypeName;
        }

        public void setPayTypeName(String PayTypeName) {
            this.PayTypeName = PayTypeName;
        }

        public double getPayMoney() {
            return PayMoney;
        }

        public void setPayMoney(double PayMoney) {
            this.PayMoney = PayMoney;
        }

        public Object getThirdPartyNo() {
            return ThirdPartyNo;
        }

        public void setThirdPartyNo(Object ThirdPartyNo) {
            this.ThirdPartyNo = ThirdPartyNo;
        }

        public Object getOutTradeNo() {
            return OutTradeNo;
        }

        public void setOutTradeNo(Object OutTradeNo) {
            this.OutTradeNo = OutTradeNo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String StateName) {
            this.StateName = StateName;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public Object getBuyerAccount() {
            return BuyerAccount;
        }

        public void setBuyerAccount(Object BuyerAccount) {
            this.BuyerAccount = BuyerAccount;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }
    }
}
