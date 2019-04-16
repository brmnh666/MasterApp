package com.ying.administrator.masterappdemo.entity;

import com.blankj.utilcode.util.TimeUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendOrderBean implements Serializable {

    /**
     * Id : 577
     * SendID : 577
     * CreateDate : 2019-04-02T16:54:33
     * UserID : 13806652840
     * OrderID : 2000000636
     * State : 1
     * UpdateDate : 2019-04-02T16:54:42
     * ServiceDate : null
     * ServiceDate2 : null
     * LoginUser : system
     * IsUse : Y
     * CategoryID : 250
     * CategoryName : 冰箱
     * SubTypeID : 0
     * SubTypeName : null
     * Memo : 测试测试测试测试
     * BrandID : 77
     * BrandName : 虾米
     * ProductType : null
     * ProvinceCode : 360000
     * CityCode : 360100
     * AreaCode : 360102
     * Address : 江西省南昌市东湖区公园街道叠山路110号戴家巷社区 
     * Guarantee : Y
     * UserName : 又来
     * Phone : 18767773654
     * AppointmentState : 1
     * AppointmentMessage : 预约成功
     * page : 1
     * limit : 10
     * Version : 0
     */

    private String Id;
    private String SendID;
    private String CreateDate;
    private String UserID;
    private String OrderID;
    private String State;
    private String UpdateDate;
    private String ServiceDate;
    private String ServiceDate2;
    private String LoginUser;
    private String IsUse;
    private String CategoryID;
    private String CategoryName;
    private String SubTypeID;
    private String SubTypeName;
    private String Memo;
    private String BrandID;
    private String BrandName;
    private String ProductType;
    private String ProvinceCode;
    private String CityCode;
    private String AreaCode;
    private String Address;
    private String Guarantee;
    private String UserName;
    private String Phone;
    private String AppointmentState;
    private String AppointmentMessage;
    private String page;
    private String limit;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSendID() {
        return SendID;
    }

    public void setSendID(String SendID) {
        this.SendID = SendID;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public String getServiceDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return ServiceDate==null?"":format.format(TimeUtils.string2Date(ServiceDate.replace("T"," ")));
    }

    public void setServiceDate(String ServiceDate) {
        this.ServiceDate = ServiceDate;
    }

    public String getServiceDate2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return ServiceDate2==null?"":format.format(TimeUtils.string2Date(ServiceDate2.replace("T"," ")));
    }

    public void setServiceDate2(String ServiceDate2) {
        this.ServiceDate2 = ServiceDate2;
    }

    public String getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(String LoginUser) {
        this.LoginUser = LoginUser;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getSubTypeID() {
        return SubTypeID;
    }

    public void setSubTypeID(String SubTypeID) {
        this.SubTypeID = SubTypeID;
    }

    public String getSubTypeName() {
        return SubTypeName;
    }

    public void setSubTypeName(String SubTypeName) {
        this.SubTypeName = SubTypeName;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String Memo) {
        this.Memo = Memo;
    }

    public String getBrandID() {
        return BrandID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public String getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(String ProvinceCode) {
        this.ProvinceCode = ProvinceCode;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String AreaCode) {
        this.AreaCode = AreaCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getGuarantee() {
        return Guarantee;
    }

    public void setGuarantee(String Guarantee) {
        this.Guarantee = Guarantee;
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

    public String getAppointmentState() {
        return AppointmentState;
    }

    public void setAppointmentState(String AppointmentState) {
        this.AppointmentState = AppointmentState;
    }

    public String getAppointmentMessage() {
        return AppointmentMessage;
    }

    public void setAppointmentMessage(String AppointmentMessage) {
        this.AppointmentMessage = AppointmentMessage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}
