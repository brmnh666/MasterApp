package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.CodeMoney;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.HomeContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit,int page2) {
        return ApiRetrofit.getDefault().NewWorkerGetOrderList2(UserID, State, page, limit,page2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<OrderListResult> GetOrderList(String Search, String State, String page, String limit) {
        return ApiRetrofit.getDefault().GetOrderList(Search, State, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit) {
        return  ApiRetrofit.getDefault().GetListCategoryContentByCategoryID(CategoryID,page,limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID, String State, String Reason) {
        return ApiRetrofit.getDefault().UpdateSendOrderState(OrderID, State, Reason)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> messgIsOrNo(String UserID, String limit, String page) {
        return ApiRetrofit.getDefault().messgIsOrNo(UserID, limit, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<List<CodeMoney>>> GetCodeList(String Code, String limit, String page) {
        return ApiRetrofit.getDefault().GetCodeList(Code, limit, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
