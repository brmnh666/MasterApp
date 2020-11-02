package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumberSon;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderModel implements OrderContract.Model {
    @Override
    public Observable<BaseResult<Data<NavigationBarNumber>>> NavigationBarNumber(String UserID, String page, String limit) {
        return ApiRetrofit.getDefault().NavigationBarNumber(UserID, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<OrderListResult> GetOrderList(String Search, String State, String page, String limit) {
        return ApiRetrofit.getDefault().GetOrderList(Search, State, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<NavigationBarNumberSon>>> NavigationBarNumberSon(String UserID, String page, String limit) {
        return ApiRetrofit.getDefault().NavigationBarNumberSon(UserID, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit) {
        return ApiRetrofit.getDefault().NewWorkerGetOrderList(UserID, State, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
