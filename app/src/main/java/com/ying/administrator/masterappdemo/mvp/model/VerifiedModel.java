package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class VerifiedModel implements VerifiedContract.Model {



    @Override
    public Observable<BaseResult<Data<String>>> UploadImg(RequestBody json,int code) {
        return  ApiRetrofit.getDefault().UploadImg(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateAccountModel(String UserID, String TrueName, String IDCard, String Address, String Skills) {
        return  ApiRetrofit.getDefault().UpdateAccountModel(UserID, TrueName, IDCard, Address, Skills)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyAuth(String UserID) {
        return  ApiRetrofit.getDefault().ApplyAuth(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
