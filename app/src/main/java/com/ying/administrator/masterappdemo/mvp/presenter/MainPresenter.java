package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.MainContract;

import java.util.List;

public class MainPresenter extends MainContract.Presenter {


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
    public void GetMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }

    @Override
    public void GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }


}
