package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ReturnAccessoryContract;

public class ReturnAccessoryPresenter extends ReturnAccessoryContract.Presenter {


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
