package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;

public interface OrderContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<NavigationBarNumber>>> NavigationBarNumber(String UserID, String page, String limit);
        Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit);

    }

    interface View extends BaseView{
        void NavigationBarNumber(BaseResult<Data<NavigationBarNumber>> baseResult);
        void WorkerGetOrderList(BaseResult<WorkOrder> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void NavigationBarNumber(String UserID, String page, String limit);
        public abstract void WorkerGetOrderList(String UserID, String State, String page,String limit);

    }
}
