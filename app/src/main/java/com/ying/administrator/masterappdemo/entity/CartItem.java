package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class CartItem implements Serializable {


    /**
     * skuIds : [{"skuId":"232_90_0_101","count":"1"}]
     * userkey : RnlhdTc0bC9LL2ZqN28zc2FsOFMvQT09
     */

    private String userkey;
    private List<SkuIdsBean> skus;

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public List<SkuIdsBean> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuIdsBean> skus) {
        this.skus = skus;
    }

    public static class SkuIdsBean {
        /**
         * skuId : 232_90_0_101
         * count : 1
         */
        private String skuId;
        private String count;

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
