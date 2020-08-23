package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.GetOrderMoneyDetailResult;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;

import java.util.List;

import io.reactivex.Observable;

public interface ServingDetailContract {
    interface Model extends BaseModel{
        //根据工单号获取工单详情
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        //提交快递信息
        Observable<BaseResult<Data<String>>> AddReturnAccessory(String OrderID, String ReturnAccessoryMsg, String PostMoney);
        //修改订单状态
        Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID,String State,String Reason);
        //更新时间
        Observable<BaseResult<Data>> UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
        Observable<BaseResult<Data<List<GetBrandWithCategory>>>> GetBrandWithCategory2(String UserID, String BrandID, String CategoryID, String SubCategoryID, String ProductTypeID, String page, String limit);
        Observable<AddOrderSignInRecrodResult> AddOrderSignInRecrod(String userId, String signInType, String orderId);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        Observable<ProductTollResult> ProductToll(String OrderID, String UserId);
        Observable<GetOrderMoneyDetailResult> GetOrderMoneyDetail(String OrderID);
    }

    interface View extends BaseView{
        //根据工单号获取工单详情
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);
        void AddReturnAccessory(BaseResult<Data<String>> baseResult);
        //修改订单状态
        void UpdateOrderState(BaseResult<Data<String>> baseResult);
        //更新时间
        void UpdateSendOrderUpdateTime(BaseResult<Data> baseResult);
        void GetBrandWithCategory2(BaseResult<Data<List<GetBrandWithCategory>>> baseResult);
        void AddOrderSignInRecrod(AddOrderSignInRecrodResult baseResult);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void ProductToll(ProductTollResult baseResult);
        void GetOrderMoneyDetail(GetOrderMoneyDetailResult baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        //根据工单号获取工单详情
        public abstract void GetOrderInfo(String OrderID);
        public abstract void AddReturnAccessory(String OrderID,String ReturnAccessoryMsg,String PostMoney);
        public abstract void UpdateOrderState(String OrderID,String State,String Reason);
        //更新时间
        public abstract void UpdateSendOrderUpdateTime(String OrderID,String UpdateDate,String EndDate);
        public abstract void AddOrderSignInRecrod(String userId, String signInType, String orderId);
        public abstract void GetBrandWithCategory2(String UserID, String BrandID, String CategoryID, String SubCategoryID, String ProductTypeID, String page, String limit);
        public abstract void GetUserInfoList(String UserID, String limit);
        public abstract void ProductToll(String OrderID, String UserId);
        public abstract void GetOrderMoneyDetail(String OrderID);
    }
}
