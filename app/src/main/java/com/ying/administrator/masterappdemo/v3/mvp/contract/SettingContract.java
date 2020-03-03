package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;

import io.reactivex.Observable;

public interface SettingContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> LoginOut(String UserID);
    }

    interface View extends BaseView{
        void LoginOut(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void LoginOut(String UserID);
    }
}
