package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.contract.GetOrderListForMeContract;

public class GetOrderListForMePresenter extends GetOrderListForMeContract.Presenter {



    @Override
    public void GetOrderInfoListForMe(String state, String page, String limit, String SendUser) {
        mModel.GetOrderInfoListForMe(state,page,limit,SendUser).subscribe(new BaseObserver<WorkOrder>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                mView.GetOrderInfoListForMe(value);
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
    public void GetChildAccountByParentUserID(String UserID) {

        mModel.GetChildAccountByParentUserID(UserID)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.GetChildAccountByParentUserID(value);
                    }
                });
    }


}
