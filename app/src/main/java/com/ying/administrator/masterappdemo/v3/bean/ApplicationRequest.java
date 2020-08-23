package com.ying.administrator.masterappdemo.v3.bean;

import java.util.List;

public class ApplicationRequest {

    /**
     * OrderID : 2000008785
     * Accessorys : [{"FCategoryID":"283","FAccessoryID":"535","FAccessoryName":"更换皮带轮","Price":"0","Quantity":"1","NeedPlatformAuth":"N","DiscountPrice":"0","SizeID":"2"},{"FCategoryID":"283","FAccessoryID":"0","FAccessoryName":"测试配件886","Price":"0","Quantity":"1","NeedPlatformAuth":"Y","SizeID":"0"}]
     * ImgUrls : ["788a91da78b34f9c85019af158c5ec27.jpg","b012e959b2b140dcbdafe079d4d1b5b1.jpg","4d4106018b084d229c9ac1f82808a8ee.jpg"]
     * LeaveMessage : 这是留言这是留言
     * AccessoryState : 0
     * RecipientType : 1
     */

    private String OrderID;
    private String OrderProdID;
    private String Bak;
    private int AccessoryState;
    private int RecipientType; //收件类型 1用户 2师傅
    private List<FAccessorysBean> Accessorys;
    private List<String> ImgUrls;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderId) {
        this.OrderID = OrderId;
    }

    public String getOrderProdID() {
        return OrderProdID;
    }

    public void setOrderProdID(String orderProdID) {
        OrderProdID = orderProdID;
    }

    public String getBak() {
        return Bak;
    }

    public void setBak(String bak) {
        Bak = bak;
    }

    public int getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(int AccessoryState) {
        this.AccessoryState = AccessoryState;
    }

    public int getRecipientType() {
        return RecipientType;
    }

    public void setRecipientType(int recipientType) {
        RecipientType = recipientType;
    }

    public List<FAccessorysBean> getAccessorys() {
        return Accessorys;
    }

    public void setAccessorys(List<FAccessorysBean> accessorys) {
        this.Accessorys = accessorys;
    }

    public List<String> getImgUrls() {
        return ImgUrls;
    }

    public void setImgUrls(List<String> ImgUrls) {
        this.ImgUrls = ImgUrls;
    }

    public static class FAccessorysBean {
        /**
         * FAccessoryID : 535
         * FAccessoryName : 更换皮带轮
         * Price : 0
         * Quantity : 1
         */

        private String FAccessoryID;
        private String FAccessoryName;
        private String Price;
        private String Quantity;

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
    }
}
