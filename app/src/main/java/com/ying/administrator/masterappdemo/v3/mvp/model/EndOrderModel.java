package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.EndOrderContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EndOrderModel implements EndOrderContract.Model {
    @Override
    public Observable<OrderListResult> GetOrderList(String Search, String State, String page, String limit) {
        return ApiRetrofit.getDefault().GetOrderList(Search, State, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
