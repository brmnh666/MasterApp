package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class FService {
    /**
     * OrderID : 123
     * OrderServiceStr : {"OrderService":[{"ServiceID":"1","ServiceName":"加氟","Price":"50","DiscountPrice":"50"},{"ServiceID":"2","ServiceName":"加氟2","Price":"50","DiscountPrice":"50"}]}
     */

    private String OrderID;
    private OrderServiceStrBean OrderServiceStr;


    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public OrderServiceStrBean getOrderServiceStr() {
        return OrderServiceStr;
    }

    public void setOrderServiceStr(OrderServiceStrBean OrderServiceStr) {
        this.OrderServiceStr = OrderServiceStr;
    }

    public static class OrderServiceStrBean {
        private List<OrderServiceBean> OrderService;

        public List<OrderServiceBean> getOrderService() {
            return OrderService;
        }

        public void setOrderService(List<OrderServiceBean> OrderService) {
            this.OrderService = OrderService;
        }

        public static class OrderServiceBean {
            /**
             * ServiceID : 1
             * ServiceName : 加氟
             * Price : 50
             * DiscountPrice : 50
             */

            private String FServiceID;
            private String FServiceName;
            private double Price;
            private double DiscountPrice;
            private String IsPay;
            private String Relation;
            private String SNeedPlatformAuth;
            private String State;
            private String CategoryID;
            private String SizeID;

            public String getSizeID() {
                return SizeID;
            }

            public void setSizeID(String sizeID) {
                SizeID = sizeID;
            }

            public String getFServiceID() {
                return FServiceID;
            }

            public void setFServiceID(String FServiceID) {
                this.FServiceID = FServiceID;
            }

            public String getFServiceName() {
                return FServiceName;
            }

            public void setFServiceName(String FServiceName) {
                this.FServiceName = FServiceName;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(double price) {
                Price = price;
            }

            public double getDiscountPrice() {
                return DiscountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
                DiscountPrice = discountPrice;
            }

            public String getIsPay() {
                return IsPay;
            }

            public void setIsPay(String isPay) {
                IsPay = isPay;
            }

            public String getRelation() {
                return Relation;
            }

            public void setRelation(String relation) {
                Relation = relation;
            }

            public String getSNeedPlatformAuth() {
                return SNeedPlatformAuth;
            }

            public void setSNeedPlatformAuth(String SNeedPlatformAuth) {
                this.SNeedPlatformAuth = SNeedPlatformAuth;
            }

            public String getState() {
                return State;
            }

            public void setState(String state) {
                State = state;
            }

            public String getCategoryID() {
                return CategoryID;
            }

            public void setCategoryID(String categoryID) {
                CategoryID = categoryID;
            }
        }
    }
    /*
    *//**
     * OrderID : 0
     * OrderServiceStr : string
     *//*

    private int OrderID;
    private String OrderServiceStr;
    private double ServicePrice;

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderServiceStr() {
        return OrderServiceStr;
    }

    public void setOrderServiceStr(String OrderServiceStr) {
        this.OrderServiceStr = OrderServiceStr;
    }

    public double getServicePrice() {
        return ServicePrice;
    }

    public void setServicePrice(double servicePrice) {
        ServicePrice = servicePrice;
    }*/



}
