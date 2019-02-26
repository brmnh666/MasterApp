package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;


/*个人信息管理的接口请求页面*/
public interface InfoManageContract {
    interface Model extends BaseModel {

        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        /*个人头像修改*/
        Observable<BaseResult<Data<String>>> UploadAvator(RequestBody json);

        /*获取身份证图片*/
        Observable<BaseResult<List<IDCard.IDCardBean>>> GetIDCardImg(String UserID);

        /*修改昵称*/
        Observable<BaseResult<Data>> UpdateAccountNickName(String UserID,String NickName);
         /*修改密码*/
        Observable<BaseResult<Data>> UpdatePassword(String UserID,String Password);

        /*修改性别*/
        Observable<BaseResult<Data>> UpdateSex(String UserID,String Sex);


    }

    interface View extends BaseView {

        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        /*个人头像修改*/
        void UploadAvator(BaseResult<Data<String>> baseResult);
        /*获取身份证图片*/
        void GetIDCardImg(BaseResult<List<IDCard.IDCardBean>> baseResult);

        /*修改昵称*/
        void UpdateAccountNickName(BaseResult<Data> baseResult);

        /*修改密码*/
        void UpdatePassword(BaseResult<Data> baseResult);

        /*修改性别*/
        void UpdateSex(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void GetUserInfoList(String UserID,String limit);
        /*个人头像修改*/
        public abstract void UploadAvator(RequestBody json);
        /*获取身份证图片*/
        public abstract void GetIDCardImg(String UserID);

        /*修改昵称*/
        public abstract void UpdateAccountNickName(String UserID,String NickName);

        /*修改密码*/
        public abstract void UpdatePassword(String UserID,String Password);

        /*修改性别*/
        public abstract void UpdateSex(String UserID,String Sex);
    }
}
