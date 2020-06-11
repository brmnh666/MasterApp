package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class AddressList implements Serializable {
    /**
     * Id : 12
     * AccountAdressID : 12
     * UserID : 18767773654
     * Province : 内蒙古自治区
     * City : 通辽市
     * Area : 奈曼旗
     * District : 土城子镇
     * Address : 内蒙古自治区通辽市奈曼旗土城子镇咯
     * IsDefault : 0
     * IsUse : Y
     * UserName : 正海科技
     * Phone : 180 7420 8209
     * Version : 0
     */

    private String Id;
    private String AccountAdressID;
    private String UserID;
    private String Province;
    private String City;
    private String Area;
    private String District;
    private String Address;
    private String IsDefault;
    private String IsUse;
    private String UserName;
    private String Phone;
    private String Version;
    private String addrStr;

    public String getAddrStr() {
        Province=(Province==null?"":Province);
        City=(City==null?"":City);
        Area=(Area==null?"":Area);
        District=(District==null?"":District);
        addrStr=Province+City+Area+District+Address;
        return addrStr;
    }

    public void setAddrStr(String addrStr) {
        this.addrStr = addrStr;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAccountAdressID() {
        return AccountAdressID;
    }

    public void setAccountAdressID(String AccountAdressID) {
        this.AccountAdressID = AccountAdressID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getProvince() {
        return Province==null?"":Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City==null?"":City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getArea() {
        return Area==null?"":Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(String IsDefault) {
        this.IsDefault = IsDefault;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}
