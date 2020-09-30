package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.v3.bean.GetSignContractManageResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MineContract;

import okhttp3.RequestBody;

public class MinePresenter extends MineContract.Presenter {
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
    public void UploadAvator(RequestBody json) {
        mModel.UploadAvator(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UploadAvator(value);
                    }
                });
    }

    @Override
    public void GetSignContractManage() {
        mModel.GetSignContractManage()
                .subscribe(new BaseObserver2<GetSignContractManageResult>() {
                    @Override
                    protected void onHandleSuccess(GetSignContractManageResult value) {
                        mView.GetSignContractManage(value);
                    }
                });
    }
}
