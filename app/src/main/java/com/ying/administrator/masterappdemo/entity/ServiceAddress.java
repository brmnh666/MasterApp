package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class ServiceAddress implements Serializable {

    private Province province;
    private City city;
    private Area area;
    private District district;
    private String name;
    private String codestr;

    public ServiceAddress(Province province, City city, Area area, District district) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.district = district;
        if (district==null){
            this.name=province.getName()+city.getName()+area.getName();
            this.codestr=province.getCode()+"-"+city.getCode()+"-"+area.getCode();
        }else{
            this.name=province.getName()+city.getName()+area.getName()+district.getName();
            this.codestr=province.getCode()+"-"+city.getCode()+"-"+area.getCode()+"-"+district.getCode();
        }
    }

    public String getCodestr() {
        return codestr;
    }

    public void setCodestr(String codestr) {
        this.codestr = codestr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}

