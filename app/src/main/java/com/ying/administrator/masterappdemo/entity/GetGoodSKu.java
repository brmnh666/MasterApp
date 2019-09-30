package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class GetGoodSKu implements Serializable {


    /**
     * Success : true
     * SkuArray : [{"SKUId":"8;13;0","Price":299,"Stock":98},{"SKUId":"10;13;0","Price":299,"Stock":100},{"SKUId":"8;12;0","Price":299,"Stock":100},{"SKUId":"10;12;0","Price":299,"Stock":96}]
     */

    private String Success;
    private List<SkuArrayBean> SkuArray;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<SkuArrayBean> getSkuArray() {
        return SkuArray;
    }

    public void setSkuArray(List<SkuArrayBean> SkuArray) {
        this.SkuArray = SkuArray;
    }

    public static class SkuArrayBean {
        /**
         * SKUId : 8;13;0
         * Price : 299
         * Stock : 98
         */

        private String SkuId;
        private String Price;
        private int Stock;

        public String getSkuId() {
            return SkuId;
        }

        public void setSkuId(String skuId) {
            SkuId = skuId;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public int getStock() {
            return Stock;
        }

        public void setStock(int stock) {
            Stock = stock;
        }
    }
}
