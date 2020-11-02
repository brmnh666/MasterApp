package com.ying.administrator.masterappdemo.mvp.model;

import com.ying.administrator.masterappdemo.api.ApiRetrofit;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.contract.MyMessageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyMessageModel implements MyMessageContract.Model {

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String Type,String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, Type,SubType,limit,page,"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*已读*/
    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetReadMessageList(String UserID, String Type, String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, Type,SubType,limit,page,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddOrUpdatemessage(String MessageID, String IsLook) {
        return ApiRetrofit.getDefault().AddOrUpdatemessage(MessageID,IsLook)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> AllRead(String UserID, String Type, String SubType) {
        return ApiRetrofit.getDefault().AllRead(UserID, Type, SubType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
