package com.ying.administrator.masterappdemo.mvp.service;

import com.ying.administrator.masterappdemo.entity.Order_Entity;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitService {

    /*获取工单列表*/
    @GET("Order/GetOrderInfoList")
    Observable<Order_Entity> GetOrderInfoList();
}
