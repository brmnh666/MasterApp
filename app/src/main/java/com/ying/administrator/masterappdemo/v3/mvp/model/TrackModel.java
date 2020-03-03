package com.ying.administrator.masterappdemo.v3.mvp.model;


import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Track;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.TrackContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TrackModel implements TrackContract.Model {
    @Override
    public Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId) {
        return ApiRetrofit.getDefault().GetOrderRecordByOrderID(OrderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
