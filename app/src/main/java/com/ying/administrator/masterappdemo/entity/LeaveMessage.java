package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class LeaveMessage implements Serializable {

    /**
     * NoLeaveMessage : 2
     * count : 9
     * data : [{"Id":114,"LeaveMessageId":114,"UserId":"13806652840","UserName":null,"Type":"1","OrderId":2000002609,"Content":"啦啦啦","photo":null,"CreateDate":"2019-10-29T12:03:48","IsUse":"Y","factoryIslook":null,"workerIslook":null,"platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":94,"LeaveMessageId":94,"UserId":"13806652840","UserName":null,"Type":"1","OrderId":2000002418,"Content":"返回新同学","photo":null,"CreateDate":"2019-10-18T16:04:36","IsUse":"Y","factoryIslook":null,"workerIslook":null,"platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":207,"LeaveMessageId":207,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003037,"Content":"不敢当谁说的","photo":null,"CreateDate":"2019-11-20T16:57:13","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":205,"LeaveMessageId":205,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003037,"Content":"凤凰新村v个","photo":null,"CreateDate":"2019-11-20T16:36:54","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":203,"LeaveMessageId":203,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003037,"Content":"超级想几点睡觉","photo":null,"CreateDate":"2019-11-20T14:30:43","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":201,"LeaveMessageId":201,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003037,"Content":"吃好喝好","photo":null,"CreateDate":"2019-11-20T14:25:23","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":198,"LeaveMessageId":198,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003037,"Content":"东莞服务","photo":null,"CreateDate":"2019-11-20T13:19:12","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":197,"LeaveMessageId":197,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003037,"Content":"哈哈哈哈哈哈哈哈哈哈哈哈","photo":null,"CreateDate":"2019-11-20T13:14:21","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0},{"Id":195,"LeaveMessageId":195,"UserId":"13806652840","UserName":"噢噢","Type":"1","OrderId":2000003090,"Content":"请尽快审核","photo":null,"CreateDate":"2019-11-19T15:07:45","IsUse":"Y","factoryIslook":"2","workerIslook":"2","platformIslook":null,"Order":null,"page":1,"limit":999,"Version":0}]
     */

    private int NoLeaveMessage;
    private int count;
    private List<DataBean> data;

    public int getNoLeaveMessage() {
        return NoLeaveMessage;
    }

    public void setNoLeaveMessage(int NoLeaveMessage) {
        this.NoLeaveMessage = NoLeaveMessage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
         * Id : 114
         * LeaveMessageId : 114
         * UserId : 13806652840
         * UserName : null
         * Type : 1
         * OrderId : 2000002609
         * Content : 啦啦啦
         * photo : null
         * CreateDate : 2019-10-29T12:03:48
         * IsUse : Y
         * factoryIslook : null
         * workerIslook : null
         * platformIslook : null
         * Order : null
         * page : 1
         * limit : 999
         * Version : 0
         */

        private int Id;
        private int LeaveMessageId;
        private String UserId;
        private String UserName;
        private String Type;
        private String OrderId;
        private String Content;
        private String photo;
        private String CreateDate;
        private String IsUse;
        private String factoryIslook;
        private String workerIslook;
        private String platformIslook;
        private String Order;
        private int page;
        private int limit;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getLeaveMessageId() {
            return LeaveMessageId;
        }

        public void setLeaveMessageId(int LeaveMessageId) {
            this.LeaveMessageId = LeaveMessageId;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String OrderId) {
            this.OrderId = OrderId;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

        public String getFactoryIslook() {
            return factoryIslook;
        }

        public void setFactoryIslook(String factoryIslook) {
            this.factoryIslook = factoryIslook;
        }

        public String getWorkerIslook() {
            return workerIslook;
        }

        public void setWorkerIslook(String workerIslook) {
            this.workerIslook = workerIslook;
        }

        public String getPlatformIslook() {
            return platformIslook;
        }

        public void setPlatformIslook(String platformIslook) {
            this.platformIslook = platformIslook;
        }

        public String getOrder() {
            return Order;
        }

        public void setOrder(String Order) {
            this.Order = Order;
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
