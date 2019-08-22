package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class GetOrderListForMeModel implements GetOrderListForMeContract.Model {

    @Override
    public Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit) {
        return ApiRetrofit.getDefault().WorkerGetOrderList(UserID,State,page,limit)
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
    public Observable<BaseResult<Data>> AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage) {
        return ApiRetrofit.getDefault().AddOrderSuccess(OrderID,AppointmentState,AppointmentMessage)
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
    public Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>> GetChildAccountByParentUserID(String ParentUserID) {
        return ApiRetrofit.getDefault().GetChildAccountByParentUserID(ParentUserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<BaseResult<Data>> ChangeSendOrder(String OrderID, String UserID) {
        return ApiRetrofit.getDefault().ChangeSendOrder(OrderID,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID, String State) {
        return ApiRetrofit.getDefault().UpdateSendOrderState(OrderID,State)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateContinueServiceState(String OrderID) {
        return  ApiRetrofit.getDefault().UpdateContinueServiceState(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> PressFactoryAccount(String OrderID,String Content) {
        return  ApiRetrofit.getDefault().PressFactoryAccount(OrderID, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderIsLook(String OrderID, String IsLook) {
        return  ApiRetrofit.getDefault().UpdateOrderIsLook(OrderID, IsLook)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID, String UpdateDate,String EndDate) {
        return ApiRetrofit.getDefault().UpdateSendOrderUpdateTime(OrderID,UpdateDate,EndDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> ApplyAccessoryLate(String OrderID) {
        return ApiRetrofit.getDefault().ApplyAccessoryLate(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> WorkerComplaint(String OrderID, String Content) {
        return ApiRetrofit.getDefault().WorkerComplaint(OrderID, Content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
