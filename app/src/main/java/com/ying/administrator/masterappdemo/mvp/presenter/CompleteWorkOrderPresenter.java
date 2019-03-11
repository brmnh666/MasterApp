package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;

import okhttp3.RequestBody;

public class CompleteWorkOrderPresenter extends CompleteWorkOrderContract.Presenter {

    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID).subscribe(new BaseObserver<WorkOrder.DataBean>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                mView.GetOrderInfo(value);
            }
        });


    }

    @Override
    public void ServiceOrderPicUpload(RequestBody json) {
        mModel.ServiceOrderPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ServiceOrderPicUpload(value);
            }
        });
    }

    @Override
    public void ReuturnAccessoryPicUpload(RequestBody json) {
        mModel.ReuturnAccessoryPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.ReuturnAccessoryPicUpload(value);
            }
        });
    }

    @Override
    public void FinishOrderPicUpload(RequestBody json) {
        mModel.FinishOrderPicUpload(json).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.FinishOrderPicUpload(value);
            }
        });
    }


 /*   @Override
    public void GetReturnAccessoryByOrderID(String OrderID) {
        mModel.GetReturnAccessoryByOrderID(OrderID).subscribe(new BaseObserver<String>() {
            @Override
            protected void onHandleSuccess(BaseResult<String> value) {
                mView.GetReturnAccessoryByOrderID(value);
            }
        });

    }*/

    @Override
    public void UpdateOrderState(String OrderID, String State) {
        mModel.UpdateOrderState(OrderID,State).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.UpdateOrderState(value);
            }
        });
    }

    @Override
    public void AddReturnAccessory(String OrderID, String ReturnAccessoryMsg) {
        mModel.AddReturnAccessory(OrderID,ReturnAccessoryMsg).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.AddReturnAccessory(value);
            }
        });
    }
}
