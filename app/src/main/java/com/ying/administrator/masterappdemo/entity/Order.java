package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    /*
    * "Success":"true",
    * "AllOrderCounts":0,
    * "WaitingForComments":0,
    * "WaitingForRecieve":0,
    * "WaitingForPay":0,
    * "Orders":[]
    * */

    private boolean Success;
    private String AllOrderCounts;
    private String WaitingForComments;
    private String WaitingForRecieve;
    private String WaitingForPay;
    private List<OrdersBean> Orders;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getAllOrderCounts() {
        return AllOrderCounts;
    }

    public void setAllOrderCounts(String allOrderCounts) {
        AllOrderCounts = allOrderCounts;
    }

    public String getWaitingForComments() {
        return WaitingForComments;
    }

    public void setWaitingForComments(String waitingForComments) {
        WaitingForComments = waitingForComments;
    }

    public String getWaitingForRecieve() {
        return WaitingForRecieve;
    }

    public void setWaitingForRecieve(String waitingForRecieve) {
        WaitingForRecieve = waitingForRecieve;
    }

    public String getWaitingForPay() {
        return WaitingForPay;
    }

    public void setWaitingForPay(String waitingForPay) {
        WaitingForPay = waitingForPay;
    }

    public List<OrdersBean> getOrders() {
        return Orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        Orders = orders;
    }

    public static class OrdersBean{

        /*
        * "id":2017021483156396,
        * "status":"待消费",
        * "orderStatus":2,
        * "orderType":null,
        * "orderTypeName":"",
        * "shopname":"官方自营店",
        * "vshopId":10,
        * "orderTotalAmount":"11.90",
        * "productCount":1,
        * "commentCount":0,
        * "pickupCode":null,
        * "EnabledRefundAmount":11.90,
        * "itemInfo":[{"productId":702,"productName":"卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)","image":"http://mall.xigyu.com//Storage/Shop/1/Products/702/1_350.png","count":1,"price":11.90,"Unit":"包","color":null,"size":null,"version":null,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","RefundStats":null,"OrderRefundId":0,"EnabledRefundAmount":11.90}],
        * "RefundStats":0,
        * "OrderRefundId":0,
        * "HasExpressStatus":false,
        * "HasAppendComment":false,
        * "Invoice":"不需要发票",
        * "InvoiceValue":0,
        * "InvoiceContext":null,
        * "InvoiceTitle":null,
        * "PaymentType":"线上支付",
        * "PaymentTypeValue":1,
        * "CanRefund":true
        * */

        private String id;
        private String status;
        private int orderStatus;
        private String orderType;
        private String orderTypeName;
        private String shopname;
        private String vshopId;
        private double orderTotalAmount;
        private String productCount;
        private String commentCount;
        private String pickupCode;
        private String EnabledRefundAmount;
        private List<itemInfoBean> itemInfo;
        private String RefundStats;
        private String OrderRefundId;
        private String HasExpressStatus;
        private String HasAppendComment;
        private String Invoice;
        private String InvoiceValue;
        private String InvoiceContext;
        private String InvoiceTitle;
        private String PaymentType;
        private String PaymentTypeValue;
        private String CanRefund;
        private String BisId;
        private String ActualMoney;

        public String getActualMoney() {
            return ActualMoney;
        }

        public void setActualMoney(String actualMoney) {
            ActualMoney = actualMoney;
        }

        public String getBisId() {
            return BisId;
        }

        public void setBisId(String bisId) {
            BisId = bisId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrderTypeName() {
            return orderTypeName;
        }

        public void setOrderTypeName(String orderTypeName) {
            this.orderTypeName = orderTypeName;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getVshopId() {
            return vshopId;
        }

        public void setVshopId(String vshopId) {
            this.vshopId = vshopId;
        }

        public double getOrderTotalAmount() {
            return orderTotalAmount;
        }

        public void setOrderTotalAmount(double orderTotalAmount) {
            this.orderTotalAmount = orderTotalAmount;
        }

        public String getProductCount() {
            return productCount;
        }

        public void setProductCount(String productCount) {
            this.productCount = productCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getPickupCode() {
            return pickupCode;
        }

        public void setPickupCode(String pickupCode) {
            this.pickupCode = pickupCode;
        }

        public String getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(String enabledRefundAmount) {
            EnabledRefundAmount = enabledRefundAmount;
        }

        public List<itemInfoBean> getItemInfo() {
            return itemInfo;
        }

        public void setItemInfo(List<itemInfoBean> itemInfo) {
            this.itemInfo = itemInfo;
        }

        public String getRefundStats() {
            return RefundStats;
        }

        public void setRefundStats(String refundStats) {
            RefundStats = refundStats;
        }

        public String getOrderRefundId() {
            return OrderRefundId;
        }

        public void setOrderRefundId(String orderRefundId) {
            OrderRefundId = orderRefundId;
        }

        public String getHasExpressStatus() {
            return HasExpressStatus;
        }

        public void setHasExpressStatus(String hasExpressStatus) {
            HasExpressStatus = hasExpressStatus;
        }

        public String getHasAppendComment() {
            return HasAppendComment;
        }

        public void setHasAppendComment(String hasAppendComment) {
            HasAppendComment = hasAppendComment;
        }

        public String getInvoice() {
            return Invoice;
        }

        public void setInvoice(String invoice) {
            Invoice = invoice;
        }

        public String getInvoiceValue() {
            return InvoiceValue;
        }

        public void setInvoiceValue(String invoiceValue) {
            InvoiceValue = invoiceValue;
        }

        public String getInvoiceContext() {
            return InvoiceContext;
        }

        public void setInvoiceContext(String invoiceContext) {
            InvoiceContext = invoiceContext;
        }

        public String getInvoiceTitle() {
            return InvoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            InvoiceTitle = invoiceTitle;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public String getPaymentTypeValue() {
            return PaymentTypeValue;
        }

        public void setPaymentTypeValue(String paymentTypeValue) {
            PaymentTypeValue = paymentTypeValue;
        }

        public String getCanRefund() {
            return CanRefund;
        }

        public void setCanRefund(String canRefund) {
            CanRefund = canRefund;
        }
    }


    /*
    *"productId":702,
    * "productName":"卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)",
    * "image":"http://mall.xigyu.com//Storage/Shop/1/Products/702/1_350.png",
    * "count":1,
    * "price":11.90,
    * "Unit":"包",
    * "color":null,
    * "size":null,
    * "version":null,
    * "ColorAlias":"颜色",
    * "SizeAlias":"尺码",
    * "VersionAlias":"规格",
    * "RefundStats":null,
    * "OrderRefundId":0,
    * "EnabledRefundAmount":11.90
    * */

    public static class itemInfoBean{
        private String productId;
        private String productName;
        private String image;
        private String count;
        private String price;
        private String Unit;
        private String color;
        private String size;
        private String version;
        private String ColorAlias;
        private String SizeAlias;
        private String VersionAlias;
        private String RefundStats;
        private String OrderRefundId;
        private String EnabledRefundAmount;
        private String SKuId;

        public String getSKuId() {
            return SKuId;
        }

        public void setSKuId(String SKuId) {
            this.SKuId = SKuId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        public String getColor() {
            return color==null?"":color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size==null?"":size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getVersion() {
            return version==null?"":version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getColorAlias() {
            return ColorAlias;
        }

        public void setColorAlias(String colorAlias) {
            ColorAlias = colorAlias;
        }

        public String getSizeAlias() {
            return SizeAlias;
        }

        public void setSizeAlias(String sizeAlias) {
            SizeAlias = sizeAlias;
        }

        public String getVersionAlias() {
            return VersionAlias;
        }

        public void setVersionAlias(String versionAlias) {
            VersionAlias = versionAlias;
        }

        public String getRefundStats() {
            return RefundStats;
        }

        public void setRefundStats(String refundStats) {
            RefundStats = refundStats;
        }

        public String getOrderRefundId() {
            return OrderRefundId;
        }

        public void setOrderRefundId(String orderRefundId) {
            OrderRefundId = orderRefundId;
        }

        public String getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(String enabledRefundAmount) {
            EnabledRefundAmount = enabledRefundAmount;
        }
    }
}
