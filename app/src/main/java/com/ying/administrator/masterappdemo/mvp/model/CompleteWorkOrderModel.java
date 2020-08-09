package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.EndResult;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class CompleteWorkOrderModel implements CompleteWorkOrderContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
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
    public Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String State,String Reason) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID,State,Reason)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID, String ReturnAccessoryMsg,String PostMoney) {
        return ApiRetrofit.getDefault().AddReturnAccessory(OrderID,ReturnAccessoryMsg, PostMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddbarCode(String barCode, String OrderID) {
        return ApiRetrofit.getDefault().AddbarCode(barCode, OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<EndResult> End(RequestBody json) {
        return ApiRetrofit.getDefault().End(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
