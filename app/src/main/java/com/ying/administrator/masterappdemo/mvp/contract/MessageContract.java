package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.GetLeaveMsgListResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface MessageContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo);
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);

        Observable<GetLeaveMsgListResult> GetLeaveMsgList(String OrderID);

        Observable<BaseResult<Data<String>>> LeaveMessageImg(RequestBody json);
        Observable<BaseResult<Data<String>>> DeleteLeaveMessageImg(String LeaveMessageImgId);
        Observable<BaseResult<Data>> LeaveMessageWhetherLook(String OrderID);



    }

    interface View extends BaseView{
        void AddLeaveMessageForOrder(BaseResult<Data<String>> baseResult);
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void GetLeaveMsgList(GetLeaveMsgListResult baseResult);

        void LeaveMessageImg(BaseResult<Data<String>> baseResult);
        void DeleteLeaveMessageImg(BaseResult<Data<String>> baseResult);
        void LeaveMessageWhetherLook(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void AddLeaveMessageForOrder(String UserID, String OrderId, String Content,String photo);
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void GetLeaveMsgList(String OrderID);

        public abstract void LeaveMessageImg(RequestBody json);
        public abstract void DeleteLeaveMessageImg(String LeaveMessageImgId);
        public abstract void LeaveMessageWhetherLook(String OrderID);
    }
}
