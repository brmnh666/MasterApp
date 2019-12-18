package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.ComplaintList;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ComplaintContract;

import java.util.List;

import okhttp3.RequestBody;

public class ComplaintPresenter extends ComplaintContract.Presenter {
    @Override
    public void WorkerComplaint(String OrderID, String Content, String ComplaintType,String photo) {
        mModel.WorkerComplaint(OrderID, Content,ComplaintType,photo)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WorkerComplaint(value);
                    }
                });
    }

    @Override
    public void ComPlaintImg(RequestBody json) {
        mModel.ComPlaintImg(json)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ComPlaintImg(value);
                    }
                });
    }

    @Override
    public void GetComplaintListByOrderId(String OrderId,String UserID) {
        mModel.GetComplaintListByOrderId(OrderId,UserID)
                .subscribe(new BaseObserver<List<ComplaintList>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<ComplaintList>> value) {
                        mView.GetComplaintListByOrderId(value);
                    }
                });
    }
}
