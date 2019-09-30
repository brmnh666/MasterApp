package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class ShopVersion implements Serializable {

    /**
     * Name : 选择规格
     * Value : i7处理器
     * SkuId : 687
     * EnabledClass : enabled
     * SelectedClass :
     * Img : null
     */

    private String Name;
    private String Value;
    private String SkuId;
    private String EnabledClass;
    private String SelectedClass;
    private Object Img;

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

    public Object getImg() {
        return Img;
    }

    public void setImg(Object Img) {
        this.Img = Img;
    }
}
