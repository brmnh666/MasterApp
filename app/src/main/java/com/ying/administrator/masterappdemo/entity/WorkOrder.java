package com.ying.administrator.masterappdemo.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WorkOrder implements Serializable {


    /**
     * code : 0
     * msg : success
     * count : 1
     * data : [{"Id":1,"OrderID":1,"TypeID":2,"TypeName":"上门安装","SubTypeID":11,"SubTypeName":"电视机","CategoryID":1,"CategoryName":"大家电","SubCategoryID":1,"SubCategoryName":"电视机","Memo":"123","BrandID":1,"BrandName":"松下","ProductType":"TV02","ProvinceCode":"330000","CityCode":"330200","AreaCode":"330205","Address":"奔腾科技园","UserID":"admin","Guarantee":"N","UserName":"邰振江","Phone":"17681886869","CreateDate":"2019-01-11T16:21:37","AudDate":"2019-01-11T16:21:37","RepairCompleteDate":"2019-01-11T16:21:37","AppraiseDate":"2019-01-11T16:21:37","State":"0","Extra":"Y","ExtraTime":null,"ExtraFee":0,"IsUse":"Y","SendUser":"admin","LoginUser":"system","IsPay":"Y","OrderMoney":0,"BeyondMoney":0,"BeyondID":0,"BeyondState":"0","BeyondDistance":null,"Accessory":null,"AccessorySequency":null,"AccessoryState":null,"AccessorySendState":null,"AccessoryMoney":0,"Service":null,"ServiceMoney":0,"ReturnAccessory":null,"ReturnAccessoryMsg":null,"ApplyCancel":null,"UpdateTime":"2019-01-11T16:21:37","SendOrder":[{"Id":2,"SendID":2,"CreateDate":"2018-12-27T16:19:33","UserID":"admin","OrderID":1,"State":"1","UpdateDate":null,"LoginUser":"system","IsUse":"Y","CategoryID":0,"CategoryName":null,"SubTypeID":0,"SubTypeName":null,"Memo":null,"BrandID":0,"BrandName":null,"ProductType":null,"ProvinceCode":null,"CityCode":null,"AreaCode":null,"Address":null,"Guarantee":null,"UserName":null,"Phone":null,"page":1,"limit":10,"Version":0}],"OrderPayStr":null,"ThirdPartyNo":null,"ExpressNo":null,"RecycleOrderHour":0,"IsRecevieGoods":null,"page":0,"limit":0,"Version":0}]
     */

    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 1
         * OrderID : 1
         * TypeID : 2
         * TypeName : 上门安装
         * SubTypeID : 11
         * SubTypeName : 电视机
         * CategoryID : 1
         * CategoryName : 大家电
         * SubCategoryID : 1
         * SubCategoryName : 电视机
         * Memo : 123
         * BrandID : 1
         * BrandName : 松下
         * ProductType : TV02
         * ProvinceCode : 330000
         * CityCode : 330200
         * AreaCode : 330205
         * Address : 奔腾科技园
         * UserID : admin
         * Guarantee : N
         * UserName : 邰振江
         * Phone : 17681886869
         * CreateDate : 2019-01-11T16:21:37
         * AudDate : 2019-01-11T16:21:37
         * RepairCompleteDate : 2019-01-11T16:21:37
         * AppraiseDate : 2019-01-11T16:21:37
         * State : 0
         * Extra : Y
         * ExtraTime : null
         * ExtraFee : 0.0
         * IsUse : Y
         * SendUser : admin
         * LoginUser : system
         * IsPay : Y
         * OrderMoney : 0.0
         * BeyondMoney : 0.0
         * BeyondID : 0
         * BeyondState : 0
         * BeyondDistance : null
         * Accessory : null
         * AccessorySequency : null
         * AccessoryState : null
         * AccessorySendState : null
         * AccessoryMoney : 0.0
         * Service : null
         * ServiceMoney : 0.0
         * ReturnAccessory : null
         * ReturnAccessoryMsg : null
         * ApplyCancel : null
         * UpdateTime : 2019-01-11T16:21:37
         * SendOrder : [{"Id":2,"SendID":2,"CreateDate":"2018-12-27T16:19:33","UserID":"admin","OrderID":1,"State":"1","UpdateDate":null,"LoginUser":"system","IsUse":"Y","CategoryID":0,"CategoryName":null,"SubTypeID":0,"SubTypeName":null,"Memo":null,"BrandID":0,"BrandName":null,"ProductType":null,"ProvinceCode":null,"CityCode":null,"AreaCode":null,"Address":null,"Guarantee":null,"UserName":null,"Phone":null,"page":1,"limit":10,"Version":0}]
         * OrderPayStr : null
         * ThirdPartyNo : null
         * ExpressNo : null
         * RecycleOrderHour : 0
         * IsRecevieGoods : null
         * page : 0
         * limit : 0
         * Version : 0
         */

        private String Id;
        private String OrderID;
        private String TypeID;
        private String TypeName;
        private String SubTypeID;
        private String SubTypeName;
        private String CategoryID;
        private String CategoryName;
        private String SubCategoryID;
        private String SubCategoryName;
        private String Memo;
        private String BrandID;
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
        private String ExtraFee;
        private String IsUse;
        private String SendUser;
        private String LoginUser;
        private String IsPay;
        private String OrderMoney;
        private String BeyondMoney;
        private String BeyondID;
        private String BeyondState;
        private String BeyondDistance;
        private String Accessory;
        private String AccessorySequency;
        private String AccessoryState;
        private String AccessorySendState;
        private String AccessoryMoney;
        private String Service;
        private String ServiceMoney;
        private String ReturnAccessory;
        private String ReturnAccessoryMsg;
        private String ApplyCancel;
        private String UpdateTime;
        private String OrderPayStr;
        private String ThirdPartyNo;
        private String ExpressNo;
        private String RecycleOrderHour;
        private String IsRecevieGoods;
        private String page;
        private String limit;
        private String Version;
        private String Num;
        private List<SendOrderBean> SendOrder;

        public String getNum() {
            return Num;
        }

        public void setNum(String num) {
            Num = num;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public String getTypeID() {
            return TypeID;
        }

        public void setTypeID(String TypeID) {
            this.TypeID = TypeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
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

        public String getSubCategoryID() {
            return SubCategoryID;
        }

        public void setSubCategoryID(String SubCategoryID) {
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
            return AudDate.replace("T", " ");
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

        public String getExtraFee() {
            return ExtraFee;
        }

        public void setExtraFee(String ExtraFee) {
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
            if ("Y".equals(IsPay)){
                return "已支付";
            }else{
                return "未支付";
            }
        }

        public void setIsPay(String IsPay) {
            this.IsPay = IsPay;
        }

        public String getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(String OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public String getBeyondMoney() {
            return BeyondMoney;
        }

        public void setBeyondMoney(String BeyondMoney) {
            this.BeyondMoney = BeyondMoney;
        }

        public String getBeyondID() {
            return BeyondID;
        }

        public void setBeyondID(String BeyondID) {
            this.BeyondID = BeyondID;
        }

        public String getBeyondState() {
            return BeyondState;
        }

        public void setBeyondState(String BeyondState) {
            this.BeyondState = BeyondState;
        }

        public String getBeyondDistance() {
            return BeyondDistance;
        }

        public void setBeyondDistance(String BeyondDistance) {
            this.BeyondDistance = BeyondDistance;
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

        public String getAccessorySendState() {
            return AccessorySendState;
        }

        public void setAccessorySendState(String AccessorySendState) {
            this.AccessorySendState = AccessorySendState;
        }

        public String getAccessoryMoney() {
            return AccessoryMoney;
        }

        public void setAccessoryMoney(String AccessoryMoney) {
            this.AccessoryMoney = AccessoryMoney;
        }

        public String getService() {
            return Service;
        }

        public void setService(String Service) {
            this.Service = Service;
        }

        public String getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(String ServiceMoney) {
            this.ServiceMoney = ServiceMoney;
        }

        public String getReturnAccessory() {
            return ReturnAccessory;
        }

        public void setReturnAccessory(String ReturnAccessory) {
            this.ReturnAccessory = ReturnAccessory;
        }

        public String getReturnAccessoryMsg() {
            return ReturnAccessoryMsg;
        }

        public void setReturnAccessoryMsg(String ReturnAccessoryMsg) {
            this.ReturnAccessoryMsg = ReturnAccessoryMsg;
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

        public String getThirdPartyNo() {
            return ThirdPartyNo;
        }

        public void setThirdPartyNo(String ThirdPartyNo) {
            this.ThirdPartyNo = ThirdPartyNo;
        }

        public String getExpressNo() {
            return ExpressNo;
        }

        public void setExpressNo(String ExpressNo) {
            this.ExpressNo = ExpressNo;
        }

        public String getRecycleOrderHour() {
            return RecycleOrderHour;
        }

        public void setRecycleOrderHour(String RecycleOrderHour) {
            this.RecycleOrderHour = RecycleOrderHour;
        }

        public String getIsRecevieGoods() {
            return IsRecevieGoods;
        }

        public void setIsRecevieGoods(String IsRecevieGoods) {
            this.IsRecevieGoods = IsRecevieGoods;
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

        public List<SendOrderBean> getSendOrder() {
            return SendOrder;
        }

        public void setSendOrder(List<SendOrderBean> SendOrder) {
            this.SendOrder = SendOrder;
        }

        public static class SendOrderBean {
            /**
             * Id : 2
             * SendID : 2
             * CreateDate : 2018-12-27T16:19:33
             * UserID : admin
             * OrderID : 1
             * State : 1
             * UpdateDate : null
             * LoginUser : system
             * IsUse : Y
             * CategoryID : 0
             * CategoryName : null
             * SubTypeID : 0
             * SubTypeName : null
             * Memo : null
             * BrandID : 0
             * BrandName : null
             * ProductType : null
             * ProvinceCode : null
             * CityCode : null
             * AreaCode : null
             * Address : null
             * Guarantee : null
             * UserName : null
             * Phone : null
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
    }
}
