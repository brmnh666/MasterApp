package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetMessagePag;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MainContract;

import okhttp3.RequestBody;

public class MainPresenter extends MainContract.Presenter {
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
    public void GetmessagePag(String UserID) {
        mModel.GetmessagePag(UserID)
                .subscribe(new BaseObserver<Data<GetMessagePag>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<GetMessagePag>> value) {
                        mView.GetmessagePag(value);
                    }
                });
    }

    @Override
    public void AndroidTelephone(RequestBody json) {
        mModel.AndroidTelephone(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AndroidTelephone(value);
                    }
                });
    }
}
