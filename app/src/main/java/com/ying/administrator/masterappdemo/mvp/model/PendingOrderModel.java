package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.GetFactorySeviceData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class PendingOrderModel implements PendingOrderContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory() {
        return ApiRetrofit.getDefault().GetFactoryAccessory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<GetFactorySeviceData<Service>>> GetFactoryService() {

        return ApiRetrofit.getDefault().GetFactoryService()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> AddOrderAccessory(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrderAccessory(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> AddOrderService(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrderService(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> AddOrUpdateAccessoryServiceReturn(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrUpdateAccessoryServiceReturn(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
     public Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID, String UpdateDate) {
         return ApiRetrofit.getDefault().UpdateSendOrderUpdateTime(OrderID,UpdateDate)
         .observeOn(AndroidSchedulers.mainThread())
         .subscribeOn(Schedulers.io());
     }


}
