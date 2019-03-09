package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;

import io.reactivex.Observable;


public interface ReturnAccessoryContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> UpdateContinueServiceState(String OrderID);
        Observable<BaseResult<Data<String>>> PressFactoryAccount(String UserID,String OrderID);
    }
    interface View extends BaseView {
        void UpdateContinueServiceState(BaseResult<Data<String>> baseResult);
        void PressFactoryAccount(BaseResult<Data<String>> baseResult);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void UpdateContinueServiceState(String OrderID);
        public abstract void PressFactoryAccount(String UserID,String OrderID);
    }
}
