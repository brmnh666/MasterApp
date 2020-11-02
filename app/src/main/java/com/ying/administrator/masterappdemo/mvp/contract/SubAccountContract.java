package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface SubAccountContract {
    interface Model extends BaseModel {
        /*获取账号信息列表*/
        Observable <BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);

        //获取子账号
        Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>> GetChildAccountByParentUserID(String ParentUserID);


        //注销子账号
        Observable<BaseResult<Data<String>>> CancelChildAccount(String UserID,String ParentUserID);
    }

    interface View extends BaseView {
        /*获取账号信息列表*/
        void GetUserInfoList(BaseResult<UserInfo> baseResult);

        /*获取子账号*/
        void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult);
        //注销子账号
        void CancelChildAccount(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<SubAccountContract.View,SubAccountContract.Model> {
        /*获取账号信息列表*/
        public abstract void GetUserInfoList(String UserID, String limit);

        /*获取子账号*/
        public abstract void GetChildAccountByParentUserID(String ParentUserID);

        //注销子账号
        public abstract void CancelChildAccount(String UserID,String ParentUserID);
    }

}
