package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.GetOrderMoneyDetailResult;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ServingDetailModel implements ServingDetailContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID, String ReturnAccessoryMsg, String PostMoney) {
        return ApiRetrofit.getDefault().AddReturnAccessory(OrderID, ReturnAccessoryMsg, PostMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String State,String Reason) {
        return ApiRetrofit.getDefault().UpdateOrderState(OrderID,State,Reason)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID, String UpdateDate, String EndDate) {
        return ApiRetrofit.getDefault().UpdateSendOrderUpdateTime(OrderID, UpdateDate, EndDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<Data<List<GetBrandWithCategory>>>> GetBrandWithCategory2(String UserID, String BrandID, String CategoryID, String SubCategoryID, String ProductTypeID, String page, String limit) {
        return ApiRetrofit.getDefault().GetBrandWithCategory2(UserID, BrandID, CategoryID, SubCategoryID, ProductTypeID,page,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AddOrderSignInRecrodResult> AddOrderSignInRecrod(String userId, String signInType, String orderId) {
        return ApiRetrofit.getDefault().AddOrderSignInRecrod(userId,signInType,orderId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit) {
        return ApiRetrofit.getDefault().GetUserInfoList(UserID, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ProductTollResult> ProductToll(String OrderID, String UserId) {
        return ApiRetrofit.getDefault().ProductToll(OrderID, UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GetOrderMoneyDetailResult> GetOrderMoneyDetail(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderMoneyDetail(OrderID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
