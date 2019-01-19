package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AllWorkOrdersModel implements AllWorkOrdersContract.Model {

    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoList(String state, String page, String limit) {
        return ApiRetrofit.getDefault().GetOrderInfoList(state, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*抢单操作*/
    @Override
    public Observable<BaseResult<String>> AddGrabsheetapply(String OrderID, String UserID) {
        return ApiRetrofit.getDefault().AddGrabsheetapply(OrderID,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
