package com.ying.administrator.masterappdemo.mvp.model;


import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.ComplaintList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ComplaintContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class ComplaintModel implements ComplaintContract.Model {
    @Override
    public Observable<BaseResult<Data<String>>> WorkerComplaint(String OrderID, String Content, String ComplaintType, String photo) {
        return ApiRetrofit.getDefault().WorkerComplaint2(OrderID, Content,ComplaintType,photo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ComPlaintImg(RequestBody json) {
        return ApiRetrofit.getDefault().ComPlaintImg(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<ComplaintList>>> GetComplaintListByOrderId(String OrderId, String UserID) {
        return ApiRetrofit.getDefault().GetComplaintListByOrderId(OrderId,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
