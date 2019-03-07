package com.ying.administrator.masterappdemo.entity;


import java.io.Serializable;
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
         * Num : string
         * ProvinceCode : string
         * CityCode : string
         * AreaCode : string
         * DistrictCode : string
         * Address : string
         * Longitude : string
         * Dimension : string
         * UserID : string
         * Guarantee : string
         * UserName : string
         * Phone : string
         * CreateDate : 2019-02-27T01:45:22.583Z
         * AudDate : 2019-02-27T01:45:22.583Z
         * RepairCompleteDate : 2019-02-27T01:45:22.583Z
         * AppraiseDate : 2019-02-27T01:45:22.583Z
         * State : string
         * StateHtml : string
         * Extra : string
         * ExtraTime : string
         * ExtraFee : 0
         * IsUse : string
         * SendUser : string
         * OrgSendUser : string
         * LoginUser : string
         * IsPay : string
         * OrderMoney : 0
         * InitMoney : 0
         * BeyondMoney : 0
         * BeyondID : 0
         * BeyondState : string
         * BeyondDistance : string
         * Accessory : string
         * AccessorySequency : string
         * AccessoryApplyState : string
         * AccessoryState : string
         * AccessorySendState : string
         * AccessoryMoney : 0
         * Service : string
         * ServiceMoney : 0
         * ReturnAccessory : string
         * ReturnAccessoryMsg : string
         * ApplyCancel : string
         * UpdateTime : 2019-02-27T01:45:22.583Z
         * OrderPayStr : string
         * ThirdPartyNo : string
         * ExpressNo : string
         * RecycleOrderHour : 0
         * IsRecevieGoods : string
         * AppointmentMessage : string
         * AppointmentState : string
         * page : 0
         * limit : 0
         * Version : 0
         */

        private int Id;
        private String OrderID;
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
        private String Num;
        private String ProvinceCode;
        private String CityCode;
        private String AreaCode;
        private String DistrictCode;
        private String Address;
        private String Longitude;
        private String Dimension;
        private String UserID;
        private String Guarantee;
        private String UserName;
        private String Phone;
        private String CreateDate;
        private String AudDate;
        private String RepairCompleteDate;
        private String AppraiseDate;
        private String State;
        private String StateHtml;
        private String Extra;
        private String ExtraTime;
        private double ExtraFee;
        private String IsUse;
        private String SendUser;
        private String OrgSendUser;
        private String LoginUser;
        private String IsPay;
        private double OrderMoney;
        private double InitMoney;
        private double BeyondMoney;
        private int BeyondID;
        private String BeyondState;
        private String BeyondDistance;
        private String Accessory;
        private String AccessorySequency;
        private String AccessoryApplyState;
        private String AccessoryState;
        private String AccessorySendState;
        private int AccessoryMoney;
        private String Service;
        private int ServiceMoney;
        private String ReturnAccessory;
        private String ReturnAccessoryMsg;
        private String ApplyCancel;
        private String UpdateTime;
        private String OrderPayStr;
        private String ThirdPartyNo;
        private String ExpressNo;
        private int RecycleOrderHour;
        private String IsRecevieGoods;
        private String AppointmentMessage;
        private String AppointmentState;
        private List<GAccessory> OrderAccessroyDetail; //所选配件详情
        private List<GService> OrderServiceDetail;  //所选配件详情
        private String Distance;//返回距离
        private int page;
        private int limit;
        private int Version;

        public List<GAccessory> getOrderAccessroyDetail() {
            return OrderAccessroyDetail;
        }

        public void setOrderAccessroyDetail(List<GAccessory> orderAccessroyDetail) {
            OrderAccessroyDetail = orderAccessroyDetail;
        }

        public List<GService> getOrderServiceDetail() {
            return OrderServiceDetail;
        }

        public void setOrderServiceDetail(List<GService> orderServiceDetail) {
            OrderServiceDetail = orderServiceDetail;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
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

        public String getNum() {
            return Num;
        }

        public void setNum(String Num) {
            this.Num = Num;
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

        public String getDistrictCode() {
            return DistrictCode;
        }

        public void setDistrictCode(String DistrictCode) {
            this.DistrictCode = DistrictCode;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getDimension() {
            return Dimension;
        }

        public void setDimension(String Dimension) {
            this.Dimension = Dimension;
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
            return AudDate.replace("T"," ");
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

        public String getStateHtml() {
            return StateHtml;
        }

        public void setStateHtml(String StateHtml) {
            this.StateHtml = StateHtml;
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

        public double getExtraFee() {
            return ExtraFee;
        }

        public void setExtraFee(double ExtraFee) {
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

        public String getOrgSendUser() {
            return OrgSendUser;
        }

        public void setOrgSendUser(String OrgSendUser) {
            this.OrgSendUser = OrgSendUser;
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

        public double getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(double OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public double getInitMoney() {
            return InitMoney;
        }

        public void setInitMoney(double InitMoney) {
            this.InitMoney = InitMoney;
        }

        public double getBeyondMoney() {
            return BeyondMoney;
        }

        public void setBeyondMoney(double BeyondMoney) {
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

        public String getAccessoryApplyState() {
            return AccessoryApplyState;
        }

        public void setAccessoryApplyState(String AccessoryApplyState) {
            this.AccessoryApplyState = AccessoryApplyState;
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

        public int getAccessoryMoney() {
            return AccessoryMoney;
        }

        public void setAccessoryMoney(int AccessoryMoney) {
            this.AccessoryMoney = AccessoryMoney;
        }

        public String getService() {
            return Service;
        }

        public void setService(String Service) {
            this.Service = Service;
        }

        public int getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(int ServiceMoney) {
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

        public int getRecycleOrderHour() {
            return RecycleOrderHour;
        }

        public void setRecycleOrderHour(int RecycleOrderHour) {
            this.RecycleOrderHour = RecycleOrderHour;
        }

        public String getIsRecevieGoods() {
            return IsRecevieGoods;
        }

        public void setIsRecevieGoods(String IsRecevieGoods) {
            this.IsRecevieGoods = IsRecevieGoods;
        }

        public String getAppointmentMessage() {
            return AppointmentMessage;
        }

        public void setAppointmentMessage(String AppointmentMessage) {
            this.AppointmentMessage = AppointmentMessage;
        }

        public String getAppointmentState() {
            return AppointmentState;
        }

        public void setAppointmentState(String AppointmentState) {
            this.AppointmentState = AppointmentState;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String distance) {
            Distance = distance;
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
    }
}
