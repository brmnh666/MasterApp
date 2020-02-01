package com.ying.administrator.masterappdemo.v3.MVC.model;

import com.huawei.hms.api.Api;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.MVC.contract.HomeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit) {
        return ApiRetrofit.getDefault().NewWorkerGetOrderList(UserID, State, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
