package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumber;
import com.ying.administrator.masterappdemo.entity.NavigationBarNumberSon;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.contract.OrderContract;

public class OrderPresenter extends OrderContract.Presenter {
    @Override
    public void NavigationBarNumber(String UserID, String page, String limit) {
        mModel.NavigationBarNumber(UserID, page, limit)
                .subscribe(new BaseObserver<Data<NavigationBarNumber>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<NavigationBarNumber>> value) {
                        mView.NavigationBarNumber(value);
                    }
                });
    }

    @Override
    public void NavigationBarNumberSon(String UserID, String page, String limit) {
        mModel.NavigationBarNumberSon(UserID, page, limit)
                .subscribe(new BaseObserver<Data<NavigationBarNumberSon>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<NavigationBarNumberSon>> value) {
                        mView.NavigationBarNumberSon(value);
                    }
                });
    }

    @Override
    public void WorkerGetOrderList(String UserID, String State, String page, String limit) {
        mModel.WorkerGetOrderList(UserID, State, page, limit)
                .subscribe(new BaseObserver<WorkOrder>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                        mView.WorkerGetOrderList(value);
                    }
                });
    }


}
