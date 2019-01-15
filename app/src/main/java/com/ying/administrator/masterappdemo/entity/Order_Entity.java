package com.ying.administrator.masterappdemo.entity;

import java.util.List;

public class Order_Entity {

    /**
     * Id : 0
     * SendID : 0
     * CreateDate : 2019-01-14T01:24:22.307Z
     * UserID : string
     * OrderID : 0
     * State : string
     * UpdateDate : 2019-01-14T01:24:22.307Z
     * LoginUser : string
     * IsUse : string
     * Order_Entity : {"Id":0,"OrderID":0,"TypeID":0,"TypeName":"string","SubTypeID":0,"SubTypeName":"string","CategoryID":0,"CategoryName":"string","SubCategoryID":0,"SubCategoryName":"string","Memo":"string","BrandID":0,"BrandName":"string","ProductType":"string","ProvinceCode":"string","CityCode":"string","AreaCode":"string","Address":"string","UserID":"string","Guarantee":"string","UserName":"string","Phone":"string","CreateDate":"2019-01-14T01:24:22.307Z","AudDate":"2019-01-14T01:24:22.307Z","RepairCompleteDate":"2019-01-14T01:24:22.307Z","AppraiseDate":"2019-01-14T01:24:22.307Z","State":"string","Extra":"string","ExtraTime":"string","ExtraFee":0,"IsUse":"string","SendUser":"string","LoginUser":"string","IsPay":"string","OrderMoney":0,"BeyondMoney":0,"BeyondID":0,"BeyondState":"string","Accessory":"string","AccessorySequency":"string","AccessoryState":"string","SendState":"string","ApplyCancel":"string","UpdateTime":"2019-01-14T01:24:22.307Z","SendOrder":[{}],"OrderPayStr":"string","page":0,"limit":0,"Version":0}
     * TypeID : 0
     * TypeName : string
     * SubTypeID : 0
     * SubTypeName : string
     * Memo : string
     * BrandID : 0
     * BrandName : string
     * ProductType : string
     * ProvinceCode : string
     * CityCode : string
     * AreaCode : string
     * Address : string
     * Guarantee : string
     * UserName : string
     * Phone : string
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private int SendID;
    private String CreateDate;
    private String UserID;
    private int OrderID;
    private String State;
    private String UpdateDate;
    private String LoginUser;
    private String IsUse;
    private OrderBean Order;
    private int TypeID;
    private String TypeName;
    private int SubTypeID;
    private String SubTypeName;
    private String Memo;
    private int BrandID;
    private String BrandName;
    private String ProductType;
    private String ProvinceCode;
    private String CityCode;
    private String AreaCode;
    private String Address;
    private String Guarantee;
    private String UserName;
    private String Phone;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSendID() {
        return SendID;
    }

    public void setSendID(int SendID) {
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

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
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

    public OrderBean getOrder() {
        return Order;
    }

    public void setOrder(OrderBean Order) {
        this.Order = Order;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public int getSubTypeID() {
        return SubTypeID;
    }

    public void setSubTypeID(int SubTypeID) {
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

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public static class OrderBean {
        /**
         * Id : 0
         * OrderID : 0
         * TypeID : 0
         * TypeName : string
         * SubTypeID : 0
         * SubTypeName : string
         * CategoryID : 0
         * CategoryName : string
         * SubCategoryID : 0
         * SubCategoryName : string
         * Memo : string
         * BrandID : 0
         * BrandName : string
         * ProductType : string
         * ProvinceCode : string
         * CityCode : string
         * AreaCode : string
         * Address : string
         * UserID : string
         * Guarantee : string
         * UserName : string
         * Phone : string
         * CreateDate : 2019-01-14T01:24:22.307Z
         * AudDate : 2019-01-14T01:24:22.307Z
         * RepairCompleteDate : 2019-01-14T01:24:22.307Z
         * AppraiseDate : 2019-01-14T01:24:22.307Z
         * State : string
         * Extra : string
         * ExtraTime : string
         * ExtraFee : 0
         * IsUse : string
         * SendUser : string
         * LoginUser : string
         * IsPay : string
         * OrderMoney : 0
         * BeyondMoney : 0
         * BeyondID : 0
         * BeyondState : string
         * Accessory : string
         * AccessorySequency : string
         * AccessoryState : string
         * SendState : string
         * ApplyCancel : string
         * UpdateTime : 2019-01-14T01:24:22.307Z
         * SendOrder : [{}]
         * OrderPayStr : string
         * page : 0
         * limit : 0
         * Version : 0
         */

