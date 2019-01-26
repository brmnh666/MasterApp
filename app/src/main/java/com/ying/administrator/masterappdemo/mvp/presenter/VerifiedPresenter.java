package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.mvp.contract.VerifiedContract;

import okhttp3.RequestBody;

public class VerifiedPresenter extends VerifiedContract.Presenter {


    @Override
    public void UploadImg(RequestBody json) {
        mModel.UploadImg(json)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<String> value) {
                        mView.UploadImg(value);
                    }
                });
    }
}
