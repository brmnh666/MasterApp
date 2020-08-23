package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseObserver2;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.AddOrderSignInRecrodResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.bean.GetOrderMoneyDetailResult;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;
import com.ying.administrator.masterappdemo.v3.mvp.contract.ServingDetailContract;

import java.util.List;

public class ServingDetailPresenter extends ServingDetailContract.Presenter {
    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void AddReturnAccessory(String OrderID, String ReturnAccessoryMsg, String PostMoney) {
        mModel.AddReturnAccessory(OrderID, ReturnAccessoryMsg, PostMoney)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddReturnAccessory(value);
                    }
                });
    }

    @Override
    public void UpdateOrderState(String OrderID, String State,String Reason) {
        mModel.UpdateOrderState(OrderID,State,Reason).subscribe(new BaseObserver<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.UpdateOrderState(value);
            }
        });
    }

    @Override
    public void UpdateSendOrderUpdateTime(String OrderID, String UpdateDate, String EndDate) {
        mModel.UpdateSendOrderUpdateTime(OrderID, UpdateDate, EndDate)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSendOrderUpdateTime(value);
                    }
                });
    }



    @Override
    public void AddOrderSignInRecrod(String userId, String signInType, String orderId) {
        mModel.AddOrderSignInRecrod(userId, signInType, orderId)
                .subscribe(new BaseObserver2<AddOrderSignInRecrodResult>() {
                    @Override
                    protected void onHandleSuccess(AddOrderSignInRecrodResult value) {
                        mView.AddOrderSignInRecrod(value);
                    }
                });
    }

    @Override
    public void GetBrandWithCategory2(String UserID, String BrandID, String CategoryID, String SubCategoryID, String ProductTypeID, String page, String limit) {
        mModel.GetBrandWithCategory2(UserID, BrandID, CategoryID, SubCategoryID, ProductTypeID,page,limit)
                .subscribe(new BaseObserver<Data<List<GetBrandWithCategory>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<GetBrandWithCategory>>> value) {
                        mView.GetBrandWithCategory2(value);
                    }
                });
    }
    @Override
    public void GetUserInfoList(String UserID, String limit) {
        mModel.GetUserInfoList(UserID, limit)
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void ProductToll(String OrderID, String UserId) {
        mModel.ProductToll(OrderID, UserId)
                .subscribe(new BaseObserver2<ProductTollResult>() {
                    @Override
                    protected void onHandleSuccess(ProductTollResult value) {
                        mView.ProductToll(value);
                    }
                });
    }

    @Override
    public void GetOrderMoneyDetail(String OrderID) {
        mModel.GetOrderMoneyDetail(OrderID)
                .subscribe(new BaseObserver2<GetOrderMoneyDetailResult>() {
                    @Override
                    protected void onHandleSuccess(GetOrderMoneyDetailResult value) {
                        mView.GetOrderMoneyDetail(value);
                    }
                });
    }
}
