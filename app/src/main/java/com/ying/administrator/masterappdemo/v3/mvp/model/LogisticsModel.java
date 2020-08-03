package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.GetExpressInfoResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.LogisticsContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogisticsModel implements LogisticsContract.Model {
    @Override
    public Observable<GetExpressInfoResult> GetExpressInfo(String ExpressNo) {
        return ApiRetrofit.getDefault().GetExpressInfo(ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
