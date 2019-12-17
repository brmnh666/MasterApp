package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WxRegister;

import io.reactivex.Observable;


public interface RegisterContract {
    interface Model extends BaseModel{
        Observable<BaseResult<Data<String>>> Reg(String userName, String code,String password);
        Observable<BaseResult<Data<String>>> GetCode(String userName,String type);
        Observable<BaseResult<Data<String>>> Login(String userName,String password);
        Observable<BaseResult<String>> ValidateUserName(String userName);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);
        Observable<BaseResult<Data<String>>> WxReg(String userName, String code,String openid);
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

    interface View extends BaseView{
        void Reg(BaseResult<Data<String>> baseResult);
        void GetCode(BaseResult<Data<String>> baseResult);
        void Login(BaseResult<Data<String>> baseResult);
        void ValidateUserName(BaseResult<String> baseResult);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult);
        void WxReg(BaseResult<Data<String>> baseResult);
        void WxRegister(BaseResult<Data<WxRegister>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void Reg(String userName,String code,String password);
        public abstract void GetCode(String userName,String type);
        public abstract void Login(String userName,String password);
        public abstract void ValidateUserName(String userName);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
        public abstract void WxReg(String userName, String code,String openid);
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
