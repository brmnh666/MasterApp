package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class GetBrandWithCategory implements Serializable {

    /**
     * Id : 210
     * BrandCategoryID : 210
     * BrandID : 285
     * BrandName : 测试2
     * UserID : 17777777777
     * CategoryID : 304
     * CategoryName : 其他电器类
     * SubCategoryID : 1043
     * SubCategoryName : 滚筒干衣机
     * ProductTypeID : 1044
     * ProductTypeName : 滚筒干衣机
     * CourseCount : <p>v现场v的发送<img src="https://img.xigyu.com/Pics/ueditor/upload/image/20200324/6372066584876586261404161.jpg" title="5.jpg" alt="5.jpg"/></p>
     * Imge : null
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int BrandCategoryID;
    private int BrandID;
    private String BrandName;
    private String UserID;
    private int CategoryID;
    private String CategoryName;
    private int SubCategoryID;
    private String SubCategoryName;
    private int ProductTypeID;
    private String ProductTypeName;
    private String CourseCount;
    private Object Imge;
    private String IsUse;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getBrandCategoryID() {
        return BrandCategoryID;
    }

    public void setBrandCategoryID(int BrandCategoryID) {
        this.BrandCategoryID = BrandCategoryID;
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

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
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

    public int getProductTypeID() {
        return ProductTypeID;
    }

    public void setProductTypeID(int ProductTypeID) {
        this.ProductTypeID = ProductTypeID;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public void setProductTypeName(String ProductTypeName) {
        this.ProductTypeName = ProductTypeName;
    }

    public String getCourseCount() {
        return CourseCount;
    }

    public void setCourseCount(String CourseCount) {
        this.CourseCount = CourseCount;
    }

    public Object getImge() {
        return Imge;
    }

    public void setImge(Object Imge) {
        this.Imge = Imge;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
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
