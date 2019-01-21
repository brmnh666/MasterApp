package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class GetOrderListForMeModel implements GetOrderListForMeContract.Model {

    @Override
    public Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(String state, String page, String limit, String UserID) {
        return ApiRetrofit.getDefault().GetOrderInfoListForMe(state,page,limit,UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }



    /*抢单操作*/

}
