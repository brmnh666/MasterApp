package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.SubAccountContract;

import java.util.List;


public class SubAccountPresenter extends SubAccountContract.Presenter {


    @Override
    public void GetUserInfoList(String UserID, String limit) {
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
}
