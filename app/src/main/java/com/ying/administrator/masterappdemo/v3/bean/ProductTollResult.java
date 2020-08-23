package com.ying.administrator.masterappdemo.v3.bean;

import java.io.Serializable;
import java.util.List;

public class ProductTollResult implements Serializable {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":0,"msg":"success","count":0,"data":[{"SubCategoryName":"冰箱","ProductType":"两门或三门100L< 容积≤ 350L","OrderProductToll":[{"FPriceTypeID":-2,"TypeName":"上门咨询、鉴定、空跑","FactoryPrice":0,"StandardPrice":0,"PlatformFee":0,"AllAccessoryName":""},{"FPriceTypeID":-1,"TypeName":"打压检漏","FactoryPrice":0,"StandardPrice":0,"PlatformFee":0,"AllAccessoryName":""},{"FPriceTypeID":1,"TypeName":"简修","FactoryPrice":45,"StandardPrice":45,"PlatformFee":10,"AllAccessoryName":"灯泡,门封条,门把手,抽屉,搁物架,上门更换：门饰件,调整门偏移。改善门封弹性,调整门偏移。,改善门封弹性,漏电检测,线路调整,维修接地不良,处理排水管路,漏电检测,线路调整,维修接地不良,处理排水管路"},{"FPriceTypeID":2,"TypeName":"小修","FactoryPrice":65,"StandardPrice":60,"PlatformFee":10,"AllAccessoryName":"门铰链,LED 灯珠,压缩机 PTC 启动器,门开关/磁感应开关,翅片除冰,更换：门体,翅片除冰。关/磁感应开关,重锤式启动器,压缩机电容,过载保护器,重锤式启动器,压缩机电容,过载保护器"},{"FPriceTypeID":3,"TypeName":"中修","FactoryPrice":85,"StandardPrice":80,"PlatformFee":10,"AllAccessoryName":"控制板,触摸屏,传感器,温控开关,化霜加热器,热器,更换：电脑板,温度补偿开关,风扇电机,温度补偿开关,风扇电机"},{"FPriceTypeID":4,"TypeName":"大修","FactoryPrice":190,"StandardPrice":160,"PlatformFee":10,"AllAccessoryName":"干燥过滤器,毛细管,电磁阀,外冷凝器,蒸发器,更换：压缩机,排除脏堵/冰堵。发器,排除脏堵/冰堵。,检漏补焊抽真空加雪种,检漏补焊抽真空加雪种"},{"FPriceTypeID":5,"TypeName":"安装","FactoryPrice":0,"StandardPrice":0,"PlatformFee":10,"AllAccessoryName":""}]}],"errcode":null,"errorId":null,"IsActionExceted":false}
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
         * data : [{"SubCategoryName":"冰箱","ProductType":"两门或三门100L< 容积≤ 350L","OrderProductToll":[{"FPriceTypeID":-2,"TypeName":"上门咨询、鉴定、空跑","FactoryPrice":0,"StandardPrice":0,"PlatformFee":0,"AllAccessoryName":""},{"FPriceTypeID":-1,"TypeName":"打压检漏","FactoryPrice":0,"StandardPrice":0,"PlatformFee":0,"AllAccessoryName":""},{"FPriceTypeID":1,"TypeName":"简修","FactoryPrice":45,"StandardPrice":45,"PlatformFee":10,"AllAccessoryName":"灯泡,门封条,门把手,抽屉,搁物架,上门更换：门饰件,调整门偏移。改善门封弹性,调整门偏移。,改善门封弹性,漏电检测,线路调整,维修接地不良,处理排水管路,漏电检测,线路调整,维修接地不良,处理排水管路"},{"FPriceTypeID":2,"TypeName":"小修","FactoryPrice":65,"StandardPrice":60,"PlatformFee":10,"AllAccessoryName":"门铰链,LED 灯珠,压缩机 PTC 启动器,门开关/磁感应开关,翅片除冰,更换：门体,翅片除冰。关/磁感应开关,重锤式启动器,压缩机电容,过载保护器,重锤式启动器,压缩机电容,过载保护器"},{"FPriceTypeID":3,"TypeName":"中修","FactoryPrice":85,"StandardPrice":80,"PlatformFee":10,"AllAccessoryName":"控制板,触摸屏,传感器,温控开关,化霜加热器,热器,更换：电脑板,温度补偿开关,风扇电机,温度补偿开关,风扇电机"},{"FPriceTypeID":4,"TypeName":"大修","FactoryPrice":190,"StandardPrice":160,"PlatformFee":10,"AllAccessoryName":"干燥过滤器,毛细管,电磁阀,外冷凝器,蒸发器,更换：压缩机,排除脏堵/冰堵。发器,排除脏堵/冰堵。,检漏补焊抽真空加雪种,检漏补焊抽真空加雪种"},{"FPriceTypeID":5,"TypeName":"安装","FactoryPrice":0,"StandardPrice":0,"PlatformFee":10,"AllAccessoryName":""}]}]
         * errcode : null
         * errorId : null
         * IsActionExceted : false
         */

