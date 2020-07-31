package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class accessoryDataBean implements Serializable {

    /**
     * Id : 2716
     * BrandID : 0
     * OldBeyondID : 0
     * ApplyMaster : 0
     * AccessoryID : 2716
     * FAccessoryID : 0
     * FAccessoryName : null
     * SendState : N
     * Quantity : 0
     * OrderID : 2000010417
     * CreateTime : 2020-07-30T16:19:16
     * Relation : d178f43c-cbb7-4256-9fd1-9a3ab979a025
     * Price : 0.0
     * DiscountPrice : 0.0
     * IsUse : Y
     * SendAddress : null
     * IsPay : N
     * ExpressNo :
     * State : 0
     * AccessoryState : 0
     * TypeID : 1
     * SizeID : 2
     * FCategoryID : 0
     * ApplyNum : 0
     * Photo1 : null
     * Photo2 : null
     * NeedPlatformAuth : N
     * CheckRemarks : null
     * Bak : ，。
     * IsReturn : null
     * PostPayType : null
     * RecipientType : null
     * OldReturnRequest : null
     * AddressBack : null
     * QApplyNum : 0
     * Order : null
     * limit : 999
     * page : 1
     * BeyondDistance : null
     * ReturnState : 0
     * ReceiptState : 0
     * ImgUrls : null
     * OrderAttachments : [{"Id":185,"AttachmentID":185,"OrderID":2000010417,"AttachmentType":null,"CreateUserID":"18767773654","CreateDate":"2020-07-30T16:19:16","IsUse":"Y","UpdateUserID":null,"UpdateDate":null,"BusinessID":"2716","Url":"5fe7202340754bf1afa19e77a8a99249.jpg","FileName":"5fe7202340754bf1afa19e77a8a99249.jpg","Folder":"/Pics/Accessory/","Version":0},{"Id":186,"AttachmentID":186,"OrderID":2000010417,"AttachmentType":null,"CreateUserID":"18767773654","CreateDate":"2020-07-30T16:19:16","IsUse":"Y","UpdateUserID":null,"UpdateDate":null,"BusinessID":"2716","Url":"774c57ab57994f89880cc53d566026b2.jpg","FileName":"774c57ab57994f89880cc53d566026b2.jpg","Folder":"/Pics/Accessory/","Version":0},{"Id":187,"AttachmentID":187,"OrderID":2000010417,"AttachmentType":null,"CreateUserID":"18767773654","CreateDate":"2020-07-30T16:19:16","IsUse":"Y","UpdateUserID":null,"UpdateDate":null,"BusinessID":"2716","Url":"e4aa247c36e04c83b11a97932c9d9156.jpg","FileName":"e4aa247c36e04c83b11a97932c9d9156.jpg","Folder":"/Pics/Accessory/","Version":0},{"Id":188,"AttachmentID":188,"OrderID":2000010417,"AttachmentType":null,"CreateUserID":"18767773654","CreateDate":"2020-07-30T16:19:16","IsUse":"Y","UpdateUserID":null,"UpdateDate":null,"BusinessID":"2716","Url":"604149a97cad4e53a659054cbbdf1135.jpg","FileName":"604149a97cad4e53a659054cbbdf1135.jpg","Folder":"/Pics/Accessory/","Version":0},{"Id":189,"AttachmentID":189,"OrderID":2000010417,"AttachmentType":null,"CreateUserID":"18767773654","CreateDate":"2020-07-30T16:19:16","IsUse":"Y","UpdateUserID":null,"UpdateDate":null,"BusinessID":"2716","Url":"59f619223a854a94a518fe7ee47d0258.jpg","FileName":"59f619223a854a94a518fe7ee47d0258.jpg","Folder":"/Pics/Accessory/","Version":0}]
     * AccessoryDetailModels : [{"Id":13,"AccessoryDetailID":13,"OrderID":2000010417,"OrderProdID":0,"FAccessoryID":524,"FAccessoryName":"更换水位开关","Quantity":1,"CreateTime":"0001-01-01T00:00:00","IsUse":"Y","Price":0,"SizeID":2,"AccessoryID":2716,"Version":0},{"Id":14,"AccessoryDetailID":14,"OrderID":2000010417,"OrderProdID":0,"FAccessoryID":525,"FAccessoryName":"更换门安全开关","Quantity":2,"CreateTime":"0001-01-01T00:00:00","IsUse":"Y","Price":0,"SizeID":2,"AccessoryID":2716,"Version":0},{"Id":15,"AccessoryDetailID":15,"OrderID":2000010417,"OrderProdID":0,"FAccessoryID":527,"FAccessoryName":"更换导压管","Quantity":3,"CreateTime":"0001-01-01T00:00:00","IsUse":"Y","Price":0,"SizeID":2,"AccessoryID":2716,"Version":0}]
     * AccessoryNo : 200001041710
     * Version : 0
     */

    private int Id;
    private int BrandID;
    private int OldBeyondID;
    private int ApplyMaster;
    private int AccessoryID;
    private int FAccessoryID;
    private Object FAccessoryName;
    private String SendState;
    private int Quantity;
    private int OrderID;
    private String CreateTime;
    private String Relation;
    private double Price;
    private double DiscountPrice;
    private String IsUse;
    private Object SendAddress;
    private String IsPay;
    private String ExpressNo;
    private String State;
    private String AccessoryState;
    private String TypeID;
    private int SizeID;
    private int FCategoryID;
    private int ApplyNum;
    private Object Photo1;
    private Object Photo2;
    private String NeedPlatformAuth;
    private Object CheckRemarks;
    private String Bak;
    private Object IsReturn;
    private Object PostPayType;
    private Object RecipientType;
    private Object OldReturnRequest;
    private Object AddressBack;
    private int QApplyNum;
    private Object Order;
    private int limit;
    private int page;
    private Object BeyondDistance;
    private int ReturnState;
    private int ReceiptState;
    private Object ImgUrls;
    private String AccessoryNo;
    private int Version;
    private List<OrderAttachmentsBean> OrderAttachments;
    private List<AccessoryDetailModelsBean> AccessoryDetailModels;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public int getOldBeyondID() {
        return OldBeyondID;
    }

    public void setOldBeyondID(int OldBeyondID) {
        this.OldBeyondID = OldBeyondID;
    }

    public int getApplyMaster() {
        return ApplyMaster;
    }

    public void setApplyMaster(int ApplyMaster) {
        this.ApplyMaster = ApplyMaster;
    }

    public int getAccessoryID() {
        return AccessoryID;
    }

    public void setAccessoryID(int AccessoryID) {
        this.AccessoryID = AccessoryID;
    }

    public int getFAccessoryID() {
        return FAccessoryID;
    }

    public void setFAccessoryID(int FAccessoryID) {
        this.FAccessoryID = FAccessoryID;
    }

    public Object getFAccessoryName() {
        return FAccessoryName;
    }

    public void setFAccessoryName(Object FAccessoryName) {
        this.FAccessoryName = FAccessoryName;
    }

    public String getSendState() {
        return SendState;
    }

    public void setSendState(String SendState) {
        this.SendState = SendState;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(double DiscountPrice) {
        this.DiscountPrice = DiscountPrice;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public Object getSendAddress() {
        return SendAddress;
    }

    public void setSendAddress(Object SendAddress) {
        this.SendAddress = SendAddress;
    }

    public String getIsPay() {
        return IsPay;
    }

    public void setIsPay(String IsPay) {
        this.IsPay = IsPay;
    }

    public String getExpressNo() {
        return ExpressNo;
    }

    public void setExpressNo(String ExpressNo) {
        this.ExpressNo = ExpressNo;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getAccessoryState() {
        return AccessoryState;
    }

    public void setAccessoryState(String AccessoryState) {
        this.AccessoryState = AccessoryState;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String TypeID) {
        this.TypeID = TypeID;
    }

    public int getSizeID() {
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public int getFCategoryID() {
        return FCategoryID;
    }

    public void setFCategoryID(int FCategoryID) {
        this.FCategoryID = FCategoryID;
    }

    public int getApplyNum() {
        return ApplyNum;
    }

    public void setApplyNum(int ApplyNum) {
        this.ApplyNum = ApplyNum;
    }

    public Object getPhoto1() {
        return Photo1;
    }

    public void setPhoto1(Object Photo1) {
        this.Photo1 = Photo1;
    }

    public Object getPhoto2() {
        return Photo2;
    }

    public void setPhoto2(Object Photo2) {
        this.Photo2 = Photo2;
    }

    public String getNeedPlatformAuth() {
        return NeedPlatformAuth;
    }

    public void setNeedPlatformAuth(String NeedPlatformAuth) {
        this.NeedPlatformAuth = NeedPlatformAuth;
    }

    public Object getCheckRemarks() {
        return CheckRemarks;
    }

    public void setCheckRemarks(Object CheckRemarks) {
        this.CheckRemarks = CheckRemarks;
    }

    public String getBak() {
        return Bak;
    }

    public void setBak(String Bak) {
        this.Bak = Bak;
    }

    public Object getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(Object IsReturn) {
        this.IsReturn = IsReturn;
    }

    public Object getPostPayType() {
        return PostPayType;
    }

    public void setPostPayType(Object PostPayType) {
        this.PostPayType = PostPayType;
    }

    public Object getRecipientType() {
        return RecipientType;
    }

    public void setRecipientType(Object RecipientType) {
        this.RecipientType = RecipientType;
    }

    public Object getOldReturnRequest() {
        return OldReturnRequest;
    }

    public void setOldReturnRequest(Object OldReturnRequest) {
        this.OldReturnRequest = OldReturnRequest;
    }

    public Object getAddressBack() {
        return AddressBack;
    }

    public void setAddressBack(Object AddressBack) {
        this.AddressBack = AddressBack;
    }

    public int getQApplyNum() {
        return QApplyNum;
    }

    public void setQApplyNum(int QApplyNum) {
        this.QApplyNum = QApplyNum;
    }

    public Object getOrder() {
        return Order;
    }

    public void setOrder(Object Order) {
        this.Order = Order;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getBeyondDistance() {
        return BeyondDistance;
    }

    public void setBeyondDistance(Object BeyondDistance) {
        this.BeyondDistance = BeyondDistance;
    }

    public int getReturnState() {
        return ReturnState;
    }

    public void setReturnState(int ReturnState) {
        this.ReturnState = ReturnState;
    }

    public int getReceiptState() {
        return ReceiptState;
    }

    public void setReceiptState(int ReceiptState) {
        this.ReceiptState = ReceiptState;
    }

    public Object getImgUrls() {
        return ImgUrls;
    }

    public void setImgUrls(Object ImgUrls) {
        this.ImgUrls = ImgUrls;
    }

    public String getAccessoryNo() {
        return AccessoryNo;
    }

    public void setAccessoryNo(String AccessoryNo) {
        this.AccessoryNo = AccessoryNo;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public List<OrderAttachmentsBean> getOrderAttachments() {
        return OrderAttachments;
    }

    public void setOrderAttachments(List<OrderAttachmentsBean> OrderAttachments) {
        this.OrderAttachments = OrderAttachments;
    }

    public List<AccessoryDetailModelsBean> getAccessoryDetailModels() {
        return AccessoryDetailModels;
    }

    public void setAccessoryDetailModels(List<AccessoryDetailModelsBean> AccessoryDetailModels) {
        this.AccessoryDetailModels = AccessoryDetailModels;
    }

    public static class OrderAttachmentsBean implements Serializable{
        /**
         * Id : 185
         * AttachmentID : 185
         * OrderID : 2000010417
         * AttachmentType : null
         * CreateUserID : 18767773654
         * CreateDate : 2020-07-30T16:19:16
         * IsUse : Y
         * UpdateUserID : null
         * UpdateDate : null
         * BusinessID : 2716
         * Url : 5fe7202340754bf1afa19e77a8a99249.jpg
         * FileName : 5fe7202340754bf1afa19e77a8a99249.jpg
         * Folder : /Pics/Accessory/
         * Version : 0
         */

        private int Id;
        private int AttachmentID;
        private int OrderID;
        private Object AttachmentType;
        private String CreateUserID;
        private String CreateDate;
        private String IsUse;
        private Object UpdateUserID;
        private Object UpdateDate;
        private String BusinessID;
        private String Url;
        private String FileName;
        private String Folder;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getAttachmentID() {
            return AttachmentID;
        }

        public void setAttachmentID(int AttachmentID) {
            this.AttachmentID = AttachmentID;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public Object getAttachmentType() {
            return AttachmentType;
        }

        public void setAttachmentType(Object AttachmentType) {
            this.AttachmentType = AttachmentType;
        }

        public String getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(String CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public Object getUpdateUserID() {
            return UpdateUserID;
        }

        public void setUpdateUserID(Object UpdateUserID) {
            this.UpdateUserID = UpdateUserID;
        }

        public Object getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(Object UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public String getBusinessID() {
            return BusinessID;
        }

        public void setBusinessID(String BusinessID) {
            this.BusinessID = BusinessID;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFolder() {
            return Folder;
        }

        public void setFolder(String Folder) {
            this.Folder = Folder;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }
    }

    public static class AccessoryDetailModelsBean implements Serializable{
        /**
         * Id : 13
         * AccessoryDetailID : 13
         * OrderID : 2000010417
         * OrderProdID : 0
         * FAccessoryID : 524
         * FAccessoryName : 更换水位开关
         * Quantity : 1
         * CreateTime : 0001-01-01T00:00:00
         * IsUse : Y
         * Price : 0.0
         * SizeID : 2
         * AccessoryID : 2716
         * Version : 0
         */

        private int Id;
        private int AccessoryDetailID;
        private int OrderID;
        private int OrderProdID;
        private int FAccessoryID;
        private String FAccessoryName;
        private int Quantity;
        private String CreateTime;
        private String IsUse;
        private double Price;
        private int SizeID;
        private int AccessoryID;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getAccessoryDetailID() {
            return AccessoryDetailID;
        }

        public void setAccessoryDetailID(int AccessoryDetailID) {
            this.AccessoryDetailID = AccessoryDetailID;
        }

        public int getOrderID() {
            return OrderID;
        }

        public void setOrderID(int OrderID) {
            this.OrderID = OrderID;
        }

        public int getOrderProdID() {
            return OrderProdID;
        }

        public void setOrderProdID(int OrderProdID) {
            this.OrderProdID = OrderProdID;
        }

        public int getFAccessoryID() {
            return FAccessoryID;
        }

        public void setFAccessoryID(int FAccessoryID) {
            this.FAccessoryID = FAccessoryID;
        }

        public String getFAccessoryName() {
            return FAccessoryName;
        }

        public void setFAccessoryName(String FAccessoryName) {
            this.FAccessoryName = FAccessoryName;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int Quantity) {
            this.Quantity = Quantity;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public int getSizeID() {
            return SizeID;
        }

        public void setSizeID(int SizeID) {
            this.SizeID = SizeID;
        }

        public int getAccessoryID() {
            return AccessoryID;
        }

        public void setAccessoryID(int AccessoryID) {
            this.AccessoryID = AccessoryID;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }
    }
}
