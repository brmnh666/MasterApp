package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> Login(String userName, String passWord) {
        return ApiRetrofit.getDefault().LoginOn(userName,passWord,"7")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<String>> GetUserInfo(String userName) {
        return ApiRetrofit.getDefault().GetUserInfo(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*向服务端推送token*/
    @Override
    public Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token, String type, String UserID) {
        return ApiRetrofit.getDefault().AddAndUpdatePushAccount(token,type,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> ValidateUserName(String userName) {
        return ApiRetrofit.getDefault().ValidateUserName(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> GetCode(String userName, String type) {
        return ApiRetrofit.getDefault().GetCode(userName,type,"worker")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<BaseResult<Data<String>>> LoginOnMessage(String mobile, String code) {
        return ApiRetrofit.getDefault().LoginOnMessage(mobile,code,"7")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
