package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    /**
     * code : 0
     * msg : success
     * count : 2
     * data : [{"Id":1,"CategoryContentID":1,"Title":"今天出大事啦","Author":"小西瓜","Source":null,"Url":null,"Content":"      今天是一个伟大的日子，\n因为万众期待的西瓜鱼APP正式发布啦。","CategoryID":5,"ParentCategoryID":1,"IsUse":"Y","CreateTime":"2019-03-01T14:26:01","page":1,"limit":999,"Version":0},{"Id":2,"CategoryContentID":2,"Title":"今天出大事啦","Author":"管理员02","Source":"CCTV","Url":null,"Content":"      今天是一个伟大的日子，\n因为万众期待的西瓜鱼APP正式发布啦。","CategoryID":5,"ParentCategoryID":1,"IsUse":"Y","CreateTime":"2019-03-01T14:26:01","page":1,"limit":999,"Version":0}]
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
         * CategoryContentID : 1
         * Title : 今天出大事啦
         * Author : 小西瓜
         * Source : null
         * Url : null
         * Content :       今天是一个伟大的日子，
         因为万众期待的西瓜鱼APP正式发布啦。
         * CategoryID : 5
         * ParentCategoryID : 1
         * IsUse : Y
         * CreateTime : 2019-03-01T14:26:01
         * page : 1
         * limit : 999
         * Version : 0
         */

        private String Id;
        private String CategoryContentID;
        private String Title;
        private String Author;
        private String Source;
        private String Url;
        private String Content;
        private String CategoryID;
        private String ParentCategoryID;
        private String IsUse;
        private String CreateTime;
        private String page;
        private String limit;
        private String Version;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getCategoryContentID() {
            return CategoryContentID;
        }

        public void setCategoryContentID(String CategoryContentID) {
            this.CategoryContentID = CategoryContentID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String Author) {
            this.Author = Author;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getCategoryID() {
            return CategoryID;
        }

        public void setCategoryID(String CategoryID) {
            this.CategoryID = CategoryID;
        }

        public String getParentCategoryID() {
            return ParentCategoryID;
        }

        public void setParentCategoryID(String ParentCategoryID) {
            this.ParentCategoryID = ParentCategoryID;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
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
