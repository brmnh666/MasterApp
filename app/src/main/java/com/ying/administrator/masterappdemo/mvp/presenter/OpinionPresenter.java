package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.OpinionContract;

public class OpinionPresenter extends OpinionContract.Presenter {

    @Override
    public void AddOpinion(String UserID, String BackType, String Content) {
        mModel.AddOpinion(UserID, BackType, Content)
                .subscribe(new BaseObserver<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOpinion(value);
                    }
                });
    }
}
