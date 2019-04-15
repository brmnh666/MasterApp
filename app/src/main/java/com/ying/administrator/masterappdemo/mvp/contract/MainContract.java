package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;


/*mainactivity的接口请求页面*/
public interface MainContract {
    interface Model extends BaseModel {

        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);

        Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String SubType, String limit, String page);

        Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page);

    }

    interface View extends BaseView {

        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void GetUserInfoList(String UserID,String limit);
        public abstract void GetMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTransactionMessageList(String UserID,String SubType,String limit,String page);
    }
}
