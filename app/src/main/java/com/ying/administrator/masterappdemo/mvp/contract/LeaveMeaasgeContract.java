package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.LeaveMessage;

import java.util.List;

import io.reactivex.Observable;


public interface LeaveMeaasgeContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage( String UserID, String limit, String page);
        Observable<BaseResult<Data>> LeaveMessageWhetherLook(String OrderID);
    }

    interface View extends BaseView{
        void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult);
        void LeaveMessageWhetherLook(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetNewsLeaveMessage(String UserID, String limit, String page);
        public abstract void LeaveMessageWhetherLook(String OrderID);
    }
}
