package com.ying.administrator.masterappdemo.v3.MVC.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.MVC.contract.HomeContract;

public class HomePresenter extends HomeContract.Presenter {
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
