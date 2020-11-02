package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.mvp.contract.LeaveMeaasgeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LeaveMeaasgeModel implements LeaveMeaasgeContract.Model {
    @Override
    public Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(String UserID, String limit, String page) {
        return ApiRetrofit.getDefault().GetNewsLeaveMessage(UserID,"1",limit,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> LeaveMessageWhetherLook(String OrderID) {
        return ApiRetrofit.getDefault().LeaveMessageWhetherLook(OrderID,"1","2","1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
