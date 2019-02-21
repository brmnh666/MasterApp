package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;


public interface GetOrderListForMeContract {
    interface Model extends BaseModel {

        //根据用户名获取已抢订单
        Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(String state, String page, String limit, String SendUser);
        //未预约成功
        Observable<BaseResult<Data>> AddOrderfailureReason(String OrderID, String AppointmentState, String AppointmentMessage);

    }

    interface View extends BaseView {
        //根据用户名获取已抢订单
        void GetOrderInfoListForMe(BaseResult<WorkOrder> baseResult);

        //未预约成功
        void AddOrderfailureReason(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        //根据用户名获取已抢订单
        public abstract void GetOrderInfoListForMe(String state, String page, String limit,String SendUser);
        //未预约成功
        public abstract void AddOrderfailureReason(String OrderID,String AppointmentState,String AppointmentMessage);
    }
}
