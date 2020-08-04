package com.ying.administrator.masterappdemo.v3.bean;

import java.util.List;

public class UpdateAccessoryRequest {

    /**
     * AccessoryID : 2682
     * FAccessoryID :
     * FAccessoryName : 测试1
     * Quantity : 1
     * Price : 0
     * ImgUrls : ["1.jpg"]
     * AccessoryState : 0
     */

    private String AccessoryID;
    private String FAccessoryID;
    private String FAccessoryName;
    private String Quantity;
    private int Price;
    private String AccessoryState;
    private List<String> ImgUrls;

    public String getAccessoryID() {
        return AccessoryID;
    }

    public void setAccessoryID(String AccessoryID) {
        this.AccessoryID = AccessoryID;
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

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(String AccessoryState) {
        this.AccessoryState = AccessoryState;
    }

    public List<String> getImgUrls() {
        return ImgUrls;
    }

    public void setImgUrls(List<String> ImgUrls) {
        this.ImgUrls = ImgUrls;
    }
}
