package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class VerifiedModel implements VerifiedContract.Model {



    @Override
    public Observable<BaseResult<Data<String>>> IDCardUpload(RequestBody json,int code) {
        return  ApiRetrofit.getDefault().IDCardUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit) {
        return  ApiRetrofit.getDefault().GetUserInfoList(UserID,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyAuthInfo(String UserID, String TrueName,String Sex, String IDCard, String Address, String NodeIds, String Province, String City, String Area, String District, String Longitude, String Dimension,String ServiceAreaJsonStr,String ISwoker) {
        return  ApiRetrofit.getDefault().ApplyAuthInfo(UserID, TrueName,Sex, IDCard, Address, NodeIds, Province, City, Area, District, Longitude, Dimension,ServiceAreaJsonStr,ISwoker)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyAuthInfoBysub(String UserID, String TrueName, String Sex, String IDCard, String Address, String Province, String City, String Area, String District, String Longitude, String Dimension,String ISwoker) {
        return  ApiRetrofit.getDefault().ApplyAuthInfoBysub(UserID, TrueName,Sex, IDCard, Address, Province, City, Area, District, Longitude, Dimension,ISwoker)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
