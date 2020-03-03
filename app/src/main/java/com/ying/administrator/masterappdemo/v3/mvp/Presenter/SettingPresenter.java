package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.v3.mvp.contract.SettingContract;

public class SettingPresenter extends SettingContract.Presenter {
    @Override
    public void LoginOut(String UserID) {
        mModel.LoginOut(UserID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.LoginOut(value);
                    }
                });
    }
}
