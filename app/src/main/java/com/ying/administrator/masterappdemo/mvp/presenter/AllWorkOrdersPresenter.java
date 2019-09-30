package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.AllWorkOrdersContract;

public class AllWorkOrdersPresenter extends AllWorkOrdersContract.Presenter {

    @Override
    public void WorkerGetOrderList(String UserID, String State, String page, String limit) {
        mModel.WorkerGetOrderList(UserID,State, page, limit)
                .subscribe(new BaseObserver<WorkOrder>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                        mView.WorkerGetOrderList(value);
                    }
                });

    }

    @Override
    public void UpdateSendOrderState(String OrderID, String State,String Reason) {
        mModel.UpdateSendOrderState(OrderID,State,Reason)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSendOrderState(value);
                    }
                });
    }

  /*  @Override
    public void GetOrderInfoList(String SendUser,String state, String page, String limit) {
        mModel.GetOrderInfoList(SendUser,state, page, limit)
                .subscribe(new BaseObserver<WorkOrder>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                        mView.GetOrderInfoList(value);
                    }
                });
    }
*/
   /* @Override
    public void AddGrabsheetapply(String OrderID, String UserID) {
        mModel.AddGrabsheetapply(OrderID,UserID)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                     mView.AddGrabsheetapply(value);
                    }
                });
    }*/

    @Override
    public void GetUserInfo(String userName) {

        mModel.GetUserInfo(userName)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetUserInfo(value);
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
    public void UpdateSendOrderUpdateTime(String OrderID, String UpdateDate,String EndDate) {
        mModel.UpdateSendOrderUpdateTime(OrderID,UpdateDate,EndDate).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.UpdateSendOrderUpdateTime(value);
            }
        });

    }

    @Override
    public void AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage) {
        mModel.AddOrderSuccess(OrderID,AppointmentState,AppointmentMessage).subscribe(new BaseObserver<Data>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data> value) {
                mView.AddOrderSuccess(value);
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
}
