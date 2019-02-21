package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;


/*home页面*/
public interface AllWorkOrdersContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder>> GetOrderInfoList(String state, String page, String limit);
       //抢单操作
        Observable<BaseResult<Data>> AddGrabsheetapply(String OrderID, String UserID);
        //根据用户名获取已抢订单
      //  Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(String state, String page, String limit,String UserID);

        //获取用户信息
        Observable<BaseResult<String>> GetUserInfo(String userName);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID,String limit);


    }

    interface View extends BaseView {
        void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
        //抢单操作
        void AddGrabsheetapply(BaseResult<Data> baseResult);
        //根据用户名获取已抢订单
       // void GetOrderInfoListForMe(BaseResult<WorkOrder> baseResult);
        //获取用户信息
        void GetUserInfo(BaseResult<String> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfoList(String state, String page,String limit);
        //抢单操作
        public abstract void AddGrabsheetapply(String OrderID,String UserID);
        //根据用户名获取已抢订单
        //public abstract void GetOrderInfoListForMe(String state, String page, String limit,String UserID);
        //获取用户信息
        public abstract void GetUserInfo(String userName);
        public abstract void GetUserInfoList(String UserID,String limit);

    }
}
