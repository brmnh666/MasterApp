package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface CompleteWorkOrderContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);

        //上传服务图片
        Observable<BaseResult<Data<String>>> ServiceOrderPicUpload(RequestBody json);
        //上传维修图片

        Observable<BaseResult<Data<String>>> ReuturnAccessoryPicUpload(RequestBody json);

        Observable<BaseResult<Data<String>>> FinishOrderPicUpload(RequestBody json);
        //获取返件图片
       // Observable<BaseResult<String>> GetReturnAccessoryByOrderID(String OrderID);

        //修改订单状态
        Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID,String State,String Reason);

        //提交快递信息
        Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID,String ReturnAccessoryMsg,String PostMoney);
        Observable<BaseResult<Data<String>>> AddbarCode(String barCode,String OrderID);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);

        //上传服务图片
        void ServiceOrderPicUpload(BaseResult<Data<String>> baseResult);

        //上传维修图片

        void ReuturnAccessoryPicUpload(BaseResult<Data<String>> baseResult);

        void FinishOrderPicUpload(BaseResult<Data<String>> baseResult);

        //获取返件图片
        // void GetReturnAccessoryByOrderID(BaseResult<String> baseResult);

        //修改订单状态
        void UpdateOrderState(BaseResult<Data<String>> baseResult);

        //提交快递信息
        void AddReturnAccessory(BaseResult<Data<String>> baseResult);
        void AddbarCode(BaseResult<Data<String>> baseResult);

    }
   abstract class Presenter extends BasePresenter<View,Model> {
       //根据工单号获取工单详情
       public abstract void GetOrderInfo(String OrderID);

       //上传服务图片
       public abstract void ServiceOrderPicUpload(RequestBody json);
       //上传维修图片

       public abstract void ReuturnAccessoryPicUpload(RequestBody json);

       public abstract void FinishOrderPicUpload(RequestBody json);
       //获取返件图片
       //public abstract void GetReturnAccessoryByOrderID(String OrderID);
       public abstract void UpdateOrderState(String OrderID,String State,String Reason);

       //提交快递信息
       public abstract void AddReturnAccessory(String OrderID,String ReturnAccessoryMsg,String PostMoney);
       public abstract void AddbarCode(String barCode,String OrderID);
   }
}
