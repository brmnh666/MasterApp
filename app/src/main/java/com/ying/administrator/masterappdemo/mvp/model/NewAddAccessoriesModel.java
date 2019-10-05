package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data2;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.mvp.contract.NewAddAccessoriesContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class NewAddAccessoriesModel implements NewAddAccessoriesContract.Model {

    @Override
    public Observable<BaseResult<GetFactoryData<Accessory>>> GetFactoryAccessory(String FProductTypeID) {
        return ApiRetrofit.getDefault().GetFactoryAccessory(FProductTypeID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data2>> ApplyAccessoryphotoUpload(RequestBody json) {
        return ApiRetrofit.getDefault().ApplyAccessoryphotoUpload(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
