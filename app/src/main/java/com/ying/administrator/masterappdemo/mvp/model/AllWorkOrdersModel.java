package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AllWorkOrdersModel implements AllWorkOrdersContract.Model {

    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoList(String SendUser,String state, String page, String limit) {
        return ApiRetrofit.getDefault().GetOrderInfoList(SendUser,state, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    /*抢单操作*/
    @Override
    public Observable<BaseResult<Data>> AddGrabsheetapply(String OrderID, String UserID) {
        return  ApiRetrofit.getDefault().AddGrabsheetapply(OrderID,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<String>> GetUserInfo(String userName) {
        return  ApiRetrofit.getDefault().GetUserInfo(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID,String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserID,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


}
