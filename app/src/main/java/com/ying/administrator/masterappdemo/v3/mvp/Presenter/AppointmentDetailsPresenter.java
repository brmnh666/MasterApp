package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.contract.AppointmentDetailsContract;

import java.util.List;

public class AppointmentDetailsPresenter extends AppointmentDetailsContract.Persenter {
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
    public void OrderIsCall(String OrderID, String IsCall) {
        mModel.OrderIsCall(OrderID, IsCall)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.OrderIsCall(value);
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

    @Override
    public void UpdateSendOrderUpdateTime(String OrderID, String UpdateDate, String EndDate) {
        mModel.UpdateSendOrderUpdateTime(OrderID, UpdateDate, EndDate)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSendOrderUpdateTime(value);
                    }
                });
    }

    @Override
    public void AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage) {
        mModel.AddOrderSuccess(OrderID, AppointmentState, AppointmentMessage)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.AddOrderSuccess(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String UserID, String limit) {
        mModel.GetUserInfoList(UserID, limit)
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
    public void GetBrandWithCategory2(String UserID, String BrandID, String CategoryID, String SubCategoryID, String ProductTypeID, String page, String limit) {
        mModel.GetBrandWithCategory2(UserID, BrandID, CategoryID, SubCategoryID, ProductTypeID,page,limit)
                .subscribe(new BaseObserver<Data<List<GetBrandWithCategory>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<GetBrandWithCategory>>> value) {
                        mView.GetBrandWithCategory2(value);
                    }
                });
    }
}
