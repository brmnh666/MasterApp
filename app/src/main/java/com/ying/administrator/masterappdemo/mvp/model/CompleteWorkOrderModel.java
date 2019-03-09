package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class CompleteWorkOrderModel implements CompleteWorkOrderContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ServiceOrderPicUpload(RequestBody json) {
        return ApiRetrofit.getDefault().ServiceOrderPicUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ReuturnAccessoryPicUpload(RequestBody json) {
        return ApiRetrofit.getDefault().ReuturnAccessoryPicUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> FinishOrderPicUpload(RequestBody json) {
        return ApiRetrofit.getDefault().FinishOrderPicUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

   /* @Override
    public Observable<BaseResult<String>> GetReturnAccessoryByOrderID(String OrderID) {
        return ApiRetrofit.getDefault().GetReturnAccessoryByOrderID(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }*/

    @Override
    public Observable<BaseResult<Data>> UpdateOrderState(String OrderID, String State) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID,State)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
