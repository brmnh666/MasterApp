package com.ying.administrator.masterappdemo.v3.mvp.model;

import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetMessagePag;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.mvp.service.ApiRetrofit;
import com.ying.administrator.masterappdemo.v3.mvp.contract.MessageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageModel implements MessageContract.Model {
    @Override
    public Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit) {
        return ApiRetrofit.getDefault().GetListCategoryContentByCategoryID(CategoryID, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetOrderMessageList(String UserID, String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetTicketMessageList(String UserID, String SubType, String limit, String page) {
        return  ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetReviewMessageList(String UserID, String SubType, String limit, String page) {
        return  ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetComplaintMessageList(String UserID, String SubType, String limit, String page) {
        return ApiRetrofit.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        return  ApiRetrofit.getDefault().GetMessageList(UserID, "1",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(String UserID, String limit, String page) {
        return ApiRetrofit.getDefault().GetNewsLeaveMessage(UserID,"1",limit,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<GetMessagePag>>> GetmessagePag(String UserID) {
        return ApiRetrofit.getDefault().GetmessagePag(UserID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}