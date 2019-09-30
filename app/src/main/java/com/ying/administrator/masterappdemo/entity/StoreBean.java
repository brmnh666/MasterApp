package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

/*用于购物车店铺实体类*/
public class StoreBean implements Serializable {
    private boolean ischeck; //判断是否勾选
    private String ShopName;//店铺名
    private String ShopLogo;//店铺标志
    private OneCoupons oneCoupons;//店铺中领的优惠券
    private String ProvideInvoice;

    public String getProvideInvoice() {
        return ProvideInvoice;
    }

    public void setProvideInvoice(String provideInvoice) {
        ProvideInvoice = provideInvoice;
    }

    private List<CommodityBean> list; //店铺中的商品

    public OneCoupons getOneCoupons() {
        return oneCoupons;
    }

    public void setOneCoupons(OneCoupons oneCoupons) {
        this.oneCoupons = oneCoupons;
    }

    /*领取的优惠券*/
    public static class OneCoupons {
        /**
         * BaseId : 922
         * BasePrice : 99.0
         * BaseName : 新用户优惠券
         * BaseType : 0
         * BaseShopName : 官方自营店
         * BaseEndTime : 2019-06-03T00:00:00
         * BaseShopId : 1
         * OrderAmount : 0.0
         */

        private int BaseId;
        private double BasePrice;
        private String BaseName;
        private int BaseType;
        private String BaseShopName;
        private String BaseEndTime;
        private int BaseShopId;
        private double OrderAmount;

        public int getBaseId() {
            return BaseId;
        }

        public void setBaseId(int BaseId) {
            this.BaseId = BaseId;
        }

        public double getBasePrice() {
            return BasePrice;
        }

        public void setBasePrice(double BasePrice) {
            this.BasePrice = BasePrice;
        }

        public String getBaseName() {
            return BaseName;
        }

        public void setBaseName(String BaseName) {
            this.BaseName = BaseName;
        }

        public int getBaseType() {
            return BaseType;
        }

        public void setBaseType(int BaseType) {
            this.BaseType = BaseType;
        }

        public String getBaseShopName() {
            return BaseShopName;
        }

        public void setBaseShopName(String BaseShopName) {
            this.BaseShopName = BaseShopName;
        }

        public String getBaseEndTime() {
            return BaseEndTime;
        }

        public void setBaseEndTime(String BaseEndTime) {
            this.BaseEndTime = BaseEndTime;
        }

        public int getBaseShopId() {
            return BaseShopId;
        }

        public void setBaseShopId(int BaseShopId) {
            this.BaseShopId = BaseShopId;
        }

        public double getOrderAmount() {
            return OrderAmount;
        }

        public void setOrderAmount(double OrderAmount) {
            this.OrderAmount = OrderAmount;
        }


    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopLogo() {
        return ShopLogo;
    }

    public void setShopLogo(String shopLogo) {
        ShopLogo = shopLogo;
    }


    public List<CommodityBean> getList() {
        return list;
    }

    public void setList(List<CommodityBean> list) {
        this.list = list;
    }


}
