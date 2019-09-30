package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

/*购物车中店铺的商品*/
public class CommodityBean implements Serializable {
    private boolean ischeck;

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    /**
     * CartItemId : 182
     * SkuId : 707_675_661_0
     * Id : 707
     * ImgUrl : http://mall.xigyu.com//Storage/Shop/1/Products/707/1_150.png
     * Name : ONLY春季新品含莱卡面料五分袖修身露肩一字领针织连衣裙女E|116361504 010黑 175/92A/XL
     * Price : 90.00
     * Count : 2
     * Size : 165/84(S)
     * Color : 蓝色
     * Version : null
     * ShopId : 1
     * VShopId : 10
     * ShopName : 官方自营店
     * ShopLogo : http://mall.xigyu.com//Storage/Shop/1/VShop/201702141737310419930.png
     * Url : http://mall.xigyu.com//m-IOS/product/detail/707
     * Status : 1
     * ColorAlias : 颜色
     * SizeAlias : 尺码
     * VersionAlias : 规格
     * AddTime : 2019-04-27T10:24:49
     * ShopBranchId : 0
     * ShopBranchName : null
     * ShopBranchLogo : null
     */

    private String CartItemId;
    private String SkuId;
    private String Id;
    private String ImgUrl;
    private String Name;
    private String Price;
    private String Count;
    private String Size;
    private String Color;
    private String Version;
    private String ShopId;
    private String VShopId;
    private String ShopName;
    private String ShopLogo;
    private String Url;
    private int Status;
    private String ColorAlias;
    private String SizeAlias;
    private String VersionAlias;
    private String AddTime;
    private int ShopBranchId;
    private String ShopBranchName;
    private String ShopBranchLogo;

    public String getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(String CartItemId) {
        this.CartItemId = CartItemId;
    }

    public String getSkuId() {
        return SkuId;
    }

    public void setSkuId(String SkuId) {
        this.SkuId = SkuId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String Count) {
        this.Count = Count;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getVShopId() {
        return VShopId;
    }

    public void setVShopId(String VShopId) {
        this.VShopId = VShopId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getShopLogo() {
        return ShopLogo;
    }

    public void setShopLogo(String ShopLogo) {
        this.ShopLogo = ShopLogo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
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

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String AddTime) {
        this.AddTime = AddTime;
    }

    public int getShopBranchId() {
        return ShopBranchId;
    }

    public void setShopBranchId(int ShopBranchId) {
        this.ShopBranchId = ShopBranchId;
    }

    public Object getShopBranchName() {
        return ShopBranchName;
    }

    public void setShopBranchName(String ShopBranchName) {
        this.ShopBranchName = ShopBranchName;
    }

    public Object getShopBranchLogo() {
        return ShopBranchLogo;
    }

    public void setShopBranchLogo(String ShopBranchLogo) {
        this.ShopBranchLogo = ShopBranchLogo;
    }
/*    private String SkuId;
    private String Id; //货物的id
    private String ImgUrl;//商品的图片
    private String Price; //商品的价格
    private String Count;//选择的数量
    private String Size;
    private String Color;
    private String Version;*/



}
