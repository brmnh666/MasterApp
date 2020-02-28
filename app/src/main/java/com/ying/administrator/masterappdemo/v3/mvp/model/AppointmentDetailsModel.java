package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.huawei.hms.api.Api;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AppointmentDetailsContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppointmentDetailsModel implements AppointmentDetailsContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> OrderIsCall(String OrderID, String IsCall) {
        return ApiRetrofit.getDefault().OrderIsCall(OrderID, IsCall)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID, String State,String Reason) {
        return ApiRetrofit.getDefault().UpdateSendOrderState(OrderID,State,Reason)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID, String UpdateDate, String EndDate) {
        return ApiRetrofit.getDefault().UpdateSendOrderUpdateTime(OrderID, UpdateDate, EndDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage) {
        return ApiRetrofit.getDefault().AddOrderSuccess(OrderID, AppointmentState, AppointmentMessage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
