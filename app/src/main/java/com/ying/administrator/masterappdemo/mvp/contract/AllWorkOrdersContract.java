package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;


public interface AllWorkOrdersContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder>> GetOrderInfoList(String state, String page, String limit);
       //抢单操作
        Observable<BaseResult<Data>> AddGrabsheetapply(String OrderID, String UserID);
    }

    interface View extends BaseView {
        void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
        //抢单操作
        void AddGrabsheetapply(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfoList(String state, String page,String limit);
        //抢单操作
        public abstract void AddGrabsheetapply(String OrderID,String UserID);
    }
}
