package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ReturnAccessoryContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ReturnAccessoryModel implements ReturnAccessoryContract.Model {

    @Override
    public Observable<BaseResult<Data<String>>> UpdateContinueServiceState(String OrderID) {
        return  ApiRetrofit.getDefault().UpdateContinueServiceState(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> PressFactoryAccount(String UserID, String OrderID) {
        return  ApiRetrofit.getDefault().PressFactoryAccount(UserID, OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
