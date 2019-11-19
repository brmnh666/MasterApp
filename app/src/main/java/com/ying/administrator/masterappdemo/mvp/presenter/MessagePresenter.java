package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.ReadMessage;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.MessageContract;

import java.util.List;

import okhttp3.RequestBody;

public class MessagePresenter extends MessageContract.Presenter {
    @Override
    public void AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo) {
        mModel.AddLeaveMessageForOrder(UserID, OrderId, Content,photo)
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

    @Override
    public void LeaveMessageWhetherLook(String OrderID) {
        mModel.LeaveMessageWhetherLook(OrderID)
                .subscribe(new BaseObserver<Data<List<ReadMessage>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<ReadMessage>>> value) {
                        mView.LeaveMessageWhetherLook(value);
                    }
                });
    }
}
