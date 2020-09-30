package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {


    private String code;
    private String msg;
    private String count;
    private List<UserInfoDean> data;

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

    public List<UserInfoDean> getData() {
        return data;
    }

    public void setData(List<UserInfoDean> data) {
        this.data = data;
    }

    public static class UserInfoDean {

        /**
         * Id : string
         * UserID : string
         * NickName : string
         * Avator : string
         * CreateDate : 2019-02-21T05:17:47.560Z
         * LastLoginDate : 2019-02-21T05:17:47.560Z
         * LoginCount : 0
         * RemainMoney : 0
         * TotalMoney : 0    总余额
         * FrozenMoney : 0   冻结
         * Type : string
         * TopRank : string
         * IsUse : string
         * PassWord : string
         * PayPassWord : string
         * RoleID : 0
         * RoleName : string
         * AccountID : 0
         * DistrictCode : string
         * StartDate : string
         * EndDate : string
         * ProvinceCode : string
         * CityCode : string
         * AreaCode : string
         * Address : string
         * DepositMoney : 0
         * DepositFrozenMoney : 0
         * Skills : string
         * IfAuth : string
         * AuthMessage : string
         * ParentUserID : string
         * TrueName : string
         * IDCard : string
         * Sex : string
         * Phone : string
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
        private Double RemainMoney;
        private Double TotalMoney;
        private Double FrozenMoney;
        private String Type;
        private String TopRank;
        private String IsUse;
        private String PassWord;
        private String PayPassWord;
        private int RoleID;
        private String RoleName;
        private int AccountID;
        private String DistrictCode;
        private String StartDate;
        private String EndDate;
        private String ProvinceCode;
        private String CityCode;
        private String AreaCode;
        private String Address;
        private double DepositMoney;
        private int DepositFrozenMoney;
        private String Skills;
        private String IfAuth;
        private String AuthMessage;
        private String ParentUserID;
        private String TrueName;
        private String IDCard;
        private String Sex;
        private String Phone;
        private String Longitude;
        private String Dimension;
        private int page;
        private int limit;
        private int Version;
        private Double Con;
        private String ServiceTotalOrderNum;
        private String ServiceComplaintNum;
        private Double UnfinishedAmount;
        private double ServiceTotalMoney;
        private String emergencyContact;
        private String teamNumber;
        private String IsOrNoTruck;
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

        public String getTeamNumber() {
            return teamNumber;
        }

        public void setTeamNumber(String teamNumber) {
            this.teamNumber = teamNumber;
        }

        public String getIsOrNoTruck() {
            return IsOrNoTruck;
        }

        public void setIsOrNoTruck(String isOrNoTruck) {
            IsOrNoTruck = isOrNoTruck;
        }

        public String getEmergencyContact() {
            return emergencyContact;
        }

        public void setEmergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
        }

        public double getServiceTotalMoney() {
            return ServiceTotalMoney;
        }

        public void setServiceTotalMoney(double serviceTotalMoney) {
            ServiceTotalMoney = serviceTotalMoney;
        }

        public Double getUnfinishedAmount() {
            return UnfinishedAmount;
        }

        public void setUnfinishedAmount(Double unfinishedAmount) {
            UnfinishedAmount = unfinishedAmount;
        }

        public String getServiceTotalOrderNum() {
            return ServiceTotalOrderNum;
        }

        public void setServiceTotalOrderNum(String serviceTotalOrderNum) {
            ServiceTotalOrderNum = serviceTotalOrderNum;
        }

        public String getServiceComplaintNum() {
            return ServiceComplaintNum;
        }

        public void setServiceComplaintNum(String serviceComplaintNum) {
            ServiceComplaintNum = serviceComplaintNum;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        public String getDimension() {
            return Dimension;
        }

        public void setDimension(String dimension) {
            Dimension = dimension;
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

        public Double getRemainMoney() {
            return RemainMoney;
        }

        public void setRemainMoney(Double remainMoney) {
            RemainMoney = remainMoney;
        }

        public Double getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(Double totalMoney) {
            TotalMoney = totalMoney;
        }

        public Double getFrozenMoney() {
            return FrozenMoney;
        }

        public void setFrozenMoney(Double frozenMoney) {
            FrozenMoney = frozenMoney;
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

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
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

        public int getDepositFrozenMoney() {
            return DepositFrozenMoney;
        }

        public void setDepositFrozenMoney(int DepositFrozenMoney) {
            this.DepositFrozenMoney = DepositFrozenMoney;
        }

        public String getSkills() {
            return Skills;
        }

        public void setSkills(String Skills) {
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

        public Double getCon() {
            return Con;
        }

        public void setCon(Double con) {
            Con = con;
        }

        @Override
        public String toString() {
            return "UserInfoDean{" +
                    "Id='" + Id + '\'' +
                    ", UserID='" + UserID + '\'' +
                    ", NickName='" + NickName + '\'' +
                    ", Avator='" + Avator + '\'' +
                    ", CreateDate='" + CreateDate + '\'' +
                    ", LastLoginDate='" + LastLoginDate + '\'' +
                    ", LoginCount=" + LoginCount +
                    ", RemainMoney=" + RemainMoney +
                    ", TotalMoney=" + TotalMoney +
                    ", FrozenMoney=" + FrozenMoney +
                    ", Type='" + Type + '\'' +
                    ", TopRank='" + TopRank + '\'' +
                    ", IsUse='" + IsUse + '\'' +
                    ", PassWord='" + PassWord + '\'' +
                    ", PayPassWord='" + PayPassWord + '\'' +
                    ", RoleID=" + RoleID +
                    ", RoleName='" + RoleName + '\'' +
                    ", AccountID=" + AccountID +
                    ", DistrictCode='" + DistrictCode + '\'' +
                    ", StartDate='" + StartDate + '\'' +
                    ", EndDate='" + EndDate + '\'' +
                    ", ProvinceCode='" + ProvinceCode + '\'' +
                    ", CityCode='" + CityCode + '\'' +
                    ", AreaCode='" + AreaCode + '\'' +
                    ", Address='" + Address + '\'' +
                    ", DepositMoney=" + DepositMoney +
                    ", DepositFrozenMoney=" + DepositFrozenMoney +
                    ", Skills='" + Skills + '\'' +
                    ", IfAuth='" + IfAuth + '\'' +
                    ", AuthMessage='" + AuthMessage + '\'' +
                    ", ParentUserID='" + ParentUserID + '\'' +
                    ", TrueName='" + TrueName + '\'' +
                    ", IDCard='" + IDCard + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", page=" + page +
                    ", limit=" + limit +
                    ", Version=" + Version +
                    ", Con=" + Con +
                    '}';
        }
    }


}
