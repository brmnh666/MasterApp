package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetMessagePag;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import io.reactivex.Observable;

public interface MainContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        Observable<BaseResult<Data<GetMessagePag>>> GetmessagePag(String UserID);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void GetmessagePag(BaseResult<Data<GetMessagePag>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserID,String limit);
        public abstract void GetmessagePag(String UserID);
    }
}
