package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetMessagePag;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;

import java.util.List;

import io.reactivex.Observable;

public interface MessageContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
        Observable<BaseResult<MessageData<List<Message>>>> GetOrderMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetTicketMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetReviewMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetComplaintMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(String UserID, String limit, String page);
        Observable<BaseResult<Data<GetMessagePag>>> GetmessagePag(String UserID);

    }

    interface View extends BaseView {
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);

        void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTicketMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetReviewMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetComplaintMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult);
        void GetmessagePag(BaseResult<Data<GetMessagePag>> baseResult);

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
        public abstract void GetOrderMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTicketMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetReviewMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetComplaintMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTransactionMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetNewsLeaveMessage(String UserID, String limit, String page);
        public abstract void GetmessagePag(String UserID);
    }
}
