package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.MessageContract;

import okhttp3.RequestBody;

public class MessagePresenter extends MessageContract.Presenter {
    @Override
    public void AddLeaveMessageForOrder(String UserID, String OrderId, String Content) {
        mModel.AddLeaveMessageForOrder(UserID, OrderId, Content)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddLeaveMessageForOrder(value);
                    }
                });
    }

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
    public void LeaveMessageImg(RequestBody json ) {
        mModel.LeaveMessageImg(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.LeaveMessageImg(value);
                    }
                });
    }

    @Override
    public void DeleteLeaveMessageImg(String LeaveMessageImgId) {
        mModel.DeleteLeaveMessageImg(LeaveMessageImgId)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.DeleteLeaveMessageImg(value);
                    }
                });
    }
}
