package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class GetOrderListForMeModel implements GetOrderListForMeContract.Model {

    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(String state, String page, String limit, String SendUser) {
        return ApiRetrofit.getDefault().GetOrderInfoListForMe(state,page,limit,SendUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*废除订单*/
    @Override
    public Observable<BaseResult<Data>> AddOrderfailureReason(String OrderID, String AppointmentState, String AppointmentMessage) {
        return ApiRetrofit.getDefault().AddOrderfailureReason(OrderID,AppointmentState,AppointmentMessage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserID,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*得到 子账户*/
    @Override
    public Observable<BaseResult<String>> GetChildAccountByParentUserID(String UserID) {
        return ApiRetrofit.getDefault().GetChildAccountByParentUserID(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
