package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class SubUserInfo implements Serializable {




    private List<SubUserInfoDean> data;

    public List<SubUserInfoDean> getData() {
        return data;
    }

    public void setData(List<SubUserInfoDean> data) {
        this.data = data;
    }

    public static class SubUserInfoDean {


        /**
         * Id : 121
         * AccountID : 121
         * UserID : 18892621501
         * NickName : 123
         * Avator : null
         * PassWord : 123
         * PayPassWord : 888888
         * CreateDate : 2019-02-22T15:06:10
         * LastLoginDate : 2019-02-23T15:22:29
         * LoginCount : 32
         * RemainMoney : 0.0
         * TotalMoney : 0.0
         * FrozenMoney : 0.0
         * Type : 5
         * TopRank : 3
         * IsUse : Y
         * ProvinceCode : null
         * CityCode : null
         * AreaCode : null
         * DistrictCode : null
         * Longitude : null
         * Dimension : null
         * Address : null
         * DepositMoney : 0.0
         * DepositFrozenMoney : 0.0
         * Skills : null
         * AccountDetail : []
         * AccountServiceArea : []
         * IfAuth : 1
         * AuthMessage : null
         * ParentUserID : 18892621500
         * TrueName : null
         * IDCard : null
         * Sex : 男
         * Phone : 18892621501
         * RoleID : null
         * page : 0
         * limit : 0
         * Version : 0
         */

        private int Id;
        private int AccountID;
        private String UserID;
        private String NickName;
        private String Avator;
        private String PassWord;
        private String PayPassWord;
        private String CreateDate;
        private String LastLoginDate;
        private int LoginCount;
        private double RemainMoney;
        private double TotalMoney;
        private double FrozenMoney;
        private String Type;
        private String TopRank;
        private String IsUse;
        private String ProvinceCode;
        private String CityCode;
        private String AreaCode;
        private String DistrictCode;
        private String Longitude;
        private String Dimension;
        private String Address;
        private double DepositMoney;
        private double DepositFrozenMoney;
        private String Skills;
        private String IfAuth;
        private String AuthMessage;
        private String ParentUserID;
        private String TrueName;
        private String IDCard;
        private String Sex;
        private String Phone;
        private String RoleID;
        private int page;
        private int limit;
        private int Version;
        private List<?> AccountDetail;
        private List<?> AccountServiceArea;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getAccountID() {
            return AccountID;
        }

        public void setAccountID(int accountID) {
            AccountID = accountID;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String userID) {
            UserID = userID;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String nickName) {
            NickName = nickName;
        }

        public String getAvator() {
            return Avator;
        }

        public void setAvator(String avator) {
            Avator = avator;
        }

        public String getPassWord() {
            return PassWord;
        }

        public void setPassWord(String passWord) {
            PassWord = passWord;
        }

        public String getPayPassWord() {
            return PayPassWord;
        }

        public void setPayPassWord(String payPassWord) {
            PayPassWord = payPassWord;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getLastLoginDate() {
            return LastLoginDate;
        }

        public void setLastLoginDate(String lastLoginDate) {
            LastLoginDate = lastLoginDate;
        }

        public int getLoginCount() {
            return LoginCount;
        }

        public void setLoginCount(int loginCount) {
            LoginCount = loginCount;
        }

        public double getRemainMoney() {
            return RemainMoney;
        }

        public void setRemainMoney(double remainMoney) {
            RemainMoney = remainMoney;
        }

        public double getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            TotalMoney = totalMoney;
        }

        public double getFrozenMoney() {
            return FrozenMoney;
        }

        public void setFrozenMoney(double frozenMoney) {
            FrozenMoney = frozenMoney;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getTopRank() {
            return TopRank;
        }

        public void setTopRank(String topRank) {
            TopRank = topRank;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String isUse) {
            IsUse = isUse;
        }

        public String getProvinceCode() {
            return ProvinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            ProvinceCode = provinceCode;
        }

        public String getCityCode() {
            return CityCode;
        }

        public void setCityCode(String cityCode) {
            CityCode = cityCode;
        }

        public String getAreaCode() {
            return AreaCode;
        }

        public void setAreaCode(String areaCode) {
            AreaCode = areaCode;
        }

        public String getDistrictCode() {
            return DistrictCode;
        }

        public void setDistrictCode(String districtCode) {
            DistrictCode = districtCode;
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

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public double getDepositMoney() {
            return DepositMoney;
        }

        public void setDepositMoney(double depositMoney) {
            DepositMoney = depositMoney;
        }

        public double getDepositFrozenMoney() {
            return DepositFrozenMoney;
        }

        public void setDepositFrozenMoney(double depositFrozenMoney) {
            DepositFrozenMoney = depositFrozenMoney;
        }

        public String getSkills() {
            return Skills;
        }

        public void setSkills(String skills) {
            Skills = skills;
        }

        public String getIfAuth() {
            return IfAuth;
        }

        public void setIfAuth(String ifAuth) {
            IfAuth = ifAuth;
        }

        public String getAuthMessage() {
            return AuthMessage;
        }

        public void setAuthMessage(String authMessage) {
            AuthMessage = authMessage;
        }

        public String getParentUserID() {
            return ParentUserID;
        }

        public void setParentUserID(String parentUserID) {
            ParentUserID = parentUserID;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String trueName) {
            TrueName = trueName;
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

        public void setSex(String sex) {
            Sex = sex;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getRoleID() {
            return RoleID;
        }

        public void setRoleID(String roleID) {
            RoleID = roleID;
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

        public void setVersion(int version) {
            Version = version;
        }

        public List<?> getAccountDetail() {
            return AccountDetail;
        }

        public void setAccountDetail(List<?> accountDetail) {
            AccountDetail = accountDetail;
        }

        public List<?> getAccountServiceArea() {
            return AccountServiceArea;
        }

        public void setAccountServiceArea(List<?> accountServiceArea) {
            AccountServiceArea = accountServiceArea;
        }
    }

}
