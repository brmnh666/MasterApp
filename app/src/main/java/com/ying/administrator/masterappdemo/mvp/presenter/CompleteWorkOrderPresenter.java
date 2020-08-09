package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.CompleteWorkOrderContract;
import com.ying.administrator.masterappdemo.v3.bean.EndResult;

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
    public void UpdateOrderState(String OrderID, String State,String Reason) {
        mModel.UpdateOrderState(OrderID,State,Reason).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.UpdateOrderState(value);
            }
        });
    }

    @Override
    public void AddReturnAccessory(String OrderID, String ReturnAccessoryMsg,String PostMoney) {
        mModel.AddReturnAccessory(OrderID,ReturnAccessoryMsg, PostMoney).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.AddReturnAccessory(value);
            }
        });
    }

    @Override
    public void AddbarCode(String barCode, String OrderID) {
        mModel.AddbarCode(barCode, OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddbarCode(value);
                    }
                });
    }

    @Override
    public void End(RequestBody json) {
        mModel.End(json)
                .subscribe(new BaseObserver2<EndResult>() {
                    @Override
                    protected void onHandleSuccess(EndResult value) {
                        mView.End(value);
                    }
                });
    }
}
