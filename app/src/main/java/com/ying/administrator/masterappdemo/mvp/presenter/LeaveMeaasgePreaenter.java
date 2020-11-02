package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.mvp.contract.LeaveMeaasgeContract;

public class LeaveMeaasgePreaenter extends LeaveMeaasgeContract.Presenter {
    @Override
    public void GetNewsLeaveMessage(String UserID, String limit, String page) {
        mModel.GetNewsLeaveMessage(UserID, limit, page)
                .subscribe(new BaseObserver<Data<LeaveMessage>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<LeaveMessage>> value) {
                        mView.GetNewsLeaveMessage(value);
                    }
                });
    }

    @Override
    public void LeaveMessageWhetherLook(String OrderID) {
        mModel.LeaveMessageWhetherLook(OrderID)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.LeaveMessageWhetherLook(value);
                    }
                });
    }
}
