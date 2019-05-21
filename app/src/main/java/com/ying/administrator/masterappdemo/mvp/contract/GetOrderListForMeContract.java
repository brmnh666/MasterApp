package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;


public interface GetOrderListForMeContract {
    interface Model extends BaseModel {

        //根据用户名获取已抢订单
        Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID,String State,String page,String limit);
        //未预约成功
        Observable<BaseResult<Data>> AddOrderfailureReason(String OrderID, String AppointmentState, String AppointmentMessage);

        //预约成功
        Observable<BaseResult<Data>> AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage);

        //获取自己的信息
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        //获取子账号
        Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>> GetChildAccountByParentUserID(String ParentUserID);
        //主账号派单操作
        Observable<BaseResult<Data>> ChangeSendOrder(String OrderID,String UserID);
        //取消订单
        Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID,String State);//State为-1取消订单
        Observable<BaseResult<Data<String>>> UpdateContinueServiceState(String OrderID);
        Observable<BaseResult<Data<String>>> PressFactoryAccount(String OrderID,String Content);

         Observable<BaseResult<Data<String>>> UpdateOrderIsLook(String OrderID,String IsLook);

        //更新时间
        Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);

        //申请延期
        Observable<BaseResult<Data<String>>> ApplyAccessoryLate(String OrderID);
    }

    interface View extends BaseView {
        //根据用户名获取已抢订单
        void WorkerGetOrderList(BaseResult<WorkOrder> baseResult);

        //未预约成功
        void AddOrderfailureReason(BaseResult<Data> baseResult);

        //预约成功

        void AddOrderSuccess(BaseResult<Data> baseResult);

        //获取自己的信息
        void GetUserInfoList(BaseResult<UserInfo> baseResult);

        //获取子账号
        void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult);

        //主账号派单操作
        void ChangeSendOrder(BaseResult<Data> baseResult);

        //取消订单
        void UpdateSendOrderState(BaseResult<Data> baseResult);

        void UpdateContinueServiceState(BaseResult<Data<String>> baseResult);
        void PressFactoryAccount(BaseResult<Data<String>> baseResult);

        void UpdateOrderIsLook(BaseResult<Data<String>> baseResult);

        //更新时间
        void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult);

        void ApplyAccessoryLate(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        //根据用户名获取已抢订单
        public abstract void WorkerGetOrderList(String UserID, String State, String page,String limit);
        //未预约成功
        public abstract void AddOrderfailureReason(String OrderID,String AppointmentState,String AppointmentMessage);
        //预约成功
        public abstract void AddOrderSuccess(String OrderID,String AppointmentState,String AppointmentMessage);
        //获取自己的信息
        public abstract void GetUserInfoList(String UserID,String limit);
        //获取子账号
        public abstract void GetChildAccountByParentUserID(String ParentUserID);
        //主账号派单操作
        public abstract void ChangeSendOrder(String OrderID,String UserID);
        //取消订单
        public abstract void UpdateSendOrderState(String OrderID,String State);

        public abstract void UpdateContinueServiceState(String OrderID);
        public abstract void PressFactoryAccount(String OrderID,String Content);

        public abstract void UpdateOrderIsLook(String OrderID,String IsLook);

        //更新时间
        public abstract void UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);

        public abstract void ApplyAccessoryLate(String OrderId);
    }
}
