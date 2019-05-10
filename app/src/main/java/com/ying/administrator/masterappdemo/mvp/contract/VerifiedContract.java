package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface VerifiedContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> IDCardUpload(RequestBody json,int code);

        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID,String limit);

        Observable<BaseResult<Data<String>>> ApplyAuthInfo(String UserID,
                                                                String TrueName,
                                                                String Sex,
                                                                String IDCard,
                                                                String Address,
                                                           String NodeIds,
                                                            String Province,
                                                            String City,
                                                            String Area,
                                                          String District,
                                                           String Longitude,
                                                           String Dimension,
                                                           String ServiceAreaJsonStr,
                                                           String ISwoker
        );

        Observable<BaseResult<Data<String>>> ApplyAuthInfoBysub(String UserID,
                                                           String TrueName,
                                                           String Sex,
                                                           String IDCard,
                                                           String Address,
                                                           String Province,
                                                           String City,
                                                           String Area,
                                                           String District,
                                                           String Longitude,
                                                           String Dimension,
                                                           String ISwoker);





    }

    interface View extends BaseView {
        void IDCardUpload(BaseResult<Data<String>> baseResult,int code);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void ApplyAuthInfo(BaseResult<Data<String>> baseResult);
        void ApplyAuthInfoBysub(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void IDCardUpload(RequestBody json,int code);
        public abstract void GetUserInfoList(String UserID,String limit);
        public abstract void ApplyAuthInfo(String UserID,
                                           String TrueName,
                                           String Sex,
                                           String IDCard,
                                           String Address,
                                           String NodeIds,
                                           String Province,
                                           String City,
                                           String Area,
                                           String District,
                                           String Longitude,
                                           String Dimension,
                                           String ServiceAreaJsonStr,
                                           String ISwoker);


        public abstract void ApplyAuthInfoBysub(String UserID,
                                           String TrueName,
                                           String Sex,
                                           String IDCard,
                                           String Address,
                                           String Province,
                                           String City,
                                           String Area,
                                           String District,
                                           String Longitude,
                                           String Dimension,
                                           String ISwoker
                                           );
    }


}
