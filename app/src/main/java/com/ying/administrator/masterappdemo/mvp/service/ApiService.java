package com.ying.administrator.masterappdemo.mvp.service;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    /**
     * 判断用户名是否可用
     */
    @FormUrlEncoded
    @POST("Account/ValidateUserName")
    Observable<BaseResult<String>> ValidateUserName(@Field("UserID") String userName);

    /**
     * 获取短信
     */
    @FormUrlEncoded
    @POST("Message/Send")
    Observable<BaseResult<String>> GetCode(@Field("mobile") String mobile,
                                           @Field("type") String type,
                                           @Field("roleType") String roleType);



    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("Account/Reg")
    Observable<BaseResult<String>> Reg(@Field("mobile") String mobile,
                                       @Field("type") String type,
                                       @Field("code") String code,
                                       @Field("roleType") String roleType);


    /**
     * app用户登录
     */
    @FormUrlEncoded
    @POST("Account/LoginOn")
    Observable<BaseResult<String>> LoginOn(@Field("userName") String userName,
                                           @Field("passWord") String passWord);


    /**
     * app获取用户信息
     */
    @POST("Account/GetUserInfo")
    Observable<BaseResult<String>> GetUserInfo(@Body RequestBody json);

    /**
     * app获取用户信息
     */
    @FormUrlEncoded
    @POST("Account/GetUserInfo")
    Observable<BaseResult<String>> GetUserInfo(@Field("userName") String userName);



    /*
     *新增获取更新推送账户的token以及tags， 工厂的type是6 师傅的type是7 ， createtime可以不传 UserID为登录用户名
     * */
    @FormUrlEncoded
    @POST("Message/AddAndUpdatePushAccount")
    Observable<BaseResult<String>> AddAndUpdatePushAccount(@Field("token") String token,
                                                           @Field("type") String type,
                                                           @Field("UserID") String UserID);

    /**
     * 获取工单列表
     * 废除-1，待审核0，派单中1，服务中2，已完成3
     */
    @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoList(@Field("state") String state,
                                                       @Field("page") String page,
                                                       @Field("limit") String limit);


    /*
     *
     * 获取工单详情
     * 通过OrderID获取工单详情
     * */
    @FormUrlEncoded
    @POST("Order/GetOrderInfo")
    Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(@Field("OrderID") String OrderID);



    /**
     * 提交抢单申请
     */
    @FormUrlEncoded
    @POST("Order/GrabOrder")
    Observable<BaseResult<Data>> AddGrabsheetapply (@Field("OrderID") String OrderID,
                                                    @Field("UserID") String UserID);

    /**
     * 获取其他状态的 获取已接的订单
     * **/
    @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(@Field("state") String state,
                                                            @Field("page") String page,
                                                            @Field("limit") String limit,
                                                            @Field("SendUser") String SendUser);
    /**
     * 提交师傅预约失败的原因
     * **/
    @FormUrlEncoded
    @POST("Order/UpdateSendOrderAppointmentState")
    Observable<BaseResult<Data>> AddOrderfailureReason(@Field("OrderID") String OrderID,
                                                       @Field("AppointmentState") String AppointmentState,
                                                       @Field("AppointmentMessage") String AppointmentMessage);

    /*
     *
     * 获取工厂配件信息
     * */
    @POST("FactoryConfig/GetFactoryAccessory")
    Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory();

    /*获取工厂服务信息*/

    @POST("FactoryConfig/GetFactoryService")
    Observable<BaseResult<GetFactorySeviceData<Service>>> GetFactoryService();




    /*
     *
     * 上传图片
     * */
    @POST("Upload/UploadImg")
    Observable<BaseResult<Data<String>>> UploadImg(@Body RequestBody json);

    /*
    * 预接单提交配件信息  json
    *
    *
    * {
    "OrderID":"123",
    "AccessorySequency":"0",
    "OrderAccessoryStr":{
    "OrderAccessory":[
    {
    "FAccessoryID":"1",
    "FAccessoryName":"PC管",
    "Quantity":"2",
    "Price":"1",
    "DiscountPrice":"1"
    }
    ]
    }
    }
    *
    * */
    @POST("Order/AddOrderAccessory")
    Observable<BaseResult<String>> AddOrderAccessory(@Body RequestBody json);





    @FormUrlEncoded
    @POST("Order/UpdateSendOrderUpdateTime")


    Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(@Field("OrderID") String OrderID,
                                                           @Field("UpdateDate") String UpdateDate);

    /**
     * 获取分类
     */
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory();
//    /**
//     * 获取子分类
//     */
//    @FormUrlEncoded
//    @POST("FactoryConfig/GetFactoryCategory")
//    Observable<BaseResult<Data<List<Category>>>> GetChildFactoryCategory(@Field("ParentID") String ParentID);
    /**
     * 更新用户信息
     */
    @FormUrlEncoded
    @POST("Account/UpdateAccountModel")
    Observable<BaseResult<Data<String>>> UpdateAccountModel(
            @Field("UserID") String UserID,
            @Field("TrueName") String TrueName,
            @Field("IDCard") String IDCard,
            @Field("Address") String Address,
            @Field("Skills") String Skills
    );
    /**
     * 提交认证申请
     */
    @FormUrlEncoded
    @POST("Account/ApplyAuth")
    Observable<BaseResult<Data<String>>> ApplyAuth(
            @Field("UserID") String UserID
    );
}
