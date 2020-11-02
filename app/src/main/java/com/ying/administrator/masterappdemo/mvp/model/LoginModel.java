package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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
        return ApiRetrofit.getDefault().LoginOnMessage(mobile,code,"3","7")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> LoginOut(String UserID) {
        return ApiRetrofit.getDefault().LoginOut(UserID,"7")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<WxRegister>>> WxRegister(String openid, String nickname, String sex, String language, String city, String province, String country, String headimgurl, String unionid) {
        return ApiRetrofit.getDefault().WxRegister(openid, nickname, sex, language, city, province, country, headimgurl, unionid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
