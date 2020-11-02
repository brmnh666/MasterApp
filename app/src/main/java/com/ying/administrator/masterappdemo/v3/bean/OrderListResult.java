package com.ying.administrator.masterappdemo.v3.bean;

import com.ying.administrator.masterappdemo.entity.SignsBean;

import java.util.List;

public class OrderListResult {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : {"code":0,"msg":"success","count":1,"data":[{"OrderID":2000019149,"CreateDate":"2020-10-12 17:44:12","UserName":"好老大","Phone":"17512428523","Address":"辽宁省沈阳市和平区沈水湾街道远洋康桥郡3号楼1202","TypeID":"2","TypeName":"安装","Guarantee":"N","SendUser":"18767773654","Longitude":"123.404420","Dimension":"41.749477","Distance":736.04,"DistanceTureOrFalse":true,"OrderProductModels":[{"CategoryName":"冰洗类","SubCategoryName":"冰箱","ProductType":"单门 容积X≤100","ProdModelName":"av-x","BrandName":"猪"}],"Signs":[]}],"errcode":"","errorId":""}
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
         * count : 1
         * data : [{"OrderID":2000019149,"CreateDate":"2020-10-12 17:44:12","UserName":"好老大","Phone":"17512428523","Address":"辽宁省沈阳市和平区沈水湾街道远洋康桥郡3号楼1202","TypeID":"2","TypeName":"安装","Guarantee":"N","SendUser":"18767773654","Longitude":"123.404420","Dimension":"41.749477","Distance":736.04,"DistanceTureOrFalse":true,"OrderProductModels":[{"CategoryName":"冰洗类","SubCategoryName":"冰箱","ProductType":"单门 容积X≤100","ProdModelName":"av-x","BrandName":"猪"}],"Signs":[]}]
         * errcode :
         * errorId :
         */

        private int code;
        private String msg;
        private int count;
        private String errcode;
        private String errorId;
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

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getErrorId() {
            return errorId;
        }

        public void setErrorId(String errorId) {
            this.errorId = errorId;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * OrderID : 2000019149
             * CreateDate : 2020-10-12 17:44:12
             * UserName : 好老大
             * Phone : 17512428523
             * Address : 辽宁省沈阳市和平区沈水湾街道远洋康桥郡3号楼1202
             * TypeID : 2
             * TypeName : 安装
             * Guarantee : N
             * SendUser : 18767773654
             * Longitude : 123.404420
             * Dimension : 41.749477
             * Distance : 736.04
             * DistanceTureOrFalse : true
             * OrderProductModels : [{"CategoryName":"冰洗类","SubCategoryName":"冰箱","ProductType":"单门 容积X≤100","ProdModelName":"av-x","BrandName":"猪"}]
             * Signs : []
             */

            private String OrderID;
            private String CreateDate;
            private String UserName;
            private String Phone;
            private String Address;
            private String TypeID;
            private String TypeName;
            private String Guarantee;
            private String SendUser;
            private String Longitude;
            private String Dimension;
            private String CompanyName;
            private double Distance;
            private boolean DistanceTureOrFalse;
            private List<OrderProductModelsBean> OrderProductModels;
            private List<SignsBean> Signs;

            public String getCompanyName() {
                return CompanyName;
            }

            public void setCompanyName(String companyName) {
                CompanyName = companyName;
            }

            public String getOrderID() {
                return OrderID;
            }

            public void setOrderID(String OrderID) {
                this.OrderID = OrderID;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getTypeID() {
                return TypeID;
            }

            public void setTypeID(String TypeID) {
                this.TypeID = TypeID;
            }

            public String getTypeName() {
                return TypeName;
            }

            public void setTypeName(String TypeName) {
                this.TypeName = TypeName;
            }

            public String getGuarantee() {
                return Guarantee;
            }
            public String getGuaranteeText() {
                return "Y".equals(Guarantee)?"保内":"保外";
            }

            public void setGuarantee(String Guarantee) {
                this.Guarantee = Guarantee;
            }

            public String getSendUser() {
                return SendUser;
            }

            public void setSendUser(String SendUser) {
                this.SendUser = SendUser;
            }

            public String getLongitude() {
                return Longitude;
            }

            public void setLongitude(String Longitude) {
                this.Longitude = Longitude;
            }

            public String getDimension() {
                return Dimension;
            }

            public void setDimension(String Dimension) {
                this.Dimension = Dimension;
            }

            public double getDistance() {
                return Distance;
            }

            public void setDistance(double Distance) {
                this.Distance = Distance;
            }

            public boolean isDistanceTureOrFalse() {
                return DistanceTureOrFalse;
            }

            public void setDistanceTureOrFalse(boolean DistanceTureOrFalse) {
                this.DistanceTureOrFalse = DistanceTureOrFalse;
            }

            public List<OrderProductModelsBean> getOrderProductModels() {
                return OrderProductModels;
            }

            public void setOrderProductModels(List<OrderProductModelsBean> OrderProductModels) {
                this.OrderProductModels = OrderProductModels;
            }

            public List<SignsBean> getSigns() {
                return Signs;
            }

            public void setSigns(List<SignsBean> Signs) {
                this.Signs = Signs;
            }

            public static class OrderProductModelsBean {
                /**
                 * CategoryName : 冰洗类
                 * SubCategoryName : 冰箱
                 * ProductType : 单门 容积X≤100
                 * ProdModelName : av-x
                 * BrandName : 猪
                 */

                private String CategoryName;
                private String SubCategoryName;
                private String ProductType;
                private String ProdModelName;
                private String BrandName;

                public String getCategoryName() {
                    return CategoryName;
                }

                public void setCategoryName(String CategoryName) {
                    this.CategoryName = CategoryName;
                }

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

                public String getProdModelName() {
                    return ProdModelName;
                }

                public void setProdModelName(String ProdModelName) {
                    this.ProdModelName = ProdModelName;
                }

                public String getBrandName() {
                    return BrandName;
                }

                public void setBrandName(String BrandName) {
                    this.BrandName = BrandName;
                }
            }
        }
    }
}
