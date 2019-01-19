package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.mvp.contract.GrabSheetContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GrabSheetModel implements GrabSheetContract.Model {
    @Override
    public Observable<BaseResult<String>> AddGrabsheetapply(String OrderID, String UserID) {
        return ApiRetrofit.getDefault().AddGrabsheetapply(OrderID,UserID)
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribeOn(Schedulers.io());
    }
}
