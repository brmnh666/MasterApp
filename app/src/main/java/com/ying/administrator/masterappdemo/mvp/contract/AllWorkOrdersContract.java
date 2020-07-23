package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;
import okhttp3.RequestBody;


/*home页面*/
public interface AllWorkOrdersContract {
    interface Model extends BaseModel {
      //获取列表新接口
       Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID,String State,String page,String limit);

        //获取列表旧接口
        // Observable<BaseResult<WorkOrder>> GetOrderInfoList(String SendUser,String state, String page, String limit);
        //抢单操作
        // Observable<BaseResult<Data>> AddGrabsheetapply(String OrderID, String UserID);
        //接单操作
         Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID,String State,String Reason);
        //根据用户名获取已抢订单
        //  Observable<BaseResult<WorkOrder>> GetOrderInfoListForMe(String state, String page, String limit,String UserID);
        //获取用户信息
        Observable<BaseResult<String>> GetUserInfo(String userName);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID,String limit);
        //更新时间
        Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
        //预约成功
        Observable<BaseResult<Data>> AddOrderSuccess(String OrderID, String AppointmentState, String AppointmentMessage);
        //未预约成功
        Observable<BaseResult<Data>> AddOrderfailureReason(String OrderID, String AppointmentState, String AppointmentMessage);

        //上传远程费图片
        Observable<BaseResult<Data<String>>> OrderByondImgPicUpload(RequestBody json);
        //申请远程费
        Observable<BaseResult<Data<String>>> ApplyBeyondMoney(String OrderID,String BeyondMoney,String BeyondDistance,String Bak);

        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
    }

    interface View extends BaseView {
        //获取列表新接口
        void WorkerGetOrderList(BaseResult<WorkOrder>baseResult);

        // void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
        //抢单操作
        // void AddGrabsheetapply(BaseResult<Data> baseResult);
        //根据用户名获取已抢订单
        // void GetOrderInfoListForMe(BaseResult<WorkOrder> baseResult);
        //接单操作
        void UpdateSendOrderState(BaseResult<Data> baseResult);
        //获取用户信息
        void GetUserInfo(BaseResult<String> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);

        //更新时间
        void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult);
        //预约成功

        void AddOrderSuccess(BaseResult<Data> baseResult);
        //未预约成功
        void AddOrderfailureReason(BaseResult<Data> baseResult);
        //上传远程费图片
        void OrderByondImgPicUpload(BaseResult<Data<String>> baseResult);

        //申请远程费
        void ApplyBeyondMoney(BaseResult<Data<String>> baseResult);

        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        //获取列表新接口
        public abstract void WorkerGetOrderList(String UserID,String State,String page,String limit);

        // public abstract void GetOrderInfoList(String SendUser,String state, String page,String limit);
        //抢单操作
        // public abstract void AddGrabsheetapply(String OrderID,String UserID);
        //接单操作
        public abstract void UpdateSendOrderState(String OrderID,String State,String Reason);
        //根据用户名获取已抢订单
        //public abstract void GetOrderInfoListForMe(String state, String page, String limit,String UserID);
        //获取用户信息
        public abstract void GetUserInfo(String userName);
        public abstract void GetUserInfoList(String UserID,String limit);

        //更新时间
        public abstract void UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
        //预约成功
        public abstract void AddOrderSuccess(String OrderID,String AppointmentState,String AppointmentMessage);
        //未预约成功
        public abstract void AddOrderfailureReason(String OrderID,String AppointmentState,String AppointmentMessage);
        //上传远程费图片
        public abstract void OrderByondImgPicUpload(RequestBody json);
        //申请远程费
        public abstract void ApplyBeyondMoney(String OrderID,String BeyondMoney,String BeyondDistance,String Bak);

        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
    }
}
