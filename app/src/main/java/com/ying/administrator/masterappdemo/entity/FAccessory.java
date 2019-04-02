package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class FAccessory implements Serializable {
    /**
     * OrderID : 123
     * AccessorySequency : 0
     * OrderAccessoryStr : {"OrderAccessory":[{"FAccessoryID":"1","FAccessoryName":"PC管","Quantity":"2","Price":"1","DiscountPrice":"1"},{"FAccessoryID":"1","FAccessoryName":"PC管","Quantity":"2","Price":"1","DiscountPrice":"1"}]}
     */

    private String OrderID;
    private String AccessorySequency;
    private OrderAccessoryStrBean OrderAccessoryStr;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getAccessorySequency() {
        return AccessorySequency;
    }

    public void setAccessorySequency(String AccessorySequency) {
        this.AccessorySequency = AccessorySequency;
    }

    public OrderAccessoryStrBean getOrderAccessoryStr() {
        return OrderAccessoryStr;
    }

    public void setOrderAccessoryStr(OrderAccessoryStrBean OrderAccessoryStr) {
        this.OrderAccessoryStr = OrderAccessoryStr;
    }

    public static class OrderAccessoryStrBean {
        private String Memo;
        private List<OrderAccessoryBean> OrderAccessory;

        public List<OrderAccessoryBean> getOrderAccessory() {
            return OrderAccessory;
        }

        public void setOrderAccessory(List<OrderAccessoryBean> OrderAccessory) {
            this.OrderAccessory = OrderAccessory;
        }

        public String getAccessoryMemo() {
            return Memo;
        }

        public void setAccessoryMemo(String accessoryMemo) {
            Memo = accessoryMemo;
        }

        public static class OrderAccessoryBean {
            /**
             * FAccessoryID : 1
             * FAccessoryName : PC管
             * Quantity : 2
             * Price : 1
             * DiscountPrice : 1
             */

            private String FAccessoryID;
            private String FAccessoryName;
            private String Quantity;
            private String SendState;
            private String Relation;
            private String IsPay;
            private String ExpressNo;
            private String State;
            private double Price;
            private double DiscountPrice;

            public String getState() {
                return State;
            }

            public void setState(String state) {
                State = state;
            }

            public String getExpressNo() {
                return ExpressNo;
            }

            public void setExpressNo(String expressNo) {
                ExpressNo = expressNo;
            }

            public String getSendState() {
                return SendState;
            }

            public void setSendState(String sendState) {
                SendState = sendState;
            }

            public String getRelation() {
                return Relation;
            }

            public void setRelation(String relation) {
                Relation = relation;
            }

            public String getIsPay() {
                return IsPay;
            }

            public void setIsPay(String isPay) {
                IsPay = isPay;
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

            public String getQuantity() {
                return Quantity;
            }

            public void setQuantity(String quantity) {
                Quantity = quantity;
            }

            /*public String getPrice() {
                return Price;
            }

            public void setPrice(String Price) {
                this.Price = Price;
            }*/

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
        }
    }
  /*  private String FAccessoryID;
    private String FAccessoryName;
    private String Quantity;
    private String Price;
    private double DiscountPrice;

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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        DiscountPrice = discountPrice;
    }*/



}
