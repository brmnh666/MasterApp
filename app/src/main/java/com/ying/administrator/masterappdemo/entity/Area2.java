package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Area2 implements Serializable {


    /**
     * Id : string
     * code : string
     * name : string
     * parentcode : string
     * IsUse : string
     * Version : 0
     */

    private Area area;
    private boolean isCheck;

    public Area2(Area area, boolean isCheck) {
        this.area = area;
        this.isCheck = isCheck;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}

