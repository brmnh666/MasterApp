package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class City2 implements Serializable {


    /**
     * Id : string
     * code : string
     * name : string
     * parentcode : string
     * IsUse : string
     * Version : 0
     */

    private City city;
    private boolean isCheck;
    private List<Area2> counties = new ArrayList<Area2>();

    public City2(City city, boolean isCheck, List<Area2> counties) {
        this.city = city;
        this.isCheck = isCheck;
        this.counties = counties;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public List<Area2> getCounties() {
        return counties;
    }

    public void setCounties(List<Area2> counties) {
        this.counties = counties;
    }
}

