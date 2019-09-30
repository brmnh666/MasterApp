package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Refund implements Serializable {


    /**
     * Total : 2
     * Data : [{"ShopName":"官方自营店","Vshopid":10,"RefundStatus":"待商家审核","Id":254,"ProductName":"AUX奥克斯KFR35GWBpR3QYQ22大15匹冷暖家用2级变频壁挂空调","EnabledRefundAmount":2069.1,"Amount":2069.1,"Img":"http://mall.xigyu.com//Storage/Shop/1/Products/778/1_350.png","ShopId":1,"RefundMode":"订单退款","RefundModeValue":1,"OrderId":2019051086835370,"OrderItems":[{"ThumbnailsUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/778/1_350.png","ProductName":"AUX奥克斯KFR35GWBpR3QYQ22大15匹冷暖家用2级变频壁挂空调"}],"SellerAuditStatus":"待商家审核","SellerAuditStatusValue":1}]
     * Success : true
     */

    private int Total;
    private boolean Success;
    private List<DataBean> Data;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ShopName : 官方自营店
         * Vshopid : 10
         * RefundStatus : 待商家审核
         * Id : 254
         * ProductName : AUX奥克斯KFR35GWBpR3QYQ22大15匹冷暖家用2级变频壁挂空调
         * EnabledRefundAmount : 2069.1
         * Amount : 2069.1
         * Img : http://mall.xigyu.com//Storage/Shop/1/Products/778/1_350.png
         * ShopId : 1
         * RefundMode : 订单退款
         * RefundModeValue : 1
         * OrderId : 2019051086835370
         * OrderItems : [{"ThumbnailsUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/778/1_350.png","ProductName":"AUX奥克斯KFR35GWBpR3QYQ22大15匹冷暖家用2级变频壁挂空调"}]
         * SellerAuditStatus : 待商家审核
         * SellerAuditStatusValue : 1
         */

        private String ShopName;
        private int Vshopid;
        private String RefundStatus;
        private String Id;
        private String ProductName;
        private double EnabledRefundAmount;
        private double Amount;
        private String Img;
        private int ShopId;
        private String RefundMode;
        private int RefundModeValue;
        private String OrderId;
        private String SellerAuditStatus;
        private int SellerAuditStatusValue;
        private List<OrderItemsBean> OrderItems;

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public int getVshopid() {
            return Vshopid;
        }

        public void setVshopid(int Vshopid) {
            this.Vshopid = Vshopid;
        }

        public String getRefundStatus() {
            return RefundStatus;
        }

        public void setRefundStatus(String RefundStatus) {
            this.RefundStatus = RefundStatus;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public double getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(double EnabledRefundAmount) {
            this.EnabledRefundAmount = EnabledRefundAmount;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String Img) {
            this.Img = Img;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public String getRefundMode() {
            return RefundMode;
        }

        public void setRefundMode(String RefundMode) {
            this.RefundMode = RefundMode;
        }

        public int getRefundModeValue() {
            return RefundModeValue;
        }

        public void setRefundModeValue(int RefundModeValue) {
            this.RefundModeValue = RefundModeValue;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String orderId) {
            OrderId = orderId;
        }

        public String getSellerAuditStatus() {
            return SellerAuditStatus;
        }

        public void setSellerAuditStatus(String SellerAuditStatus) {
            this.SellerAuditStatus = SellerAuditStatus;
        }

        public int getSellerAuditStatusValue() {
            return SellerAuditStatusValue;
        }

        public void setSellerAuditStatusValue(int SellerAuditStatusValue) {
            this.SellerAuditStatusValue = SellerAuditStatusValue;
        }

        public List<OrderItemsBean> getOrderItems() {
            return OrderItems;
        }

        public void setOrderItems(List<OrderItemsBean> OrderItems) {
            this.OrderItems = OrderItems;
        }

        public static class OrderItemsBean {
            /**
             * ThumbnailsUrl : http://mall.xigyu.com//Storage/Shop/1/Products/778/1_350.png
             * ProductName : AUX奥克斯KFR35GWBpR3QYQ22大15匹冷暖家用2级变频壁挂空调
             */

            private String ThumbnailsUrl;
            private String ProductName;

            public String getThumbnailsUrl() {
                return ThumbnailsUrl;
            }

            public void setThumbnailsUrl(String ThumbnailsUrl) {
                this.ThumbnailsUrl = ThumbnailsUrl;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }
        }
    }
}
