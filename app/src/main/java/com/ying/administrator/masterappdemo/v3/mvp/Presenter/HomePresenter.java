package com.ying.administrator.masterappdemo.v3.mvp.Presenter;

import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.CodeMoney;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.v3.mvp.contract.HomeContract;

import java.util.List;

public class HomePresenter extends HomeContract.Presenter {
    @Override
    public void WorkerGetOrderList(String UserID, String State, String page, String limit,int page2) {
        mModel.WorkerGetOrderList(UserID, State, page, limit,page2)
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

    @Override
    public void messgIsOrNo(String UserID, String limit, String page) {
        mModel.messgIsOrNo(UserID, limit, page)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.messgIsOrNo(value);
                    }
                });
    }

    @Override
    public void GetCodeList(String Code, String limit, String page) {
        mModel.GetCodeList(Code, limit, page)
                .subscribe(new BaseObserver<List<CodeMoney>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<CodeMoney>> value) {
                        mView.GetCodeList(value);
                    }
                });
    }
}
