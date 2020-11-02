package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.RedPointData;
import com.ying.administrator.masterappdemo.mvp.contract.ReceivingContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReceivingModel implements ReceivingContract.Model {
    @Override
    public Observable<BaseResult<RedPointData>> WorkerGetOrderRed(String UserId) {
        return ApiRetrofit.getDefault().WorkerGetOrderRed(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
