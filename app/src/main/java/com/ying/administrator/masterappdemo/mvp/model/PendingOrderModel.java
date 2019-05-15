package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GAccessory;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Logistics;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.PendingOrderContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import java.util.List;

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
    public Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory(String FProductTypeID) {
        return ApiRetrofit.getDefault().GetFactoryAccessory(FProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<GetFactoryData<Service>>> GetFactoryService(String FBrandID,String FCategoryID) {

        return ApiRetrofit.getDefault().GetFactoryService(FBrandID,FCategoryID)
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
    public Observable<BaseResult<Data<String>>> AddOrUpdateAccessoryServiceReturn(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrUpdateAccessoryServiceReturn(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
     public Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID, String UpdateDate,String EndDate) {
         return ApiRetrofit.getDefault().UpdateSendOrderUpdateTime(OrderID,UpdateDate,EndDate)
         .observeOn(AndroidSchedulers.mainThread())
         .subscribeOn(Schedulers.io());
     }

    @Override
    public Observable<BaseResult<List<GAccessory>>> GetOrderAccessoryByOrderID(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderAccessoryByOrderID(OrderID)
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

    @Override
    public Observable<BaseResult<Data<String>>> OrderByondImgPicUpload(RequestBody json) {
        return ApiRetrofit.getDefault().OrderByondImgPicUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyBeyondMoney(String OrderID, String BeyondMoney, String BeyondDistance) {
        return ApiRetrofit.getDefault().ApplyBeyondMoney(OrderID,BeyondMoney,BeyondDistance)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<BaseResult<Data<String>>> PressFactoryAccount(String OrderID,String Content) {
        return ApiRetrofit.getDefault().PressFactoryAccount(OrderID, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID,String ReturnAccessoryMsg,String PostMoney) {
        return ApiRetrofit.getDefault().AddReturnAccessory(OrderID, ReturnAccessoryMsg, PostMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String State) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID,State)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> ConfirmtoFreezeByOrderID(String OrderID, String type) {
        return ApiRetrofit.getDefault().ConfirmtoFreezeByOrderID(OrderID,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo) {
        return ApiRetrofit.getDefault().GetExpressInfo(ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
