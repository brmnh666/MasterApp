package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.huawei.hms.api.Api;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetFactoryData;
import com.ying.administrator.masterappdemo.entity.Service;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ApplicationAccessoriesContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class ApplicationAccessoriesModel implements ApplicationAccessoriesContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> GetFactoryAccessoryMoney(String OrderID, String FCategoryID, String SizeID, String Price) {
        return ApiRetrofit.getDefault().GetFactoryAccessoryMoney(OrderID, FCategoryID, SizeID, Price)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<GetFactoryData<Service>>> GetFactoryService(String FCategoryID) {
        return ApiRetrofit.getDefault().GetFactoryService(FCategoryID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<AddressList>>> GetAccountAddress(String UserId) {
        return ApiRetrofit.getDefault().GetAccountAddress(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderAddressByOrderID(String OrderID, String SendAddress) {
        return ApiRetrofit.getDefault().UpdateOrderAddressByOrderID(OrderID, SendAddress)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> AddOrderAccessoryAndService(RequestBody json) {
        return ApiRetrofit.getDefault().AddOrderAccessoryAndService(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
