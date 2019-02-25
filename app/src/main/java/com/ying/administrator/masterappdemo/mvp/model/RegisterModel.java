package com.ying.administrator.masterappdemo.mvp.model;

import com.huawei.hms.api.Api;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.mvp.contract.RegisterContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel implements RegisterContract.Model {
    @Override
    public Observable<BaseResult<String>> Reg(String userName, String code) {
        return ApiRetrofit.getDefault().Reg(userName,"Reg",code,"worker")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> GetCode(String userName) {
        return ApiRetrofit.getDefault().GetCode(userName,"Reg","Master")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> Login(String userName, String password) {
        return ApiRetrofit.getDefault().LoginOn(userName,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> ValidateUserName(String userName) {
        return ApiRetrofit.getDefault().ValidateUserName(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
