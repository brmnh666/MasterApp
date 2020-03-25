package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.GetBrandWithCategory;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
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
    public void ConfirmReceipt(String OrderID) {
        mModel.ConfirmReceipt(OrderID)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ConfirmReceipt(value);
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
}
