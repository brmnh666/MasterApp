package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class Accessory {
   // private List<DataBean> data;
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


        private String Id;
        private String FAccessoryID;
        private String AccessoryName;
        private double AccessoryPrice;
        private int ServicePrice;
        private String Sate;
        private int FProductTypeID;
        private String IsUse;
        private int page;
        private int limit;
        private int Version;
        private boolean ischeck;
        private int checkedcount;


       /* *//*新增属性*//*
        private String AccessoryID;
        private String SendState;
        private int Quantity;
        private String OrderID;
        private String CreateTime;
        private String Relation;*/
     /*   public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
*/

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

  /*  public int getFAccessoryID() {
            return FAccessoryID;
        }

        public void setFAccessoryID(int FAccessoryID) {
            this.FAccessoryID = FAccessoryID;
        }
*/

    public String getFAccessoryID() {
        return FAccessoryID;
    }

    public void setFAccessoryID(String FAccessoryID) {
        this.FAccessoryID = FAccessoryID;
    }

    public String getAccessoryName() {
            return AccessoryName;
        }

        public void setAccessoryName(String AccessoryName) {
            this.AccessoryName = AccessoryName;
        }

        public double getAccessoryPrice() {
            return AccessoryPrice;
        }

        public void setAccessoryPrice(double accessoryPrice) {
            AccessoryPrice = accessoryPrice;
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

        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
           this.ischeck = ischeck;
        }

    public int getCheckedcount() {
        return checkedcount;
    }

    public void setCheckedcount(int checkedcount) {
        this.checkedcount = checkedcount;
    }
}
