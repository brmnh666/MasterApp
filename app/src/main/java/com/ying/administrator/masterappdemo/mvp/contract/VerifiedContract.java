package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface VerifiedContract {
    interface Model extends BaseModel {
        Observable<BaseResult<String>> UploadImg(RequestBody json);
    }

    interface View extends BaseView {
        void UploadImg(BaseResult<String> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void UploadImg(RequestBody json);
    }
}
