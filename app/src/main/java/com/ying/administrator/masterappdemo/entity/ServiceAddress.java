package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class ServiceAddress implements Serializable {

    private Province province;
    private City city;
    private Area area;
    private District district;
    private String name;
    private String codestr;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String districtName;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String districtCode;


    public ServiceAddress(Province province, City city, Area area, District district) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.district = district;
        if(province==null){
            provinceName="";
            provinceCode="";
        }else{
            provinceName=province.getName();
            provinceCode=province.getCode();
        }
        if(city==null){
            cityName="";
            cityCode="";
        }else{
            cityName=city.getName();
            cityCode=city.getCode();
        }
        if(area==null){
            areaName="";
            areaCode="";
        }else{
            areaName=area.getName();
            areaCode=area.getCode();
        }
        if(district==null){
            districtName="";
            districtCode="";
        }else{
            districtName=district.getName();
            districtCode=district.getCode();
        }
        this.name=provinceName+cityName+areaName+districtName;
        this.codestr=provinceCode+"-"+cityCode+"-"+areaCode+"-"+districtCode;
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

