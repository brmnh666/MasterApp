package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface VerifiedContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> UploadImg(RequestBody json,int code);
        Observable<BaseResult<Data<String>>> UpdateAccountModel(String UserID,
                                                                String TrueName,
                                                                String IDCard,
                                                                String Address,
                                                                String Skills);
        Observable<BaseResult<Data<String>>> ApplyAuth(String UserID);
    }

    interface View extends BaseView {
        void UploadImg(BaseResult<Data<String>> baseResult,int code);
        void UpdateAccountModel(BaseResult<Data<String>> baseResult);
        void ApplyAuth(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void UploadImg(RequestBody json,int code);
        public abstract void UpdateAccountModel(String UserID,
                                                String TrueName,
                                                String IDCard,
                                                String Address,
                                                String Skills);
        public abstract void ApplyAuth(String UserID);
    }
}
