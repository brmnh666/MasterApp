package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;

import java.util.List;

public class GetOrderListForMePresenter extends GetOrderListForMeContract.Presenter {



    @Override
    public void WorkerGetOrderList(String UserID, String State, String page, String limit) {
        mModel.WorkerGetOrderList(UserID,State,page,limit).subscribe(new BaseObserver<WorkOrder>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                mView.WorkerGetOrderList(value);
            }
        });

    }

    @Override
    public void AddOrderfailureReason(String OrderID, String AppointmentState, String AppointmentMessage) {
        mModel.AddOrderfailureReason(OrderID,AppointmentState,AppointmentMessage).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrderfailureReason(value);
            }
        });
    }
    @Override
    public void GetUserInfoList(String UserID,String limit) {
        mModel.GetUserInfoList(UserID,limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void GetChildAccountByParentUserID(String ParentUserID) {

        mModel.GetChildAccountByParentUserID(ParentUserID)
                .subscribe(new BaseObserver<List<SubUserInfo.SubUserInfoDean>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<SubUserInfo.SubUserInfoDean>> value) {
                        mView.GetChildAccountByParentUserID(value);
                    }
                });
    }

    @Override
    public void ChangeSendOrder(String OrderID, String UserID) {
        mModel.ChangeSendOrder(OrderID,UserID)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.ChangeSendOrder(value);
                    }
                });
    }

    @Override
    public void UpdateSendOrderState(String OrderID, String State) {
        mModel.UpdateSendOrderState(OrderID,State)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSendOrderState(value);
                    }
                });
    }

    @Override
    public void UpdateContinueServiceState(String OrderID) {
        mModel.UpdateContinueServiceState(OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateContinueServiceState(value);
                    }
                });
    }

    @Override
    public void PressFactoryAccount(String UserID, String OrderID) {
        mModel.PressFactoryAccount(UserID, OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.PressFactoryAccount(value);
                    }
                });
    }
}
