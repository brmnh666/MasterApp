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
        Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(String state, String page, String limit, String SendUser);
        //未预约成功
        Observable<BaseResult<Data>> AddOrderfailureReason(String OrderID, String AppointmentState, String AppointmentMessage);
        //获取自己的信息
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);

        //获取子账号
        Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>> GetChildAccountByParentUserID(String ParentUserID);

        //主账号派单操作
        Observable<BaseResult<Data>> ChangeSendOrder(String OrderID,String UserID);

    }

    interface View extends BaseView {
        //根据用户名获取已抢订单
        void GetOrderInfoListForMe(BaseResult<WorkOrder> baseResult);

        //未预约成功
        void AddOrderfailureReason(BaseResult<Data> baseResult);

        //获取自己的信息
        void GetUserInfoList(BaseResult<UserInfo> baseResult);

        //获取子账号
        void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult);

        //主账号派单操作
        void ChangeSendOrder(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        //根据用户名获取已抢订单
        public abstract void GetOrderInfoListForMe(String state, String page, String limit,String SendUser);
        //未预约成功
        public abstract void AddOrderfailureReason(String OrderID,String AppointmentState,String AppointmentMessage);
        //获取自己的信息
        public abstract void GetUserInfoList(String UserID,String limit);
        //获取子账号
        public abstract void GetChildAccountByParentUserID(String ParentUserID);
        //主账号派单操作
        public abstract void ChangeSendOrder(String OrderID,String UserID);
    }
}
