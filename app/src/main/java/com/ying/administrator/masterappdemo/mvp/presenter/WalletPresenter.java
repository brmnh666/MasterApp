package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.WalletContract;

public class WalletPresenter extends WalletContract.Presenter {
    @Override
    public void GetUserInfoList(String UserId, String limit) {
        mModel.GetUserInfoList(UserId, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void AccountBill(String UserID,String state) {
        mModel.AccountBill(UserID,state)
                .subscribe(new BaseObserver<Data<Bill>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Bill>> value) {
                        mView.AccountBill(value);
                    }
                });
    }
}
