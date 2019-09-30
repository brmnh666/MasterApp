package com.ying.administrator.masterappdemo.mvp.model;

import com.alipay.sdk.auth.APAuthInfo;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.MessageContract;
import com.ying.administrator.masterappdemo.mvp.contract.MyMessageContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MessageModel implements MessageContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> AddLeaveMessageForOrder(String UserID, String OrderId, String Content) {
        return ApiRetrofit.getDefault().AddLeaveMessageForOrder(UserID, "1", OrderId, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> LeaveMessageImg(RequestBody json) {
        return  ApiRetrofit.getDefault().LeaveMessageImg(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> DeleteLeaveMessageImg(String LeaveMessageImgId) {
        return ApiRetrofit.getDefault().DeleteLeaveMessageImg(LeaveMessageImgId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
