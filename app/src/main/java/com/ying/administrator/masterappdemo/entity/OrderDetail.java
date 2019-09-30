package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class OrderDetail implements Serializable {

    /**
     * Success : true
     * Order : {"Id":2019051066544565,"OrderType":null,"OrderTypeName":"","Status":"已完成","JoinStatus":-2,"ShipTo":"钉钉官方短信","Phone":"10655059113144","Address":"河北省 唐山市 路南区 学院南路街道 4","HasExpressStatus":false,"ExpressCompanyName":null,"Freight":0,"StringegralDiscount":0,"RealTotalAmount":1619.1,"RefundTotalAmount":0,"OrderDate":"2019-05-10 11:49:06","ShopName":"官方自营店","VShopId":10,"commentCount":0,"ShopId":1,"orderStatus":5,"Invoice":"不需要发票","InvoiceValue":0,"InvoiceContext":null,"InvoiceTitle":null,"PaymentType":"","PaymentTypeValue":0,"FullDiscount":0,"DiscountAmount":0,"OrderRemarks":"","HasBonus":false,"ShareHref":"","ShareTitle":"","ShareDetail":"","IsCanRefund":false,"EnabledRefundAmount":1619.1,"HasAppendComment":false,"SelfTake":0}
     * OrderItem : [{"ItemId":1176,"ProductId":779,"BrandId":356,"BrandName":"aux","CategoryId":251,"CategoryName":"单门 容积X≤100","ParentCategoryId":250,"ParentCategoryName":"冰箱","ProductName":"AUX奥克斯KFR35GWNFI193大15匹冷暖定频壁挂式家用空调挂机","Count":1,"Price":1619.1,"ProductImage":"http://mall.xigyu.com//Storage/Shop/1/Products/779/1_100.png","color":null,"size":null,"version":null,"IsCanRefund":false,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","EnabledRefundAmount":1619.1}]
     * StoreInfo : null
     * CustomerServices : []
     */

    private boolean Success;
    private OrderBean Order;
    private String StoreInfo;
    private List<OrderItemBean> OrderItem;
    private List<?> CustomerServices;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public OrderBean getOrder() {
        return Order;
    }

    public void setOrder(OrderBean Order) {
        this.Order = Order;
    }

    public String getStoreInfo() {
        return StoreInfo;
    }

    public void setStoreInfo(String StoreInfo) {
        this.StoreInfo = StoreInfo;
    }

    public List<OrderItemBean> getOrderItem() {
        return OrderItem;
    }

    public void setOrderItem(List<OrderItemBean> OrderItem) {
        this.OrderItem = OrderItem;
    }

    public List<?> getCustomerServices() {
        return CustomerServices;
    }

    public void setCustomerServices(List<?> CustomerServices) {
        this.CustomerServices = CustomerServices;
    }

    public static class OrderBean implements Serializable{
        /**
         * Id : 2019051066544565
         * OrderType : null
         * OrderTypeName : 
         * Status : 已完成
         * JoinStatus : -2
         * ShipTo : 钉钉官方短信
         * Phone : 10655059113144
         * Address : 河北省 唐山市 路南区 学院南路街道 4
         * HasExpressStatus : false
         * ExpressCompanyName : null
         * Freight : 0.0
         * StringegralDiscount : 0.0
         * RealTotalAmount : 1619.1
         * RefundTotalAmount : 0.0
         * OrderDate : 2019-05-10 11:49:06
         * ShopName : 官方自营店
         * VShopId : 10
         * commentCount : 0
         * ShopId : 1
         * orderStatus : 5
         * Invoice : 不需要发票
         * InvoiceValue : 0
         * InvoiceContext : null
         * InvoiceTitle : null
         * PaymentType : 
         * PaymentTypeValue : 0
         * FullDiscount : 0.0
         * DiscountAmount : 0.0
         * OrderRemarks : 
         * HasBonus : false
         * ShareHref : 
         * ShareTitle : 
         * ShareDetail : 
         * IsCanRefund : false
         * EnabledRefundAmount : 1619.1
         * HasAppendComment : false
         * SelfTake : 0
         */

        private String Id;
        private String OrderType;
        private String BisId;
        private String OrderTypeName;
        private String Status;
        private String JoinStatus;
        private String ShipTo;
        private String Phone;
        private String Address;
        private String RegionId;
        private boolean HasExpressStatus;
        private String ExpressCompanyName;
        private double Freight;
        private double StringegralDiscount;
        private double RealTotalAmount;
        private double RefundTotalAmount;
        private String OrderDate;
        private String ShopName;
        private String VShopId;
        private String commentCount;
        private String ShopId;
        private String orderStatus;
        private String Invoice;
        private String InvoiceValue;
        private String InvoiceContext;
        private String InvoiceTitle;
        private String PaymentType;
        private String PaymentTypeValue;
        private double FullDiscount;
        private double DiscountAmount;
        private String OrderRemarks;
        private boolean HasBonus;
        private String ShareHref;
        private String ShareTitle;
        private String ShareDetail;
        private boolean IsCanRefund;
        private double EnabledRefundAmount;
        private boolean HasAppendComment;
        private String SelfTake;
        private String ActualMoney;

        public String getActualMoney() {
            return ActualMoney;
        }

        public void setActualMoney(String actualMoney) {
            ActualMoney = actualMoney;
        }

        public String getRegionId() {
            return RegionId;
        }

        public void setRegionId(String regionId) {
            RegionId = regionId;
        }

        public String getBisId() {
            return BisId;
        }

        public void setBisId(String bisId) {
            BisId = bisId;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getOrderType() {
            return OrderType;
        }

        public void setOrderType(String OrderType) {
            this.OrderType = OrderType;
        }

        public String getOrderTypeName() {
            return OrderTypeName;
        }

        public void setOrderTypeName(String OrderTypeName) {
            this.OrderTypeName = OrderTypeName;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getJoinStatus() {
            return JoinStatus;
        }

        public void setJoinStatus(String JoinStatus) {
            this.JoinStatus = JoinStatus;
        }

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String ShipTo) {
            this.ShipTo = ShipTo;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public boolean isHasExpressStatus() {
            return HasExpressStatus;
        }

        public void setHasExpressStatus(boolean HasExpressStatus) {
            this.HasExpressStatus = HasExpressStatus;
        }

        public String getExpressCompanyName() {
            return ExpressCompanyName;
        }

        public void setExpressCompanyName(String ExpressCompanyName) {
            this.ExpressCompanyName = ExpressCompanyName;
        }

        public double getFreight() {
            return Freight;
        }

        public void setFreight(double Freight) {
            this.Freight = Freight;
        }

        public double getStringegralDiscount() {
            return StringegralDiscount;
        }

        public void setStringegralDiscount(double StringegralDiscount) {
            this.StringegralDiscount = StringegralDiscount;
        }

        public double getRealTotalAmount() {
            return RealTotalAmount;
        }

        public void setRealTotalAmount(double RealTotalAmount) {
            this.RealTotalAmount = RealTotalAmount;
        }

        public double getRefundTotalAmount() {
            return RefundTotalAmount;
        }

        public void setRefundTotalAmount(double RefundTotalAmount) {
            this.RefundTotalAmount = RefundTotalAmount;
        }

        public String getOrderDate() {
            return OrderDate;
        }

        public void setOrderDate(String OrderDate) {
            this.OrderDate = OrderDate;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getVShopId() {
            return VShopId;
        }

        public void setVShopId(String VShopId) {
            this.VShopId = VShopId;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getInvoice() {
            return Invoice;
        }

        public void setInvoice(String Invoice) {
            this.Invoice = Invoice;
        }

        public String getInvoiceValue() {
            return InvoiceValue;
        }

        public void setInvoiceValue(String InvoiceValue) {
            this.InvoiceValue = InvoiceValue;
        }

        public String getInvoiceContext() {
            return InvoiceContext;
        }

        public void setInvoiceContext(String InvoiceContext) {
            this.InvoiceContext = InvoiceContext;
        }

        public String getInvoiceTitle() {
            return InvoiceTitle;
        }

        public void setInvoiceTitle(String InvoiceTitle) {
            this.InvoiceTitle = InvoiceTitle;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String PaymentType) {
            this.PaymentType = PaymentType;
        }

        public String getPaymentTypeValue() {
            return PaymentTypeValue;
        }

        public void setPaymentTypeValue(String PaymentTypeValue) {
            this.PaymentTypeValue = PaymentTypeValue;
        }

        public double getFullDiscount() {
            return FullDiscount;
        }

        public void setFullDiscount(double FullDiscount) {
            this.FullDiscount = FullDiscount;
        }

        public double getDiscountAmount() {
            return DiscountAmount;
        }

        public void setDiscountAmount(double DiscountAmount) {
            this.DiscountAmount = DiscountAmount;
        }

        public String getOrderRemarks() {
            return OrderRemarks;
        }

        public void setOrderRemarks(String OrderRemarks) {
            this.OrderRemarks = OrderRemarks;
        }

        public boolean isHasBonus() {
            return HasBonus;
        }

        public void setHasBonus(boolean HasBonus) {
            this.HasBonus = HasBonus;
        }

        public String getShareHref() {
            return ShareHref;
        }

        public void setShareHref(String ShareHref) {
            this.ShareHref = ShareHref;
        }

        public String getShareTitle() {
            return ShareTitle;
        }

        public void setShareTitle(String ShareTitle) {
            this.ShareTitle = ShareTitle;
        }

        public String getShareDetail() {
            return ShareDetail;
        }

        public void setShareDetail(String ShareDetail) {
            this.ShareDetail = ShareDetail;
        }

        public boolean isIsCanRefund() {
            return IsCanRefund;
        }

        public void setIsCanRefund(boolean IsCanRefund) {
            this.IsCanRefund = IsCanRefund;
        }

        public double getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(double EnabledRefundAmount) {
            this.EnabledRefundAmount = EnabledRefundAmount;
        }

        public boolean isHasAppendComment() {
            return HasAppendComment;
        }

        public void setHasAppendComment(boolean HasAppendComment) {
            this.HasAppendComment = HasAppendComment;
        }

        public String getSelfTake() {
            return SelfTake;
        }

        public void setSelfTake(String SelfTake) {
            this.SelfTake = SelfTake;
        }
    }

    public static class OrderItemBean implements Serializable{
        /**
         * ItemId : 1176
         * ProductId : 779
         * BrandId : 356
         * BrandName : aux
         * CategoryId : 251
         * CategoryName : 单门 容积X≤100
         * ParentCategoryId : 250
         * ParentCategoryName : 冰箱
         * ProductName : AUX奥克斯KFR35GWNFI193大15匹冷暖定频壁挂式家用空调挂机
         * Count : 1
         * Price : 1619.1
         * ProductImage : http://mall.xigyu.com//Storage/Shop/1/Products/779/1_100.png
         * color : null
         * size : null
         * version : null
         * IsCanRefund : false
         * ColorAlias : 颜色
         * SizeAlias : 尺码
         * VersionAlias : 规格
         * EnabledRefundAmount : 1619.1
         */

        private String ItemId;
        private String ProductId;
        private String BrandId;
        private String BrandName;
        private String CategoryId;
        private String CategoryName;
        private String ParentCategoryId;
        private String ParentCategoryName;
        private String ProductName;
        private String Count;
        private double Price;
        private String ProductImage;
        private String color;
        private String size;
        private String version;
        private boolean IsCanRefund;
        private String ColorAlias;
        private String SizeAlias;
        private String VersionAlias;
        private double EnabledRefundAmount;
        private boolean IsInstall;
        private String SKuId;

        public String getSKuId() {
            return SKuId;
        }

        public void setSKuId(String SKuId) {
            this.SKuId = SKuId;
        }

        public boolean isInstall() {
            return IsInstall;
        }

        public void setInstall(boolean install) {
            IsInstall = install;
        }

        public String getItemId() {
            return ItemId;
        }

        public void setItemId(String ItemId) {
            this.ItemId = ItemId;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String ProductId) {
            this.ProductId = ProductId;
        }

        public String getBrandId() {
            return BrandId;
        }

        public void setBrandId(String BrandId) {
            this.BrandId = BrandId;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(String CategoryId) {
            this.CategoryId = CategoryId;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public String getParentCategoryId() {
            return ParentCategoryId;
        }

        public void setParentCategoryId(String ParentCategoryId) {
            this.ParentCategoryId = ParentCategoryId;
        }

        public String getParentCategoryName() {
            return ParentCategoryName;
        }

        public void setParentCategoryName(String ParentCategoryName) {
            this.ParentCategoryName = ParentCategoryName;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String Count) {
            this.Count = Count;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String ProductImage) {
            this.ProductImage = ProductImage;
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

        public boolean isIsCanRefund() {
            return IsCanRefund;
        }

        public void setIsCanRefund(boolean IsCanRefund) {
            this.IsCanRefund = IsCanRefund;
        }

        public String getColorAlias() {
            return ColorAlias;
        }

        public void setColorAlias(String ColorAlias) {
            this.ColorAlias = ColorAlias;
        }

        public String getSizeAlias() {
            return SizeAlias;
        }

        public void setSizeAlias(String SizeAlias) {
            this.SizeAlias = SizeAlias;
        }

        public String getVersionAlias() {
            return VersionAlias;
        }

        public void setVersionAlias(String VersionAlias) {
            this.VersionAlias = VersionAlias;
        }

        public double getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(double EnabledRefundAmount) {
            this.EnabledRefundAmount = EnabledRefundAmount;
        }
    }
}
