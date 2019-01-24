package com.ying.administrator.masterappdemo.entity;

public class FAccessory {
    private String FAccessoryID;
    private String FAccessoryName;
    private String Quantity;
    private String Price;
    private int DiscountPrice;

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

    public int getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        DiscountPrice = discountPrice;
    }
}
