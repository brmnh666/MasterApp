package com.ying.administrator.masterappdemo.v3.bean;

import java.io.Serializable;
import java.util.List;

public class GetOrderMoneyDetailResult implements Serializable {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":0,"msg":"success","count":0,"data":{"Item1":[{"OrderFeeID":144449,"Type":"1","TypeName":"维修费/安装费","Describes":"志高 洗衣机\u2014全自动波轮洗衣机_XQB75-2010","FactoryMoney":0,"MasterPrice":0,"text":null,"ProductTypeID":283},{"OrderFeeID":160252,"Type":"2","TypeName":"平台费","Describes":"志高 洗衣机\u2014全自动波轮洗衣机_XQB75-2010","FactoryMoney":10,"MasterPrice":0,"text":null,"ProductTypeID":283},{"OrderFeeID":168971,"Type":"3","TypeName":"上门费","Describes":"第1次上门费","FactoryMoney":30,"MasterPrice":30,"text":null,"ProductTypeID":null}],"Item2":40,"Item3":30},"errcode":null,"errorId":null,"IsActionExceted":false}
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
         * data : {"Item1":[{"OrderFeeID":144449,"Type":"1","TypeName":"维修费/安装费","Describes":"志高 洗衣机\u2014全自动波轮洗衣机_XQB75-2010","FactoryMoney":0,"MasterPrice":0,"text":null,"ProductTypeID":283},{"OrderFeeID":160252,"Type":"2","TypeName":"平台费","Describes":"志高 洗衣机\u2014全自动波轮洗衣机_XQB75-2010","FactoryMoney":10,"MasterPrice":0,"text":null,"ProductTypeID":283},{"OrderFeeID":168971,"Type":"3","TypeName":"上门费","Describes":"第1次上门费","FactoryMoney":30,"MasterPrice":30,"text":null,"ProductTypeID":null}],"Item2":40,"Item3":30}
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
            /**
             * Item1 : [{"OrderFeeID":144449,"Type":"1","TypeName":"维修费/安装费","Describes":"志高 洗衣机\u2014全自动波轮洗衣机_XQB75-2010","FactoryMoney":0,"MasterPrice":0,"text":null,"ProductTypeID":283},{"OrderFeeID":160252,"Type":"2","TypeName":"平台费","Describes":"志高 洗衣机\u2014全自动波轮洗衣机_XQB75-2010","FactoryMoney":10,"MasterPrice":0,"text":null,"ProductTypeID":283},{"OrderFeeID":168971,"Type":"3","TypeName":"上门费","Describes":"第1次上门费","FactoryMoney":30,"MasterPrice":30,"text":null,"ProductTypeID":null}]
             * Item2 : 40.0
             * Item3 : 30.0
             */

            private double Item2;
            private double Item3;
            private List<Item1Bean> Item1;

            public double getItem2() {
                return Item2;
            }

            public void setItem2(double Item2) {
                this.Item2 = Item2;
            }

            public double getItem3() {
                return Item3;
            }

            public void setItem3(double Item3) {
                this.Item3 = Item3;
            }

            public List<Item1Bean> getItem1() {
                return Item1;
            }

            public void setItem1(List<Item1Bean> Item1) {
                this.Item1 = Item1;
            }

            public static class Item1Bean {
                /**
                 * OrderFeeID : 144449
                 * Type : 1
                 * TypeName : 维修费/安装费
                 * Describes : 志高 洗衣机—全自动波轮洗衣机_XQB75-2010
                 * FactoryMoney : 0.0
                 * MasterPrice : 0.0
                 * text : null
                 * ProductTypeID : 283
                 */

                private int OrderFeeID;
                private String Type;
                private String TypeName;
                private String Describes;
                private double FactoryMoney;
                private double MasterPrice;
                private Object text;
                private int ProductTypeID;

                public int getOrderFeeID() {
                    return OrderFeeID;
                }

                public void setOrderFeeID(int OrderFeeID) {
                    this.OrderFeeID = OrderFeeID;
                }

                public String getType() {
                    return Type;
                }

                public void setType(String Type) {
                    this.Type = Type;
                }

                public String getTypeName() {
                    return TypeName;
                }

                public void setTypeName(String TypeName) {
                    this.TypeName = TypeName;
                }

                public String getDescribes() {
                    return Describes;
                }

                public void setDescribes(String Describes) {
                    this.Describes = Describes;
                }

                public double getFactoryMoney() {
                    return FactoryMoney;
                }

                public void setFactoryMoney(double FactoryMoney) {
                    this.FactoryMoney = FactoryMoney;
                }

                public double getMasterPrice() {
                    return MasterPrice;
                }

                public void setMasterPrice(double MasterPrice) {
                    this.MasterPrice = MasterPrice;
                }

                public Object getText() {
                    return text;
                }

                public void setText(Object text) {
                    this.text = text;
                }

                public int getProductTypeID() {
                    return ProductTypeID;
                }

                public void setProductTypeID(int ProductTypeID) {
                    this.ProductTypeID = ProductTypeID;
                }
            }
        }
    }
}
