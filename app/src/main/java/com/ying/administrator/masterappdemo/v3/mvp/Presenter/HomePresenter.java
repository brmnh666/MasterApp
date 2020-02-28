package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.contract.HomeContract;

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

    @Override
    public void GetListCategoryContentByCategoryID(String CategoryID, String page, String limit) {
        mModel.GetListCategoryContentByCategoryID(CategoryID, page, limit)
                .subscribe(new BaseObserver<Article>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Article> value) {
                        mView.GetListCategoryContentByCategoryID(value);
                    }
                });
    }

    @Override
    public void UpdateSendOrderState(String OrderID, String State, String Reason) {
        mModel.UpdateSendOrderState(OrderID, State, Reason)
                .subscribe(new BaseObserver<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSendOrderState(value);
                    }
                });
    }
}
