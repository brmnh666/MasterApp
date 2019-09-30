package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class ShopColor implements Serializable {


    /**
     * Name : 选择颜色
     * Value : 黑色
     * SkuId : 655
     * EnabledClass : enabled
     * SelectedClass :
     * Img : http://mall.xigyu.com//Storage/Shop/1/Products/707/skus/707_655_663_0.png
     */

    private String Name;
    private String Value;
    private String SkuId;
    private String EnabledClass;
    private String SelectedClass;
    private String Img;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getSkuId() {
        return SkuId;
    }

    public void setSkuId(String SkuId) {
        this.SkuId = SkuId;
    }

    public String getEnabledClass() {
        return EnabledClass;
    }

    public void setEnabledClass(String EnabledClass) {
        this.EnabledClass = EnabledClass;
    }

    public String getSelectedClass() {
        return SelectedClass;
    }

    public void setSelectedClass(String SelectedClass) {
        this.SelectedClass = SelectedClass;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
}
