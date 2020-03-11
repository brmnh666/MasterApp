package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Data3;
import com.ying.administrator.masterappdemo.entity.WXOfferQuery;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.QuoteDetailsContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuoteDetailsModel implements QuoteDetailsContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> WXOrderOffer(String SenUserId, String Price, String OrderId, String Reason) {
        return ApiRetrofit.getDefault().WXOrderOffer(SenUserId, Price, OrderId, Reason,"0","Y")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data3<List<WXOfferQuery>>>> WXOfferQuery(String SenUserId, String Price, String OrderId, String Reason) {
        return ApiRetrofit.getDefault().WXOfferQuery(SenUserId,  OrderId,String.valueOf(10),String.valueOf(1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
