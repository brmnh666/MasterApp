package com.ying.administrator.masterappdemo.mvp.presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.contract.MyMessageContract;

import java.util.List;

public class MyMessagePresenter extends MyMessageContract.Presenter {


    @Override
    public void GetMessageList(String UserID, String Type,String SubType,String limit,String page) {
        mModel.GetMessageList(UserID, Type,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }

    @Override
    public void GetReadMessageList(String UserID, String Type, String SubType, String limit, String page) {
        mModel.GetReadMessageList(UserID, Type,SubType,limit,page)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetReadMessageList(value);
                    }
                });
    }

    @Override
    public void AddOrUpdatemessage(String MessageID, String IsLook) {
        mModel.AddOrUpdatemessage(MessageID,IsLook)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOrUpdatemessage(value);
                    }
                });
    }

    @Override
    public void AllRead(String UserID, String Type, String SubType) {
        mModel.AllRead(UserID, Type, SubType)
                .subscribe(new BaseObserver<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.AllRead(value);
                    }
                });
    }
}