        private int Id;
        private int OrderID;
        private int TypeID;
        private String TypeName;
        private int SubTypeID;
        private String SubTypeName;
        private int CategoryID;
        private String CategoryName;
        private int SubCategoryID;
        private String SubCategoryName;
        private String Memo;
        private int BrandID;
        private String BrandName;
        private String ProductType;
        private String ProvinceCode;
        private String CityCode;
        private String AreaCode;
        private String Address;
        private String UserID;
        private String Guarantee;
        private String UserName;
        private String Phone;
        private String CreateDate;
        private String AudDate;
        private String RepairCompleteDate;
        private String AppraiseDate;
        private String State;
        private String Extra;
        private String ExtraTime;
        private int ExtraFee;
        private String IsUse;
        private String SendUser;
        private String LoginUser;
        private String IsPay;
        private int OrderMoney;
        private int BeyondMoney;
        private int BeyondID;
        private String BeyondState;
        private String Accessory;
        private String AccessorySequency;
        private String AccessoryState;
        private String SendState;
        private String ApplyCancel;
        private String UpdateTime;
        private String OrderPayStr;
        private int page;
        private int limit;
        private int Version;
        private List<SendOrderBean> SendOrder;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int TypeID) {
            this.TypeID = TypeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public int getSubTypeID() {
            return SubTypeID;
        }

        public void setSubTypeID(int SubTypeID) {
            this.SubTypeID = SubTypeID;
        }

        public String getSubTypeName() {
            return SubTypeName;
        }

        public void setSubTypeName(String SubTypeName) {
            this.SubTypeName = SubTypeName;
        }

        public int getCategoryID() {
            return CategoryID;
        }

        public void setCategoryID(int CategoryID) {
            this.CategoryID = CategoryID;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public int getSubCategoryID() {
            return SubCategoryID;
        }

        public void setSubCategoryID(int SubCategoryID) {
            this.SubCategoryID = SubCategoryID;
        }

        public String getSubCategoryName() {
            return SubCategoryName;
        }

        public void setSubCategoryName(String SubCategoryName) {
            this.SubCategoryName = SubCategoryName;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public int getBrandID() {
            return BrandID;
        }

        public void setBrandID(int BrandID) {
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

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
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

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getAudDate() {
            return AudDate;
        }

        public void setAudDate(String AudDate) {
            this.AudDate = AudDate;
        }

        public String getRepairCompleteDate() {
            return RepairCompleteDate;
        }

        public void setRepairCompleteDate(String RepairCompleteDate) {
            this.RepairCompleteDate = RepairCompleteDate;
        }

        public String getAppraiseDate() {
            return AppraiseDate;
        }

        public void setAppraiseDate(String AppraiseDate) {
            this.AppraiseDate = AppraiseDate;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getExtra() {
            return Extra;
        }

        public void setExtra(String Extra) {
            this.Extra = Extra;
        }

        public String getExtraTime() {
            return ExtraTime;
        }

        public void setExtraTime(String ExtraTime) {
            this.ExtraTime = ExtraTime;
        }

        public int getExtraFee() {
            return ExtraFee;
        }

        public void setExtraFee(int ExtraFee) {
            this.ExtraFee = ExtraFee;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getSendUser() {
            return SendUser;
        }

        public void setSendUser(String SendUser) {
            this.SendUser = SendUser;
        }

        public String getLoginUser() {
            return LoginUser;
        }

        public void setLoginUser(String LoginUser) {
            this.LoginUser = LoginUser;
        }

        public String getIsPay() {
            return IsPay;
        }

        public void setIsPay(String IsPay) {
            this.IsPay = IsPay;
        }

        public int getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(int OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public int getBeyondMoney() {
            return BeyondMoney;
        }

        public void setBeyondMoney(int BeyondMoney) {
            this.BeyondMoney = BeyondMoney;
        }

        public int getBeyondID() {
            return BeyondID;
        }

        public void setBeyondID(int BeyondID) {
            this.BeyondID = BeyondID;
        }

        public String getBeyondState() {
            return BeyondState;
        }

        public void setBeyondState(String BeyondState) {
            this.BeyondState = BeyondState;
        }

        public String getAccessory() {
            return Accessory;
        }

        public void setAccessory(String Accessory) {
            this.Accessory = Accessory;
        }

        public String getAccessorySequency() {
            return AccessorySequency;
        }

        public void setAccessorySequency(String AccessorySequency) {
            this.AccessorySequency = AccessorySequency;
        }

        public String getAccessoryState() {
            return AccessoryState;
        }

        public void setAccessoryState(String AccessoryState) {
            this.AccessoryState = AccessoryState;
        }

        public String getSendState() {
            return SendState;
        }

        public void setSendState(String SendState) {
            this.SendState = SendState;
        }

        public String getApplyCancel() {
            return ApplyCancel;
        }

        public void setApplyCancel(String ApplyCancel) {
            this.ApplyCancel = ApplyCancel;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getOrderPayStr() {
            return OrderPayStr;
        }

        public void setOrderPayStr(String OrderPayStr) {
            this.OrderPayStr = OrderPayStr;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }

        public List<SendOrderBean> getSendOrder() {
            return SendOrder;
        }

        public void setSendOrder(List<SendOrderBean> SendOrder) {
            this.SendOrder = SendOrder;
        }

        public static class SendOrderBean {
        }
    }
}
