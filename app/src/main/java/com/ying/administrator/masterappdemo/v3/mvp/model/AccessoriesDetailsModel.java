package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReceiptResult;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReturnResult;
import com.ying.administrator.masterappdemo.v3.bean.DeleteAccessoryResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AccessoriesDetailsContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AccessoriesDetailsModel implements AccessoriesDetailsContract.Model {
    @Override
    public Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID) {
        return ApiRetrofit.getDefault().GetOrderInfo(OrderID,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DeleteAccessoryResult> DeleteAccessory(String AccessoryIDs) {
        return ApiRetrofit.getDefault().DeleteAccessory(AccessoryIDs)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ConfirmReceiptResult> ConfirmReceipt(String AccessoryID) {
        return ApiRetrofit.getDefault().ConfirmReceipt(AccessoryID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ConfirmReturnResult> ConfirmReturn(String AccessoryID,String ReturnExpressNo,String PostMoney) {
        return ApiRetrofit.getDefault().ConfirmReturn(AccessoryID,ReturnExpressNo,PostMoney)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
