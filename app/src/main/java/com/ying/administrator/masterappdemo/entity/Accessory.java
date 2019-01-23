package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class Accessory {
    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;
    /**
     * Id : 0
     * FAccessoryID : 0
     * AccessoryName : string
     * AccessoryPrice : 0
     * ServicePrice : 0
     * Sate : string
     * FProductTypeID : 0
     * IsUse : string
     * page : 0
     * limit : 0
     * Version : 0
     */



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


        private int Id;
        private int FAccessoryID;
        private String AccessoryName;
        private int AccessoryPrice;
        private int ServicePrice;
        private String Sate;
        private int FProductTypeID;
        private String IsUse;
        private int page;
        private int limit;
        private int Version;


        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getFAccessoryID() {
            return FAccessoryID;
        }

        public void setFAccessoryID(int FAccessoryID) {
            this.FAccessoryID = FAccessoryID;
        }

        public String getAccessoryName() {
            return AccessoryName;
        }

        public void setAccessoryName(String AccessoryName) {
            this.AccessoryName = AccessoryName;
        }

        public int getAccessoryPrice() {
            return AccessoryPrice;
        }

        public void setAccessoryPrice(int AccessoryPrice) {
            this.AccessoryPrice = AccessoryPrice;
        }

        public int getServicePrice() {
            return ServicePrice;
        }

        public void setServicePrice(int ServicePrice) {
            this.ServicePrice = ServicePrice;
        }

        public String getSate() {
            return Sate;
        }

        public void setSate(String Sate) {
            this.Sate = Sate;
        }

        public int getFProductTypeID() {
            return FProductTypeID;
        }

        public void setFProductTypeID(int FProductTypeID) {
            this.FProductTypeID = FProductTypeID;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
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
