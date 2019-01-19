package com.ying.administrator.masterappdemo.mvp.service;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

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



    /**
     * 获取工单列表
     * 废除-1，待审核0，派单中1，服务中2，已完成3
     */
    @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<WorkOrder>> GetOrderInfoList(@Field("state") String state,
                                                       @Field("page") String page,
                                                       @Field("limit") String limit);



    /**
     * 提交抢单申请
     */
@FormUrlEncoded
@POST("Order/GrabOrder")
    Observable<BaseResult<String>> AddGrabsheetapply (@Field("OrderID") String OrderID,
                                                      @Field("UserID") String UserID);


}
