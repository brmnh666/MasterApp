package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;

import io.reactivex.Observable;


public interface RegisterContract {
    interface Model extends BaseModel{
        Observable<BaseResult<String>> Reg(String userName, String code);
        Observable<BaseResult<String>> GetCode(String userName);
        Observable<BaseResult<String>> Login(String userName,String password);
        Observable<BaseResult<String>> ValidateUserName(String userName);
    }

    interface View extends BaseView{
        void Reg(BaseResult<String> baseResult);
        void GetCode(BaseResult<String> baseResult);
        void Login(BaseResult<String> baseResult);
        void ValidateUserName(BaseResult<String> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void Reg(String userName,String code);
        public abstract void GetCode(String userName);
        public abstract void Login(String userName,String password);
        public abstract void ValidateUserName(String userName);
    }
}
