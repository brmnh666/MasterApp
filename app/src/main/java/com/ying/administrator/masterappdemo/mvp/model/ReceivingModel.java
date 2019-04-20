package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.RedPointData;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.contract.ReceivingContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReceivingModel implements ReceivingContract.Model {
    @Override
    public Observable<BaseResult<RedPointData>> WorkerGetOrderRed(String UserId) {
        return ApiRetrofit.getDefault().WorkerGetOrderRed(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
