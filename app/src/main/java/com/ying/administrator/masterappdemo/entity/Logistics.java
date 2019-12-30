package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;
import java.util.List;

public class Logistics implements Serializable {


    /**
     * ExpressDetailList : {"data":[{"content":"宁波市【宁波江北一部】，13819849377 已签收","time":"2019-12-14 15:51:58"},{"content":"宁波市【宁波江北一部】，【王洋洋/13819849377】正在派件","time":"2019-12-14 13:10:04"},{"content":"到宁波市【宁波江北一部】","time":"2019-12-14 10:19:10"},{"content":"宁波市【宁波转运中心】，正发往【宁波江北一部】","time":"2019-12-14 02:00:34"},{"content":"到宁波市【宁波转运中心】","time":"2019-12-13 17:47:00"},{"content":"杭州市【杭州转运中心】，正发往【宁波转运中心】","time":"2019-12-13 11:05:33"},{"content":"到杭州市【杭州转运中心】","time":"2019-12-13 11:01:42"},{"content":"福州市【福州转运中心】，正发往【杭州转运中心】","time":"2019-12-12 22:49:54"},{"content":"到福州市【福州转运中心】","time":"2019-12-12 19:18:53"},{"content":"到福州市【福州金山A站集货点】","time":"2019-12-12 16:25:11"},{"content":"福州市【福州金山A站】，【董可爱/18065029222】已揽收","time":"2019-12-12 16:04:02"}],"name":"百世快递"}
     */

    private ExpressDetailListBean ExpressDetailList;

    public ExpressDetailListBean getExpressDetailList() {
        return ExpressDetailList;
    }

    public void setExpressDetailList(ExpressDetailListBean ExpressDetailList) {
        this.ExpressDetailList = ExpressDetailList;
    }

    public static class ExpressDetailListBean {
        /**
         * data : [{"content":"宁波市【宁波江北一部】，13819849377 已签收","time":"2019-12-14 15:51:58"},{"content":"宁波市【宁波江北一部】，【王洋洋/13819849377】正在派件","time":"2019-12-14 13:10:04"},{"content":"到宁波市【宁波江北一部】","time":"2019-12-14 10:19:10"},{"content":"宁波市【宁波转运中心】，正发往【宁波江北一部】","time":"2019-12-14 02:00:34"},{"content":"到宁波市【宁波转运中心】","time":"2019-12-13 17:47:00"},{"content":"杭州市【杭州转运中心】，正发往【宁波转运中心】","time":"2019-12-13 11:05:33"},{"content":"到杭州市【杭州转运中心】","time":"2019-12-13 11:01:42"},{"content":"福州市【福州转运中心】，正发往【杭州转运中心】","time":"2019-12-12 22:49:54"},{"content":"到福州市【福州转运中心】","time":"2019-12-12 19:18:53"},{"content":"到福州市【福州金山A站集货点】","time":"2019-12-12 16:25:11"},{"content":"福州市【福州金山A站】，【董可爱/18065029222】已揽收","time":"2019-12-12 16:04:02"}]
         * name : 百世快递
         */

        private String name;
        private List<DataBean> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 宁波市【宁波江北一部】，13819849377 已签收
             * time : 2019-12-14 15:51:58
             */

            private String content;
            private String time;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
