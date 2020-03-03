package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ServingDetailModel implements ServingDetailContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID, String ReturnAccessoryMsg, String PostMoney) {
        return ApiRetrofit.getDefault().AddReturnAccessory(OrderID, ReturnAccessoryMsg, PostMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String State,String Reason) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID,State,Reason)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID, String UpdateDate, String EndDate) {
        return ApiRetrofit.getDefault().UpdateSendOrderUpdateTime(OrderID, UpdateDate, EndDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
