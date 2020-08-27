package com.ying.administrator.masterappdemo.v3.bean;

import java.io.Serializable;
import java.util.List;

public class CourseResult implements Serializable {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":0,"msg":"success","count":0,"data":{"FactoryData":[{"Title":"冰箱资料1","Content":"<p><img src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Image/20200823/ddefcb70-95f0-482e-b78a-01ced793c984.png\" title=\"微信截图_20200808162523.png\" alt=\"微信截图_20200808162523.png\" width=\"672\" height=\"556\" style=\"width: 672px; height: 556px;\"/><\/p><p>这是冰箱的维修资料1<\/p><p><video class=\"edui-upload-video video-js vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Video/20200823/ae6ce344-48cb-4454-9ce6-59a272a154ad.mp4\" data-setup=\"{}\"><source src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Video/20200823/ae6ce344-48cb-4454-9ce6-59a272a154ad.mp4\" type=\"video/mp4\"/>v<\/video><\/p><p><br/><\/p>"},{"Title":"冰箱维修资料2","Content":"<p style=\"text-align: center;\">这是维修资料2222222222<br/><\/p><p style=\"text-align: center;\"><img src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Image/20200823/f8f60ff7-c3aa-4f9e-8b8b-486d5cd5537a.png\" title=\"微信截图_20200808162535.png\" alt=\"微信截图_20200808162535.png\" width=\"777\" height=\"227\" style=\"width: 777px; height: 227px;\"/><\/p><p style=\"text-align: center;\">居中对齐<\/p>"}],"ReferenceData":[]},"errcode":null,"errorId":null,"IsActionExceted":false}
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
         * count : 0
         * data : {"FactoryData":[{"Title":"冰箱资料1","Content":"<p><img src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Image/20200823/ddefcb70-95f0-482e-b78a-01ced793c984.png\" title=\"微信截图_20200808162523.png\" alt=\"微信截图_20200808162523.png\" width=\"672\" height=\"556\" style=\"width: 672px; height: 556px;\"/><\/p><p>这是冰箱的维修资料1<\/p><p><video class=\"edui-upload-video video-js vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Video/20200823/ae6ce344-48cb-4454-9ce6-59a272a154ad.mp4\" data-setup=\"{}\"><source src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Video/20200823/ae6ce344-48cb-4454-9ce6-59a272a154ad.mp4\" type=\"video/mp4\"/>v<\/video><\/p><p><br/><\/p>"},{"Title":"冰箱维修资料2","Content":"<p style=\"text-align: center;\">这是维修资料2222222222<br/><\/p><p style=\"text-align: center;\"><img src=\"https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Image/20200823/f8f60ff7-c3aa-4f9e-8b8b-486d5cd5537a.png\" title=\"微信截图_20200808162535.png\" alt=\"微信截图_20200808162535.png\" width=\"777\" height=\"227\" style=\"width: 777px; height: 227px;\"/><\/p><p style=\"text-align: center;\">居中对齐<\/p>"}],"ReferenceData":[]}
         * errcode : null
         * errorId : null
         * IsActionExceted : false
         */

        private int code;
        private String msg;
        private int count;
        private DataBean data;
        private Object errcode;
        private Object errorId;
        private boolean IsActionExceted;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public Object getErrcode() {
            return errcode;
        }

        public void setErrcode(Object errcode) {
            this.errcode = errcode;
        }

        public Object getErrorId() {
            return errorId;
        }

        public void setErrorId(Object errorId) {
            this.errorId = errorId;
        }

        public boolean isIsActionExceted() {
            return IsActionExceted;
        }

        public void setIsActionExceted(boolean IsActionExceted) {
            this.IsActionExceted = IsActionExceted;
        }

        public static class DataBean {
            private List<FactoryDataBean> FactoryData;
            private List<FactoryDataBean> ReferenceData;

            public List<FactoryDataBean> getFactoryData() {
                return FactoryData;
            }

            public void setFactoryData(List<FactoryDataBean> FactoryData) {
                this.FactoryData = FactoryData;
            }

            public List<FactoryDataBean> getReferenceData() {
                return ReferenceData;
            }

            public void setReferenceData(List<FactoryDataBean> ReferenceData) {
                this.ReferenceData = ReferenceData;
            }

            public static class FactoryDataBean {
                /**
                 * Title : 冰箱资料1
                 * Content : <p><img src="https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Image/20200823/ddefcb70-95f0-482e-b78a-01ced793c984.png" title="微信截图_20200808162523.png" alt="微信截图_20200808162523.png" width="672" height="556" style="width: 672px; height: 556px;"/></p><p>这是冰箱的维修资料1</p><p><video class="edui-upload-video video-js vjs-default-skin video-js" controls="" preload="none" width="420" height="280" src="https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Video/20200823/ae6ce344-48cb-4454-9ce6-59a272a154ad.mp4" data-setup="{}"><source src="https://xigyubucket.oss-cn-hangzhou.aliyuncs.com/Product/Uploads/UeDitOr/Video/20200823/ae6ce344-48cb-4454-9ce6-59a272a154ad.mp4" type="video/mp4"/>v</video></p><p><br/></p>
                 */

                private String Title;
                private String Content;

                public String getTitle() {
                    return Title;
                }

                public void setTitle(String Title) {
                    this.Title = Title;
                }

                public String getContent() {
                    return Content;
                }

                public void setContent(String Content) {
                    this.Content = Content;
                }
            }
        }
    }
}
