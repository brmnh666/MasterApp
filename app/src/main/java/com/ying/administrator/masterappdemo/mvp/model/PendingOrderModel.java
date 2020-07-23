package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
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
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
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
    public Observable<BaseResult<GetFactoryData<Service>>> GetFactoryService(String FCategoryID) {

        return ApiRetrofit.getDefault().GetFactoryService(FCategoryID)
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
    public Observable<BaseResult<Data>> AddOrderAccessoryAndService(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrderAccessoryAndService(json)
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
    public Observable<BaseResult<Data2>> ApplyAccessoryphotoUpload(RequestBody json) {
        return ApiRetrofit.getDefault().ApplyAccessoryphotoUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<Data<String>>> ApplyBeyondMoney(String OrderID, String BeyondMoney, String BeyondDistance,String Bak) {
        return ApiRetrofit.getDefault().ApplyBeyondMoney(OrderID,BeyondMoney,BeyondDistance,Bak)
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
    public Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String State,String Reason) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID,State,Reason)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> ConfirmtoFreezeByOrderID(String OrderID, String type,String AccessoryId) {
        return ApiRetrofit.getDefault().ConfirmtoFreezeByOrderID(OrderID,type,AccessoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<Logistics>>> GetExpressInfo(String ExpressNo) {
        return ApiRetrofit.getDefault().GetExpressInfo(ExpressNo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<AddressList>>> GetAccountAddress(String UserId) {
        return ApiRetrofit.getDefault().GetAccountAddress(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderAddressByOrderID(String OrderID, String SendAddress) {
        return ApiRetrofit.getDefault().UpdateOrderAddressByOrderID(OrderID, SendAddress)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetFactoryAccessoryMoney(String OrderID, String FCategoryID, String SizeID,String Price) {
        return ApiRetrofit.getDefault().GetFactoryAccessoryMoney(OrderID, FCategoryID,SizeID,Price)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
