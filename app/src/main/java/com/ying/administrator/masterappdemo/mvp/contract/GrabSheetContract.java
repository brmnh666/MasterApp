package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;

import io.reactivex.Observable;

public interface GrabSheetContract {
    interface Model extends BaseModel{
        Observable<BaseResult<String>>AddGrabsheetapply(String OrderID,String UserID);

    }

    interface View extends BaseView{
        void AddGrabsheetapply(BaseResult<String> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void AddGrabsheetapply(String OrderID,String UserID);
    }
}
