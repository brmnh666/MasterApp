package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class DetailResult implements Serializable {

    /**
     * Success : true
     * IsOnLimitBuy : false
     * IsFightGroupActive : false
     * ActiveId : 0
     * ActiveStatus : 0
     * MaxSaleCount : 0
     * Title :
     * Second : 0.0
     * Product : {"ProductId":733,"ProductSaleStatus":1,"AuditStatus":2,"ImagePath":[],"ProductName":"丹麦依诺维绅 功能沙发床 时尚沙发 书房必用 小鸟","MarketPrice":5000,"ShortDescription":"丹麦设计 独立袋装弹簧 北欧时尚简约","ProductDescription":"<p><img src=\"http://mall.xigyu.com//Storage/Shop/1/Products/733/remark/97ae97b480ba4ff6b83cf39c79c55b5a.jpg\" title=\"605303069192223693765340_x.jpg\" alt=\"605303069192223693765340_x.jpg\"/><\/p>","MinSalePrice":3982.5,"IsFavorite":true,"Consultations":0,"CommentCount":0,"NicePercent":100,"IsOnLimitBuy":false}
     * CashDepositsServer : {"IsSevenDayNoReasonReturn":false,"IsTimelyShip":false,"IsCustomerSecurity":true,"CanSelfTake":false}
     * ProductAddress : 北京 北京市
     * Free : 免运费
     * VShopLog : http://mall.xigyu.com//Storage/Shop/1/VShop/201702141734365302700.png
     * Shop : {"Name":"官方自营店","Id":1,"ProductMark":0,"PackMark":5,"ServiceMark":5,"ComprehensiveMark":5,"CompanyName":null,"Address":null,"Phone":null,"FreeFreight":11,"ProductNum":33,"CouponCount":0,"VShopId":10,"ProductAndDescription":5,"SellerServiceAttitude":5,"SellerDeliverySpeed":5,"FavoriteShopCount":2}
     * IsFavoriteShop : false
     * Color : []
     * Size : []
     * Version : []
     * BonusCount : 0
     * BonusGrantPrice : 0.0
     * BonusRandomAmountStart : 0.0
     * BonusRandomAmountEnd : 0.0
     * fullDiscount : null
     * ColorAlias : 颜色
     * SizeAlias : 尺码
     * VersionAlias : 规格
     * IsDistribution : true
     * Brokerage : 132.75
     * IsPromoter : false
     * userId : 645
     * IsOpenStore : true
     * CustomerServices : []
     */

    private String Success;
    private boolean IsOnLimitBuy;
    private boolean IsFightGroupActive;
    private int ActiveId;
    private int ActiveStatus;
    private int MaxSaleCount;
    private String Title;
    private double Second;
    private ProductBean Product;
    private CashDepositsServerBean CashDepositsServer;
    private String ProductAddress;
    private String Free;
    private String FreightFee;
    private String VShopLog;
    private ShopBean Shop;
    private boolean IsFavoriteShop;
    private int BonusCount;
    private double BonusGrantPrice;
    private double BonusRandomAmountStart;
    private double BonusRandomAmountEnd;
    private Object fullDiscount;
    private String ColorAlias;
    private String SizeAlias;
    private String VersionAlias;
    private boolean IsDistribution;
    private String Brokerage;
    private boolean IsPromoter;
    private int userId;
    private boolean IsOpenStore;
    private List<ShopColor> Color;
    private List<ShopSize> Size;
    private List<ShopVersion> Version;
    private List<CustomerBean> CustomerServices;
    private List<String> ProductAttributeInfos;
    private List<ProductAttributeInfolistBean> ProductAttributeInfolist;
    private List<CashDepositsServerNameBean> CashDepositsServerName;
    private String SalesCount;

    public String getSalesCount() {
        return SalesCount;
    }

    public void setSalesCount(String salesCount) {
        SalesCount = salesCount;
    }

    public List<CashDepositsServerNameBean> getCashDepositsServerName() {
        return CashDepositsServerName;
    }

    public void setCashDepositsServerName(List<CashDepositsServerNameBean> cashDepositsServerName) {
        CashDepositsServerName = cashDepositsServerName;
    }


    public List<ProductAttributeInfolistBean> getProductAttributeInfolist() {
        return ProductAttributeInfolist;
    }

    public void setProductAttributeInfolist(List<ProductAttributeInfolistBean> productAttributeInfolist) {
        ProductAttributeInfolist = productAttributeInfolist;
    }

    public List<String> getProductAttributeInfos() {
        return ProductAttributeInfos;
    }

    public void setProductAttributeInfos(List<String> productAttributeInfos) {
        ProductAttributeInfos = productAttributeInfos;
    }

    public String getFreightFee() {
        return FreightFee;
    }

    public void setFreightFee(String freightFee) {
        FreightFee = freightFee;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public boolean getIsOnLimitBuy() {
        return IsOnLimitBuy;
    }

    public void setIsOnLimitBuy(boolean IsOnLimitBuy) {
        this.IsOnLimitBuy = IsOnLimitBuy;
    }

    public boolean isIsFightGroupActive() {
        return IsFightGroupActive;
    }

    public void setIsFightGroupActive(boolean IsFightGroupActive) {
        this.IsFightGroupActive = IsFightGroupActive;
    }

    public int getActiveId() {
        return ActiveId;
    }

    public void setActiveId(int ActiveId) {
        this.ActiveId = ActiveId;
    }

    public int getActiveStatus() {
        return ActiveStatus;
    }

    public void setActiveStatus(int ActiveStatus) {
        this.ActiveStatus = ActiveStatus;
    }

    public int getMaxSaleCount() {
        return MaxSaleCount;
    }

    public void setMaxSaleCount(int MaxSaleCount) {
        this.MaxSaleCount = MaxSaleCount;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public long getSecond() {
        return (long) Second;
    }

    public void setSecond(double Second) {
        this.Second = Second;
    }

    public ProductBean getProduct() {
        return Product;
    }

    public void setProduct(ProductBean Product) {
        this.Product = Product;
    }

    public CashDepositsServerBean getCashDepositsServer() {
        return CashDepositsServer;
    }

    public void setCashDepositsServer(CashDepositsServerBean CashDepositsServer) {
        this.CashDepositsServer = CashDepositsServer;
    }

    public String getProductAddress() {
        return ProductAddress;
    }

    public void setProductAddress(String ProductAddress) {
        this.ProductAddress = ProductAddress;
    }

    public String getFree() {
        return Free;
    }

    public void setFree(String Free) {
        this.Free = Free;
    }

    public String getVShopLog() {
        return VShopLog;
    }

    public void setVShopLog(String VShopLog) {
        this.VShopLog = VShopLog;
    }

    public ShopBean getShop() {
        return Shop;
    }

    public void setShop(ShopBean Shop) {
        this.Shop = Shop;
    }

    public boolean isIsFavoriteShop() {
        return IsFavoriteShop;
    }

    public void setIsFavoriteShop(boolean IsFavoriteShop) {
        this.IsFavoriteShop = IsFavoriteShop;
    }

    public int getBonusCount() {
        return BonusCount;
    }

    public void setBonusCount(int BonusCount) {
        this.BonusCount = BonusCount;
    }

    public double getBonusGrantPrice() {
        return BonusGrantPrice;
    }

    public void setBonusGrantPrice(double BonusGrantPrice) {
        this.BonusGrantPrice = BonusGrantPrice;
    }

    public double getBonusRandomAmountStart() {
        return BonusRandomAmountStart;
    }

    public void setBonusRandomAmountStart(double BonusRandomAmountStart) {
        this.BonusRandomAmountStart = BonusRandomAmountStart;
    }

    public double getBonusRandomAmountEnd() {
        return BonusRandomAmountEnd;
    }

    public void setBonusRandomAmountEnd(double BonusRandomAmountEnd) {
        this.BonusRandomAmountEnd = BonusRandomAmountEnd;
    }

    public Object getFullDiscount() {
        return fullDiscount;
    }

    public void setFullDiscount(Object fullDiscount) {
        this.fullDiscount = fullDiscount;
    }

    public String getColorAlias() {
        return ColorAlias;
    }

    public void setColorAlias(String ColorAlias) {
        this.ColorAlias = ColorAlias;
    }

    public String getSizeAlias() {
        return SizeAlias;
    }

    public void setSizeAlias(String SizeAlias) {
        this.SizeAlias = SizeAlias;
    }

    public String getVersionAlias() {
        return VersionAlias;
    }

    public void setVersionAlias(String VersionAlias) {
        this.VersionAlias = VersionAlias;
    }

    public boolean isIsDistribution() {
        return IsDistribution;
    }

    public void setIsDistribution(boolean IsDistribution) {
        this.IsDistribution = IsDistribution;
    }

    public String getBrokerage() {
        return Brokerage;
    }

    public void setBrokerage(String Brokerage) {
        this.Brokerage = Brokerage;
    }

    public boolean isIsPromoter() {
        return IsPromoter;
    }

    public void setIsPromoter(boolean IsPromoter) {
        this.IsPromoter = IsPromoter;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isIsOpenStore() {
        return IsOpenStore;
    }

    public void setIsOpenStore(boolean IsOpenStore) {
        this.IsOpenStore = IsOpenStore;
    }

    public List<ShopColor> getColor() {
        return Color;
    }

    public void setColor(List<ShopColor> Color) {
        this.Color = Color;
    }

    public List<ShopSize> getSize() {
        return Size;
    }

    public void setSize(List<ShopSize> Size) {
        this.Size = Size;
    }

    public List<ShopVersion> getVersion() {
        return Version;
    }

    public void setVersion(List<ShopVersion> Version) {
        this.Version = Version;
    }

    public List<CustomerBean> getCustomerServices() {
        return CustomerServices;
    }

    public void setCustomerServices(List<CustomerBean> customerServices) {
        CustomerServices = customerServices;
    }

    public static class ProductBean {
        /**
         * ProductId : 733
         * ProductSaleStatus : 1
         * AuditStatus : 2
         * ImagePath : []
         * ProductName : 丹麦依诺维绅 功能沙发床 时尚沙发 书房必用 小鸟
         * MarketPrice : 5000.0
         * ShortDescription : 丹麦设计 独立袋装弹簧 北欧时尚简约
         * ProductDescription : <p><img src="http://mall.xigyu.com//Storage/Shop/1/Products/733/remark/97ae97b480ba4ff6b83cf39c79c55b5a.jpg" title="605303069192223693765340_x.jpg" alt="605303069192223693765340_x.jpg"/></p>
         * MinSalePrice : 3982.5
         * IsFavorite : true
         * Consultations : 0
         * CommentCount : 0
         * NicePercent : 100
         * IsOnLimitBuy : false
         * "BrandId":100,
         * "BrandName":"佰益莱",
         * "CategoryId":51,
         * "CategoryName":"超薄电视",
         * "ParentCategoryId":46,
         * "ParentCategoryName":"电视"
         */

        private int ProductId;
        private int ProductSaleStatus;
        private int AuditStatus;
        private String ProductName;
        private double MarketPrice;
        private String ShortDescription;
        private String ProductDescription;
        private double MinSalePrice;
        private boolean IsFavorite;
        private boolean IsInstall;
        private int Consultations;
        private int CommentCount;
        private int NicePercent;
        private boolean IsOnLimitBuy;
        private List<String> ImagePath;
        private String BrandId;
        private String BrandName;
        private String CategoryId;
        private String CategoryName;
        private String ParentCategoryId;
        private String ParentCategoryName;

        public boolean isInstall() {
            return IsInstall;
        }

        public void setInstall(boolean install) {
            IsInstall = install;
        }

        public String getBrandId() {
            return BrandId;
        }

        public void setBrandId(String brandId) {
            BrandId = brandId;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String brandName) {
            BrandName = brandName;
        }

        public String getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(String categoryId) {
            CategoryId = categoryId;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public String getParentCategoryId() {
            return ParentCategoryId;
        }

        public void setParentCategoryId(String parentCategoryId) {
            ParentCategoryId = parentCategoryId;
        }

        public String getParentCategoryName() {
            return ParentCategoryName;
        }

        public void setParentCategoryName(String parentCategoryName) {
            ParentCategoryName = parentCategoryName;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getProductSaleStatus() {
            return ProductSaleStatus;
        }

        public void setProductSaleStatus(int ProductSaleStatus) {
            this.ProductSaleStatus = ProductSaleStatus;
        }

        public int getAuditStatus() {
            return AuditStatus;
        }

        public void setAuditStatus(int AuditStatus) {
            this.AuditStatus = AuditStatus;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public double getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(double MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public String getShortDescription() {
            return ShortDescription;
        }

        public void setShortDescription(String ShortDescription) {
            this.ShortDescription = ShortDescription;
        }

        public String getProductDescription() {
            return ProductDescription;
        }

        public void setProductDescription(String ProductDescription) {
            this.ProductDescription = ProductDescription;
        }

        public double getMinSalePrice() {
            return MinSalePrice;
        }

        public void setMinSalePrice(double MinSalePrice) {
            this.MinSalePrice = MinSalePrice;
        }

        public boolean isIsFavorite() {
            return IsFavorite;
        }

        public void setIsFavorite(boolean IsFavorite) {
            this.IsFavorite = IsFavorite;
        }

        public int getConsultations() {
            return Consultations;
        }

        public void setConsultations(int Consultations) {
            this.Consultations = Consultations;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int CommentCount) {
            this.CommentCount = CommentCount;
        }

        public int getNicePercent() {
            return NicePercent;
        }

        public void setNicePercent(int NicePercent) {
            this.NicePercent = NicePercent;
        }

        public boolean isIsOnLimitBuy() {
            return IsOnLimitBuy;
        }

        public void setIsOnLimitBuy(boolean IsOnLimitBuy) {
            this.IsOnLimitBuy = IsOnLimitBuy;
        }

        public List<String> getImagePath() {
            return ImagePath;
        }

        public void setImagePath(List<String> ImagePath) {
            this.ImagePath = ImagePath;
        }
    }

    public static class CashDepositsServerBean {
        /**
         * IsSevenDayNoReasonReturn : false
         * IsTimelyShip : false
         * IsCustomerSecurity : true
         * CanSelfTake : false
         */

        private boolean IsSevenDayNoReasonReturn;
        private boolean IsTimelyShip;
        private boolean IsCustomerSecurity;
        private boolean CanSelfTake;

        public boolean isIsSevenDayNoReasonReturn() {
            return IsSevenDayNoReasonReturn;
        }

        public void setIsSevenDayNoReasonReturn(boolean IsSevenDayNoReasonReturn) {
            this.IsSevenDayNoReasonReturn = IsSevenDayNoReasonReturn;
        }

        public boolean isIsTimelyShip() {
            return IsTimelyShip;
        }

        public void setIsTimelyShip(boolean IsTimelyShip) {
            this.IsTimelyShip = IsTimelyShip;
        }

        public boolean isIsCustomerSecurity() {
            return IsCustomerSecurity;
        }

        public void setIsCustomerSecurity(boolean IsCustomerSecurity) {
            this.IsCustomerSecurity = IsCustomerSecurity;
        }

        public boolean isCanSelfTake() {
            return CanSelfTake;
        }

        public void setCanSelfTake(boolean CanSelfTake) {
            this.CanSelfTake = CanSelfTake;
        }
    }

    public static class ShopBean {
        /**
         * Name : 官方自营店
         * Id : 1
         * ProductMark : 0.0
         * PackMark : 5.0
         * ServiceMark : 5.0
         * ComprehensiveMark : 5.0
         * CompanyName : null
         * Address : null
         * Phone : null
         * FreeFreight : 11.0
         * ProductNum : 33
         * CouponCount : 0
         * VShopId : 10
         * ProductAndDescription : 5.0
         * SellerServiceAttitude : 5.0
         * SellerDeliverySpeed : 5.0
         * FavoriteShopCount : 2
         */

        private String Name;
        private int Id;
        private double ProductMark;
        private double PackMark;
        private double ServiceMark;
        private double ComprehensiveMark;
        private Object CompanyName;
        private Object Address;
        private Object Phone;
        private double FreeFreight;
        private String ProductNum;
        private int CouponCount;
        private int VShopId;
        private double ProductAndDescription;
        private double SellerServiceAttitude;
        private double SellerDeliverySpeed;
        private String FavoriteShopCount;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public double getProductMark() {
            return ProductMark;
        }

        public void setProductMark(double ProductMark) {
            this.ProductMark = ProductMark;
        }

        public double getPackMark() {
            return PackMark;
        }

        public void setPackMark(double PackMark) {
            this.PackMark = PackMark;
        }

        public double getServiceMark() {
            return ServiceMark;
        }

        public void setServiceMark(double ServiceMark) {
            this.ServiceMark = ServiceMark;
        }

        public double getComprehensiveMark() {
            return ComprehensiveMark;
        }

        public void setComprehensiveMark(double ComprehensiveMark) {
            this.ComprehensiveMark = ComprehensiveMark;
        }

        public Object getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(Object CompanyName) {
            this.CompanyName = CompanyName;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public Object getPhone() {
            return Phone;
        }

        public void setPhone(Object Phone) {
            this.Phone = Phone;
        }

        public double getFreeFreight() {
            return FreeFreight;
        }

        public void setFreeFreight(double FreeFreight) {
            this.FreeFreight = FreeFreight;
        }

        public String getProductNum() {
            return ProductNum;
        }

        public void setProductNum(String ProductNum) {
            this.ProductNum = ProductNum;
        }

        public int getCouponCount() {
            return CouponCount;
        }

        public void setCouponCount(int CouponCount) {
            this.CouponCount = CouponCount;
        }

        public int getVShopId() {
            return VShopId;
        }

        public void setVShopId(int VShopId) {
            this.VShopId = VShopId;
        }

        public double getProductAndDescription() {
            return ProductAndDescription;
        }

        public void setProductAndDescription(double ProductAndDescription) {
            this.ProductAndDescription = ProductAndDescription;
        }

        public double getSellerServiceAttitude() {
            return SellerServiceAttitude;
        }

        public void setSellerServiceAttitude(double SellerServiceAttitude) {
            this.SellerServiceAttitude = SellerServiceAttitude;
        }

        public double getSellerDeliverySpeed() {
            return SellerDeliverySpeed;
        }

        public void setSellerDeliverySpeed(double SellerDeliverySpeed) {
            this.SellerDeliverySpeed = SellerDeliverySpeed;
        }

        public String getFavoriteShopCount() {
            return FavoriteShopCount;
        }

        public void setFavoriteShopCount(String FavoriteShopCount) {
            this.FavoriteShopCount = FavoriteShopCount;
        }
    }

    private class ProductAttributeInfosList {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class ProductAttributeInfolistBean {
        /**
         * key : 接口类型
         * value : USB
         */

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class CashDepositsServerNameBean {
        /**
         * CashServiceName : 七天无理由退换货
         */

        private String CashServiceName;

        public String getCashServiceName() {
            return CashServiceName;
        }

        public void setCashServiceName(String CashServiceName) {
            this.CashServiceName = CashServiceName;
        }
    }
}
