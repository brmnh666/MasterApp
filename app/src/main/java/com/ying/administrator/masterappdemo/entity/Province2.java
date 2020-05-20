package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Province2 implements Serializable {


    /**
     * Id : string
     * code : string
     * name : string
     * IsUse : string
     * Version : 0
     */

    private boolean isCheck;
    private Province province;
    private List<City2> cities = new ArrayList<City2>();

    public Province2(boolean isCheck, Province province, List<City2> cities) {
        this.isCheck = isCheck;
        this.province = province;
        this.cities = cities;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<City2> getCities() {
        return cities;
    }

    public void setCities(List<City2> cities) {
        this.cities = cities;
    }
}

