package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import io.reactivex.Observable;


public interface WalletContract {
    interface Model extends BaseModel{
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
    }

    interface View extends BaseView{
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetUserInfoList(String UserId,String limit);
    }
}
