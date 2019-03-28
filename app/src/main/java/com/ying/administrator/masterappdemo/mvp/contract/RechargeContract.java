package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import io.reactivex.Observable;


public interface RechargeContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> GetOrderStr(String userid, String TotalAmount);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID,String limit);
    }

    interface View extends BaseView {
        void GetOrderStr(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderStr(String userid,String TotalAmount);
        public abstract void GetUserInfoList(String UserID,String limit);
    }
}
