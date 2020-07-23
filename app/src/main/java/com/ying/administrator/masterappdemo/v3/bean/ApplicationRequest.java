package com.ying.administrator.masterappdemo.v3.bean;

import java.util.List;

public class ApplicationRequest {

    /**
     * OrderId : 2000008785
     * FAccessorys : [{"FCategoryID":"283","FAccessoryID":"535","FAccessoryName":"更换皮带轮","Price":"0","Quantity":"1","NeedPlatformAuth":"N","DiscountPrice":"0","SizeID":"2"},{"FCategoryID":"283","FAccessoryID":"0","FAccessoryName":"测试配件886","Price":"0","Quantity":"1","NeedPlatformAuth":"Y","SizeID":"0"}]
     * ImgUrls : ["788a91da78b34f9c85019af158c5ec27.jpg","b012e959b2b140dcbdafe079d4d1b5b1.jpg","4d4106018b084d229c9ac1f82808a8ee.jpg"]
     * LeaveMessage : 这是留言这是留言
     * AccessoryState : 0
     */

    private String OrderId;
    private String LeaveMessage;
    private int AccessoryState;
    private List<FAccessorysBean> FAccessorys;
    private List<String> ImgUrls;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getLeaveMessage() {
        return LeaveMessage;
    }

    public void setLeaveMessage(String LeaveMessage) {
        this.LeaveMessage = LeaveMessage;
    }

    public int getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(int AccessoryState) {
        this.AccessoryState = AccessoryState;
    }

    public List<FAccessorysBean> getFAccessorys() {
        return FAccessorys;
    }

    public void setFAccessorys(List<FAccessorysBean> FAccessorys) {
        this.FAccessorys = FAccessorys;
    }

    public List<String> getImgUrls() {
        return ImgUrls;
    }

    public void setImgUrls(List<String> ImgUrls) {
        this.ImgUrls = ImgUrls;
    }

    public static class FAccessorysBean {
        /**
         * FCategoryID : 283
         * FAccessoryID : 535
         * FAccessoryName : 更换皮带轮
         * Price : 0
         * Quantity : 1
         * NeedPlatformAuth : N
         * DiscountPrice : 0
         * SizeID : 2
         */

        private String FCategoryID;
        private String FAccessoryID;
        private String FAccessoryName;
        private String Price;
        private String Quantity;
        private String NeedPlatformAuth;
        private String DiscountPrice;
        private String SizeID;

        public String getFCategoryID() {
            return FCategoryID;
        }

        public void setFCategoryID(String FCategoryID) {
            this.FCategoryID = FCategoryID;
        }

        public String getFAccessoryID() {
            return FAccessoryID;
        }

        public void setFAccessoryID(String FAccessoryID) {
            this.FAccessoryID = FAccessoryID;
        }

        public String getFAccessoryName() {
            return FAccessoryName;
        }

        public void setFAccessoryName(String FAccessoryName) {
            this.FAccessoryName = FAccessoryName;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String Quantity) {
            this.Quantity = Quantity;
        }

        public String getNeedPlatformAuth() {
            return NeedPlatformAuth;
        }

        public void setNeedPlatformAuth(String NeedPlatformAuth) {
            this.NeedPlatformAuth = NeedPlatformAuth;
        }

        public String getDiscountPrice() {
            return DiscountPrice;
        }

        public void setDiscountPrice(String DiscountPrice) {
            this.DiscountPrice = DiscountPrice;
        }

        public String getSizeID() {
            return SizeID;
        }

        public void setSizeID(String SizeID) {
            this.SizeID = SizeID;
        }
    }
}
