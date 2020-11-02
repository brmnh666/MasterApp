package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;

import io.reactivex.Observable;


public interface LoginContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> Login(String userName, String passWord);
        Observable<BaseResult<String>> GetUserInfo(String userName);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);
        Observable<BaseResult<String>> ValidateUserName(String userName);
        Observable<BaseResult<Data<String>>> GetCode(String userName,String type);
        Observable<BaseResult<Data<String>>> LoginOnMessage(String mobile,String code);
        Observable<BaseResult<Data<String>>> LoginOut(String UserID);
        Observable<BaseResult<Data<WxRegister>>> WxRegister(String openid,
                                                            String nickname,
                                                            String sex,
                                                            String language,
                                                            String city,
                                                            String province,
                                                            String country,
                                                            String headimgurl,
                                                            String unionid);
    }

    interface View extends BaseView {
        void Login(BaseResult<Data<String>> baseResult);
        void GetUserInfo(BaseResult<String> baseResult);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult);
        void GetCode(BaseResult<Data<String>> baseResult);
        void ValidateUserName(BaseResult<String> baseResult);
        void LoginOnMessage(BaseResult<Data<String>> baseResult);
        void LoginOut(BaseResult<Data<String>> baseResult);
        void WxRegister(BaseResult<Data<WxRegister>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void Login(String userName, String passWord);
        public abstract void GetUserInfo(String userName);
        public abstract void GetCode(String userName,String type);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
        public abstract void ValidateUserName(String userName);
        public abstract void LoginOnMessage(String mobile,String code);
        public abstract void LoginOut(String UserID);
        public abstract void WxRegister(String openid,
                                        String nickname,
                                        String sex,
                                        String language,
                                        String city,
                                        String province,
                                        String country,
                                        String headimgurl,
                                        String unionid);
    }
}
