package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReceiptResult;
import com.ying.administrator.masterappdemo.v3.bean.ConfirmReturnResult;
import com.ying.administrator.masterappdemo.v3.bean.DeleteAccessoryResult;
import com.ying.administrator.masterappdemo.v3.bean.UpdateAccessoryResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AccessoriesDetailsContract;

import okhttp3.RequestBody;

public class AccessoriesDetailsPresenter extends AccessoriesDetailsContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void DeleteAccessory(String AccessoryIDs) {
        mModel.DeleteAccessory(AccessoryIDs)
                .subscribe(new BaseObserver2<DeleteAccessoryResult>() {
                    @Override
                    protected void onHandleSuccess(DeleteAccessoryResult value) {
                        mView.DeleteAccessory(value);
                    }
                });
    }

    @Override
    public void UpdateAccessory(RequestBody json) {
        mModel.UpdateAccessory(json)
                .subscribe(new BaseObserver2<UpdateAccessoryResult>() {
                    @Override
                    protected void onHandleSuccess(UpdateAccessoryResult value) {
                        mView.UpdateAccessory(value);
                    }
                });
    }

    @Override
    public void ConfirmReceipt(String AccessoryID) {
        mModel.ConfirmReceipt(AccessoryID)
                .subscribe(new BaseObserver2<ConfirmReceiptResult>() {
                    @Override
                    protected void onHandleSuccess(ConfirmReceiptResult value) {
                        mView.ConfirmReceipt(value);
                    }
                });
    }

    @Override
    public void ConfirmReturn(RequestBody json) {
        mModel.ConfirmReturn(json)
                .subscribe(new BaseObserver2<ConfirmReturnResult>() {
                    @Override
                    protected void onHandleSuccess(ConfirmReturnResult value) {
                        mView.ConfirmReturn(value);
                    }
                });
    }
}
