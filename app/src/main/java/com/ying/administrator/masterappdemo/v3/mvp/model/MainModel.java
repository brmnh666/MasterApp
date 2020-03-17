package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.huawei.hms.api.Api;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetMessagePag;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MainContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainModel implements MainContract.Model {
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserID, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<GetMessagePag>>> GetmessagePag(String UserID) {
        return ApiRetrofit.getDefault().GetmessagePag(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
