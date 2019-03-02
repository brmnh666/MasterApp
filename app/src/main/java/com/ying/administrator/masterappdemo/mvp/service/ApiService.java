package com.ying.administrator.masterappdemo.mvp.service;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.Province;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.Skill;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
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
                                           @Field("passWord") String passWord,
                                           @Field("RoleType") String RoleType);


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


    /*获取用户信息*/
    @FormUrlEncoded
    @POST("Account/GetUserInfoList")
    Observable<BaseResult<UserInfo>> GetUserInfoList(@Field("UserID") String UserID,
                                                     @Field("limit") String limit);


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
     * 获取首页 的单子
     */
    @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoList(@Field("SendUser") String SendUser,
                                                       @Field("state") String state,
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


    /*主账号转派订单操作*/

    @FormUrlEncoded
    @POST("Order/ChangeSendOrder")
    Observable<BaseResult<Data>> ChangeSendOrder(@Field("OrderID") String OrderID,
                                          @Field("UserID") String UserID);

  /*  *//*获取派单列表*//*
    @FormUrlEncoded
    @POST("Order/GetSendOrderList")
    Observable<BaseResult<SendOrder>> GetSendOrderList(@Field())


*/
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

    /**
     * 上传身份证以及清晰头像 UserID用户名Sort（1：正面，2：反面，3：清晰自拍照）
     * */
    @POST("Upload/IDCardUpload")
    Observable<BaseResult<Data<String>>> IDCardUpload(@Body RequestBody json);

    /**
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
    Observable<BaseResult<Data>> AddOrderAccessory(@Body RequestBody json);
     /*添加服务*/
    @POST("Order/AddOrderService")
    Observable<BaseResult<Data>> AddOrderService(@Body RequestBody json);


    @POST("Order/AddOrUpdateAccessoryServiceReturn")
    Observable<BaseResult<Data>> AddOrUpdateAccessoryServiceReturn(@Body RequestBody json);


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

   /*获取账户的技能*/
    @FormUrlEncoded
    @POST("Account/GetAccountSkill")
    Observable<BaseResult<List<Skill>>> GetAccountSkill(@Field("UserID") String UserID);

    /**
     * 添加技能
     * @param UserID
     * @return
     */
    @FormUrlEncoded
    @POST("Account/UpdateAccountSkillData")
    Observable<BaseResult<String>> UpdateAccountSkillData(@Field("UserID") String UserID,@Field("NodeIds") String NodeIds);

    /**
     * 获取省
     */
    @POST("Config/GetProvince")
    Observable<BaseResult<List<Province>>> GetProvince();
    /**
     * 获取市
     */
    @FormUrlEncoded
    @POST("Config/GetCity")
    Observable<BaseResult<Data<List<City>>>> GetCity(@Field("parentcode") String parentcode);
    /**
     * 获取区
     */
    @FormUrlEncoded
    @POST("Config/GetArea")
    Observable<BaseResult<Data<List<Area>>>> GetArea(@Field("parentcode") String parentcode);

    /**
     * 获取区
     */
    @FormUrlEncoded
    @POST("Config/GetDistrict")
    Observable<BaseResult<Data<List<District>>>> GetDistrict(@Field("parentcode") String parentcode);
    /**
     * 提交认证申请
     */
    @FormUrlEncoded
    @POST("Account/ApplyAuthInfo")
    Observable<BaseResult<Data<String>>> ApplyAuthInfo(
            @Field("UserID") String UserID,
            @Field("TrueName") String TrueName,
            @Field("IDCard") String IDCard,
            @Field("Address") String Address,
            @Field("NodeIds") String NodeIds,
            @Field("Province") String Province,
            @Field("City") String City,
            @Field("Area") String Area,
            @Field("District") String District,
            @Field("Longitude") String Longitude,
            @Field("Dimension") String Dimension,
            @Field("ServiceAreaJsonStr") String ServiceAreaJsonStr
    );

    /*
    获取子账号*/
    @FormUrlEncoded
    @POST("Account/GetChildAccountByParentUserID")
    Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>>
    GetChildAccountByParentUserID(@Field("ParentUserID") String ParentUserID);



    /*
    修改个人信息页面
    */

    /*个人信息页面修改头像*/
    @POST("Upload/UploadAvator")
    Observable<BaseResult<Data<String>>> UploadAvator(@Body RequestBody json);

    /*获取身份证的图片*/
    @FormUrlEncoded
    @POST("Account/GetIDCardImg")
    Observable<BaseResult<List<IDCard.IDCardBean>>> GetIDCardImg(@Field("UserID") String UserID);

    /*修改昵称*/
    @FormUrlEncoded
    @POST("Account/UpdateAccountNickName")
    Observable<BaseResult<Data>> UpdateAccountNickName(@Field("UserID") String UserID,
                                                       @Field("NickName") String NickName);
    /*修改密码*/

    @FormUrlEncoded
    @POST("Account/UpdatePassword")
    Observable<BaseResult<Data>> UpdatePassword(@Field("UserID") String UserID,
                                                @Field("Password") String Password);
    /*修改性别*/
    @FormUrlEncoded
    @POST("Account/UpdateSex")
    Observable<BaseResult<Data>> UpdateSex(@Field("UserID") String UserID,
                                           @Field("Sex") String Sex);


}
