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

            private String ServiceID;
            private String ServiceName;
            private String Price;
            private double DiscountPrice;

            public String getServiceID() {
                return ServiceID;
            }

            public void setServiceID(String ServiceID) {
                this.ServiceID = ServiceID;
            }

            public String getServiceName() {
                return ServiceName;
            }

            public void setServiceName(String ServiceName) {
                this.ServiceName = ServiceName;
            }

            public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }

            public double getDiscountPrice() {
                return DiscountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
                DiscountPrice = discountPrice;
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
