package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Address implements Serializable {

    /**
     * Id : 385
     * ServiceAreaID : 385
     * UserID : 18892621500
     * ProvinceCode : 330000
     * ProvinceName : 浙江省
     * CityCode : 330300
     * CityName : 温州市
     * AreaCode : 330326
     * AreaName : 平阳县
     * DistrictCode : 330326100
     * DistrictName : 昆阳镇
     * IsUse : Y
     * Version : 0
     */

    private int Id;
    private int ServiceAreaID;
    private String UserID;
    private String ProvinceCode;
    private String ProvinceName;
    private String CityCode;
    private String CityName;
    private String AreaCode;
    private String AreaName;
    private String DistrictCode;
    private String DistrictName;
    private String IsUse;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getServiceAreaID() {
        return ServiceAreaID;
    }

    public void setServiceAreaID(int ServiceAreaID) {
        this.ServiceAreaID = ServiceAreaID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(String ProvinceCode) {
        this.ProvinceCode = ProvinceCode;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String ProvinceName) {
        this.ProvinceName = ProvinceName;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String AreaCode) {
        this.AreaCode = AreaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String DistrictCode) {
        this.DistrictCode = DistrictCode;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String DistrictName) {
        this.DistrictName = DistrictName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
