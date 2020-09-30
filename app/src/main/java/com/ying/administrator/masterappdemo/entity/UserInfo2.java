package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class UserInfo2 implements Serializable {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":"0","msg":"success","count":"1","data":[{"Id":"15608079129","UserID":"15608079129","NickName":"15608079129","Avator":"b83a74fcc7734764b9b4dbf896881820.jpeg","CreateDate":"2020-03-04T16:24:36","LastLoginDate":"2020-05-12T10:46:48","LoginCount":105,"RemainMoney":0,"TotalMoney":236,"FrozenMoney":0,"Type":"7","TopRank":"3","YnAuth":null,"barCode":null,"IsUse":"Y","PassWord":"xiao123456","PayPassWord":"452464","RoleID":15,"RoleName":"维修师傅","AccountID":3938,"DistrictCode":"330202007","Con":0,"Longitude":"121.464839","Dimension":"29.94471","ServiceTotalOrderNum":"62","ServiceComplaintNum":"0","UnfinishedAmount":389,"ServiceTotalMoney":0,"CompanyName":"未填写公司名称","OrderFee":0,"FeeTypes":null,"DoorFee":0,"AgainMoney":0,"StartDate":null,"EndDate":null,"ProvinceCode":"330000","CityCode":"330200","AreaCode":"330202","Address":"浙江省宁波市江北区洪塘街道海达大厦","DepositMoney":0,"DepositFrozenMoney":0,"Skills":null,"IfAuth":"1","AuthMessage":"","ParentUserID":null,"TrueName":"肖","IDCard":"510626198606285614","Sex":"男","Phone":"15608079129","emergencyContact":null,"teamNumber":2,"IsOrNoTruck":"N","page":0,"limit":0,"Version":0}]}
     */

    private int StatusCode;
    private String Info;
    private int Count;
    private DataBeanX Data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public DataBeanX getData() {
        return Data;
    }

    public void setData(DataBeanX Data) {
        this.Data = Data;
    }

    public static class DataBeanX {
        /**
         * code : 0
         * msg : success
         * count : 1
         * data : [{"Id":"15608079129","UserID":"15608079129","NickName":"15608079129","Avator":"b83a74fcc7734764b9b4dbf896881820.jpeg","CreateDate":"2020-03-04T16:24:36","LastLoginDate":"2020-05-12T10:46:48","LoginCount":105,"RemainMoney":0,"TotalMoney":236,"FrozenMoney":0,"Type":"7","TopRank":"3","YnAuth":null,"barCode":null,"IsUse":"Y","PassWord":"xiao123456","PayPassWord":"452464","RoleID":15,"RoleName":"维修师傅","AccountID":3938,"DistrictCode":"330202007","Con":0,"Longitude":"121.464839","Dimension":"29.94471","ServiceTotalOrderNum":"62","ServiceComplaintNum":"0","UnfinishedAmount":389,"ServiceTotalMoney":0,"CompanyName":"未填写公司名称","OrderFee":0,"FeeTypes":null,"DoorFee":0,"AgainMoney":0,"StartDate":null,"EndDate":null,"ProvinceCode":"330000","CityCode":"330200","AreaCode":"330202","Address":"浙江省宁波市江北区洪塘街道海达大厦","DepositMoney":0,"DepositFrozenMoney":0,"Skills":null,"IfAuth":"1","AuthMessage":"","ParentUserID":null,"TrueName":"肖","IDCard":"510626198606285614","Sex":"男","Phone":"15608079129","emergencyContact":null,"teamNumber":2,"IsOrNoTruck":"N","page":0,"limit":0,"Version":0}]
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
             * Id : 15608079129
             * UserID : 15608079129
             * NickName : 15608079129
             * Avator : b83a74fcc7734764b9b4dbf896881820.jpeg
             * CreateDate : 2020-03-04T16:24:36
             * LastLoginDate : 2020-05-12T10:46:48
             * LoginCount : 105
             * RemainMoney : 0.0
             * TotalMoney : 236.0
             * FrozenMoney : 0.0
             * Type : 7
             * TopRank : 3
             * YnAuth : null
             * barCode : null
             * IsUse : Y
             * PassWord : xiao123456
             * PayPassWord : 452464
             * RoleID : 15
             * RoleName : 维修师傅
             * AccountID : 3938
             * DistrictCode : 330202007
             * Con : 0
             * Longitude : 121.464839
             * Dimension : 29.94471
             * ServiceTotalOrderNum : 62
             * ServiceComplaintNum : 0
             * UnfinishedAmount : 389.0
             * ServiceTotalMoney : 0.0
             * CompanyName : 未填写公司名称
             * OrderFee : 0.0
             * FeeTypes : null
             * DoorFee : 0.0
             * AgainMoney : 0.0
             * StartDate : null
             * EndDate : null
             * ProvinceCode : 330000
             * CityCode : 330200
             * AreaCode : 330202
             * Address : 浙江省宁波市江北区洪塘街道海达大厦
             * DepositMoney : 0.0
             * DepositFrozenMoney : 0.0
             * Skills : null
             * IfAuth : 1
             * AuthMessage :
             * ParentUserID : null
             * TrueName : 肖
             * IDCard : 510626198606285614
             * Sex : 男
             * Phone : 15608079129
             * emergencyContact : null
             * teamNumber : 2
             * IsOrNoTruck : N
             * page : 0
             * limit : 0
             * Version : 0
             */

            private String Id;
            private String UserID;
            private String NickName;
            private String Avator;
            private String CreateDate;
            private String LastLoginDate;
            private int LoginCount;
            private double RemainMoney;
            private double TotalMoney;
            private double FrozenMoney;
            private String Type;
            private String TopRank;
            private Object YnAuth;
            private Object barCode;
            private String IsUse;
            private String PassWord;
            private String PayPassWord;
            private int RoleID;
            private String RoleName;
            private int AccountID;
            private String DistrictCode;
            private int Con;
            private String Longitude;
            private String Dimension;
            private String ServiceTotalOrderNum;
            private String ServiceComplaintNum;
            private double UnfinishedAmount;
            private double ServiceTotalMoney;
            private String CompanyName;
            private double OrderFee;
            private Object FeeTypes;
            private double DoorFee;
            private double AgainMoney;
            private Object StartDate;
            private Object EndDate;
            private String ProvinceCode;
            private String CityCode;
            private String AreaCode;
            private String Address;
            private double DepositMoney;
            private double DepositFrozenMoney;
            private Object Skills;
            private String IfAuth;
            private String AuthMessage;
            private String ParentUserID;
            private String TrueName;
            private String IDCard;
            private String Sex;
            private String Phone;
            private Object emergencyContact;
            private int teamNumber;
            private String IsOrNoTruck;
            private int page;
            private int limit;
            private int Version;
            private int IsStartSignContract;//是否开启签约合同，0：不允许，1：允许
            private int IsAutographSuccess;//是否签名成功：0：未签名，1：签名成功，-1：签名失败

            public int getIsStartSignContract() {
                return IsStartSignContract;
            }

            public void setIsStartSignContract(int isStartSignContract) {
                IsStartSignContract = isStartSignContract;
            }

            public int getIsAutographSuccess() {
                return IsAutographSuccess;
            }

            public void setIsAutographSuccess(int isAutographSuccess) {
                IsAutographSuccess = isAutographSuccess;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getAvator() {
                return Avator;
            }

            public void setAvator(String Avator) {
                this.Avator = Avator;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public String getLastLoginDate() {
                return LastLoginDate;
            }

            public void setLastLoginDate(String LastLoginDate) {
                this.LastLoginDate = LastLoginDate;
            }

            public int getLoginCount() {
                return LoginCount;
            }

            public void setLoginCount(int LoginCount) {
                this.LoginCount = LoginCount;
            }

            public double getRemainMoney() {
                return RemainMoney;
            }

            public void setRemainMoney(double RemainMoney) {
                this.RemainMoney = RemainMoney;
            }

            public double getTotalMoney() {
                return TotalMoney;
            }

            public void setTotalMoney(double TotalMoney) {
                this.TotalMoney = TotalMoney;
            }

            public double getFrozenMoney() {
                return FrozenMoney;
            }

            public void setFrozenMoney(double FrozenMoney) {
                this.FrozenMoney = FrozenMoney;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public String getTopRank() {
                return TopRank;
            }

            public void setTopRank(String TopRank) {
                this.TopRank = TopRank;
            }

            public Object getYnAuth() {
                return YnAuth;
            }

            public void setYnAuth(Object YnAuth) {
                this.YnAuth = YnAuth;
            }

            public Object getBarCode() {
                return barCode;
            }

            public void setBarCode(Object barCode) {
                this.barCode = barCode;
            }

            public String getIsUse() {
                return IsUse;
            }

            public void setIsUse(String IsUse) {
                this.IsUse = IsUse;
            }

            public String getPassWord() {
                return PassWord;
            }

            public void setPassWord(String PassWord) {
                this.PassWord = PassWord;
            }

            public String getPayPassWord() {
                return PayPassWord;
            }

            public void setPayPassWord(String PayPassWord) {
                this.PayPassWord = PayPassWord;
            }

            public int getRoleID() {
                return RoleID;
            }

            public void setRoleID(int RoleID) {
                this.RoleID = RoleID;
            }

            public String getRoleName() {
                return RoleName;
            }

            public void setRoleName(String RoleName) {
                this.RoleName = RoleName;
            }

            public int getAccountID() {
                return AccountID;
            }

            public void setAccountID(int AccountID) {
                this.AccountID = AccountID;
            }

            public String getDistrictCode() {
                return DistrictCode;
            }

            public void setDistrictCode(String DistrictCode) {
                this.DistrictCode = DistrictCode;
            }

            public int getCon() {
                return Con;
            }

            public void setCon(int Con) {
                this.Con = Con;
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

            public String getServiceTotalOrderNum() {
                return ServiceTotalOrderNum;
            }

            public void setServiceTotalOrderNum(String ServiceTotalOrderNum) {
                this.ServiceTotalOrderNum = ServiceTotalOrderNum;
            }

            public String getServiceComplaintNum() {
                return ServiceComplaintNum;
            }

            public void setServiceComplaintNum(String ServiceComplaintNum) {
                this.ServiceComplaintNum = ServiceComplaintNum;
            }

            public double getUnfinishedAmount() {
                return UnfinishedAmount;
            }

            public void setUnfinishedAmount(double UnfinishedAmount) {
                this.UnfinishedAmount = UnfinishedAmount;
            }

            public double getServiceTotalMoney() {
                return ServiceTotalMoney;
            }

            public void setServiceTotalMoney(double ServiceTotalMoney) {
                this.ServiceTotalMoney = ServiceTotalMoney;
            }

            public String getCompanyName() {
                return CompanyName;
            }

            public void setCompanyName(String CompanyName) {
                this.CompanyName = CompanyName;
            }

            public double getOrderFee() {
                return OrderFee;
            }

            public void setOrderFee(double OrderFee) {
                this.OrderFee = OrderFee;
            }

            public Object getFeeTypes() {
                return FeeTypes;
            }

            public void setFeeTypes(Object FeeTypes) {
                this.FeeTypes = FeeTypes;
            }

            public double getDoorFee() {
                return DoorFee;
            }

            public void setDoorFee(double DoorFee) {
                this.DoorFee = DoorFee;
            }

            public double getAgainMoney() {
                return AgainMoney;
            }

            public void setAgainMoney(double AgainMoney) {
                this.AgainMoney = AgainMoney;
            }

            public Object getStartDate() {
                return StartDate;
            }

            public void setStartDate(Object StartDate) {
                this.StartDate = StartDate;
            }

            public Object getEndDate() {
                return EndDate;
            }

            public void setEndDate(Object EndDate) {
                this.EndDate = EndDate;
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

            public double getDepositMoney() {
                return DepositMoney;
            }

            public void setDepositMoney(double DepositMoney) {
                this.DepositMoney = DepositMoney;
            }

            public double getDepositFrozenMoney() {
                return DepositFrozenMoney;
            }

            public void setDepositFrozenMoney(double DepositFrozenMoney) {
                this.DepositFrozenMoney = DepositFrozenMoney;
            }

            public Object getSkills() {
                return Skills;
            }

            public void setSkills(Object Skills) {
                this.Skills = Skills;
            }

            public String getIfAuth() {
                return IfAuth;
            }

            public void setIfAuth(String IfAuth) {
                this.IfAuth = IfAuth;
            }

            public String getAuthMessage() {
                return AuthMessage;
            }

            public void setAuthMessage(String AuthMessage) {
                this.AuthMessage = AuthMessage;
            }

            public String getParentUserID() {
                return ParentUserID;
            }

            public void setParentUserID(String ParentUserID) {
                this.ParentUserID = ParentUserID;
            }

            public String getTrueName() {
                return TrueName;
            }

            public void setTrueName(String TrueName) {
                this.TrueName = TrueName;
            }

            public String getIDCard() {
                return IDCard;
            }

            public void setIDCard(String IDCard) {
                this.IDCard = IDCard;
            }

            public String getSex() {
                return Sex;
            }

            public void setSex(String Sex) {
                this.Sex = Sex;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public Object getEmergencyContact() {
                return emergencyContact;
            }

            public void setEmergencyContact(Object emergencyContact) {
                this.emergencyContact = emergencyContact;
            }

            public int getTeamNumber() {
                return teamNumber;
            }

            public void setTeamNumber(int teamNumber) {
                this.teamNumber = teamNumber;
            }

            public String getIsOrNoTruck() {
                return IsOrNoTruck;
            }

            public void setIsOrNoTruck(String IsOrNoTruck) {
                this.IsOrNoTruck = IsOrNoTruck;
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
}
