package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SearchOrderContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchOrderModel implements SearchOrderContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoList(String Phone, String OrderID, String UserID, String limit, String page) {
        return ApiRetrofit.getDefault().GetOrderInfoList(Phone, OrderID, UserID, limit, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
