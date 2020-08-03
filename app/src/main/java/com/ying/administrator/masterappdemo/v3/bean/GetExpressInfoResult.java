package com.ying.administrator.masterappdemo.v3.bean;

import java.util.List;

public class GetExpressInfoResult {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":0,"msg":"ok","count":0,"data":{"code":null,"no":"777017624040913","type":null,"state":"3","issign":"1","name":"申通快递","site":"www.sto.cn ","phone":"95543","logo":null,"courier":"洪塘一部派件员1","courierPhone":"18158566856","updateTime":"2020-07-31 10:38:35","list":[{"content":"已签收,签收人是（同事代签）先生/女士，如有疑问请联系派件员洪塘一部派件员1(18158566856)，感谢使用申通快递，期待再次为您服务","time":"2020-07-31 10:38:35"},{"content":"浙江宁波江北公司-洪塘一部营业厅(13819869903)-派件中","time":"2020-07-31 07:52:56"},{"content":"已到达-浙江宁波江北公司","time":"2020-07-31 07:50:17"},{"content":"已到达-浙江宁波江北公司","time":"2020-07-31 07:43:27"},{"content":"浙江宁波转运中心-已发往-浙江宁波江北公司","time":"2020-07-31 01:56:54"},{"content":"华北转运中心-已发往-浙江宁波转运中心","time":"2020-07-30 02:03:20"},{"content":"已到达-华北转运中心","time":"2020-07-30 01:23:55"},{"content":"河北廊坊公司-揽收专用一庞博KH-已收件","time":"2020-07-29 17:38:22"}]},"errcode":"","errorId":null,"IsActionExceted":false}
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
         * msg : ok
         * count : 0
         * data : {"code":null,"no":"777017624040913","type":null,"state":"3","issign":"1","name":"申通快递","site":"www.sto.cn ","phone":"95543","logo":null,"courier":"洪塘一部派件员1","courierPhone":"18158566856","updateTime":"2020-07-31 10:38:35","list":[{"content":"已签收,签收人是（同事代签）先生/女士，如有疑问请联系派件员洪塘一部派件员1(18158566856)，感谢使用申通快递，期待再次为您服务","time":"2020-07-31 10:38:35"},{"content":"浙江宁波江北公司-洪塘一部营业厅(13819869903)-派件中","time":"2020-07-31 07:52:56"},{"content":"已到达-浙江宁波江北公司","time":"2020-07-31 07:50:17"},{"content":"已到达-浙江宁波江北公司","time":"2020-07-31 07:43:27"},{"content":"浙江宁波转运中心-已发往-浙江宁波江北公司","time":"2020-07-31 01:56:54"},{"content":"华北转运中心-已发往-浙江宁波转运中心","time":"2020-07-30 02:03:20"},{"content":"已到达-华北转运中心","time":"2020-07-30 01:23:55"},{"content":"河北廊坊公司-揽收专用一庞博KH-已收件","time":"2020-07-29 17:38:22"}]}
         * errcode :
         * errorId : null
         * IsActionExceted : false
         */

        private int code;
        private String msg;
        private int count;
        private DataBean data;
        private String errcode;
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

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
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
            /**
             * code : null
             * no : 777017624040913
             * type : null
             * state : 3
             * issign : 1
             * name : 申通快递
             * site : www.sto.cn
             * phone : 95543
             * logo : null
             * courier : 洪塘一部派件员1
             * courierPhone : 18158566856
             * updateTime : 2020-07-31 10:38:35
             * list : [{"content":"已签收,签收人是（同事代签）先生/女士，如有疑问请联系派件员洪塘一部派件员1(18158566856)，感谢使用申通快递，期待再次为您服务","time":"2020-07-31 10:38:35"},{"content":"浙江宁波江北公司-洪塘一部营业厅(13819869903)-派件中","time":"2020-07-31 07:52:56"},{"content":"已到达-浙江宁波江北公司","time":"2020-07-31 07:50:17"},{"content":"已到达-浙江宁波江北公司","time":"2020-07-31 07:43:27"},{"content":"浙江宁波转运中心-已发往-浙江宁波江北公司","time":"2020-07-31 01:56:54"},{"content":"华北转运中心-已发往-浙江宁波转运中心","time":"2020-07-30 02:03:20"},{"content":"已到达-华北转运中心","time":"2020-07-30 01:23:55"},{"content":"河北廊坊公司-揽收专用一庞博KH-已收件","time":"2020-07-29 17:38:22"}]
             */

            private Object code;
            private String no;
            private Object type;
            private String state;
            private String issign;
            private String name;
            private String site;
            private String phone;
            private Object logo;
            private String courier;
            private String courierPhone;
            private String updateTime;
            private List<ListBean> list;

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getIssign() {
                return issign;
            }

            public void setIssign(String issign) {
                this.issign = issign;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getLogo() {
                return logo;
            }

            public void setLogo(Object logo) {
                this.logo = logo;
            }

            public String getCourier() {
                return courier;
            }

            public void setCourier(String courier) {
                this.courier = courier;
            }

            public String getCourierPhone() {
                return courierPhone;
            }

            public void setCourierPhone(String courierPhone) {
                this.courierPhone = courierPhone;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * content : 已签收,签收人是（同事代签）先生/女士，如有疑问请联系派件员洪塘一部派件员1(18158566856)，感谢使用申通快递，期待再次为您服务
                 * time : 2020-07-31 10:38:35
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
}