        private int code;
        private String msg;
        private int count;
        private Object errcode;
        private Object errorId;
        private boolean IsActionExceted;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * SubCategoryName : 冰箱
             * ProductType : 两门或三门100L< 容积≤ 350L
             * OrderProductToll : [{"FPriceTypeID":-2,"TypeName":"上门咨询、鉴定、空跑","FactoryPrice":0,"StandardPrice":0,"PlatformFee":0,"AllAccessoryName":""},{"FPriceTypeID":-1,"TypeName":"打压检漏","FactoryPrice":0,"StandardPrice":0,"PlatformFee":0,"AllAccessoryName":""},{"FPriceTypeID":1,"TypeName":"简修","FactoryPrice":45,"StandardPrice":45,"PlatformFee":10,"AllAccessoryName":"灯泡,门封条,门把手,抽屉,搁物架,上门更换：门饰件,调整门偏移。改善门封弹性,调整门偏移。,改善门封弹性,漏电检测,线路调整,维修接地不良,处理排水管路,漏电检测,线路调整,维修接地不良,处理排水管路"},{"FPriceTypeID":2,"TypeName":"小修","FactoryPrice":65,"StandardPrice":60,"PlatformFee":10,"AllAccessoryName":"门铰链,LED 灯珠,压缩机 PTC 启动器,门开关/磁感应开关,翅片除冰,更换：门体,翅片除冰。关/磁感应开关,重锤式启动器,压缩机电容,过载保护器,重锤式启动器,压缩机电容,过载保护器"},{"FPriceTypeID":3,"TypeName":"中修","FactoryPrice":85,"StandardPrice":80,"PlatformFee":10,"AllAccessoryName":"控制板,触摸屏,传感器,温控开关,化霜加热器,热器,更换：电脑板,温度补偿开关,风扇电机,温度补偿开关,风扇电机"},{"FPriceTypeID":4,"TypeName":"大修","FactoryPrice":190,"StandardPrice":160,"PlatformFee":10,"AllAccessoryName":"干燥过滤器,毛细管,电磁阀,外冷凝器,蒸发器,更换：压缩机,排除脏堵/冰堵。发器,排除脏堵/冰堵。,检漏补焊抽真空加雪种,检漏补焊抽真空加雪种"},{"FPriceTypeID":5,"TypeName":"安装","FactoryPrice":0,"StandardPrice":0,"PlatformFee":10,"AllAccessoryName":""}]
             */

            private String SubCategoryName;
            private String ProductType;
            private List<OrderProductTollBean> OrderProductToll;

            public String getSubCategoryName() {
                return SubCategoryName;
            }

            public void setSubCategoryName(String SubCategoryName) {
                this.SubCategoryName = SubCategoryName;
            }

            public String getProductType() {
                return ProductType;
            }

            public void setProductType(String ProductType) {
                this.ProductType = ProductType;
            }

            public List<OrderProductTollBean> getOrderProductToll() {
                return OrderProductToll;
            }

            public void setOrderProductToll(List<OrderProductTollBean> OrderProductToll) {
                this.OrderProductToll = OrderProductToll;
            }

            public static class OrderProductTollBean {
                /**
                 * FPriceTypeID : -2
                 * TypeName : 上门咨询、鉴定、空跑
                 * FactoryPrice : 0
                 * StandardPrice : 0
                 * PlatformFee : 0
                 * AllAccessoryName :
                 */

                private int FPriceTypeID;
                private String TypeName;
                private double FactoryPrice;
                private double StandardPrice;
                private double PlatformFee;
                private String AllAccessoryName;

                public int getFPriceTypeID() {
                    return FPriceTypeID;
                }

                public void setFPriceTypeID(int FPriceTypeID) {
                    this.FPriceTypeID = FPriceTypeID;
                }

                public String getTypeName() {
                    return TypeName;
                }

                public void setTypeName(String TypeName) {
                    this.TypeName = TypeName;
                }

                public double getFactoryPrice() {
                    return FactoryPrice;
                }

                public void setFactoryPrice(double FactoryPrice) {
                    this.FactoryPrice = FactoryPrice;
                }

                public double getStandardPrice() {
                    return StandardPrice;
                }

                public void setStandardPrice(double StandardPrice) {
                    this.StandardPrice = StandardPrice;
                }

                public double getPlatformFee() {
                    return PlatformFee;
                }

                public void setPlatformFee(double PlatformFee) {
                    this.PlatformFee = PlatformFee;
                }

                public String getAllAccessoryName() {
                    return AllAccessoryName;
                }

                public void setAllAccessoryName(String AllAccessoryName) {
                    this.AllAccessoryName = AllAccessoryName;
                }
            }
        }
    }
}
