package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.AddressList;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.City;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;
import com.ying.administrator.masterappdemo.entity.Province;

import java.util.List;

import io.reactivex.Observable;


public interface AddressContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> AddAccountAddress(
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        Observable<BaseResult<Data<String>>> UpdateAccountAddress(
                String ID,
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        Observable<BaseResult<Data<String>>> DeleteAccountAddress(
                String ID
        );
        Observable<BaseResult<List<AddressList>>> GetAccountAddress(String UserId);
        Observable<BaseResult<List<Province>>> GetProvince();
        Observable<BaseResult<Data<List<City>>>> GetCity(String parentcode);
        Observable<BaseResult<Data<List<Area>>>> GetArea(String parentcode);
        Observable<BaseResult<Data<List<District>>>> GetDistrict(String parentcode);
    }

    interface View extends BaseView {
        void AddAccountAddress(BaseResult<Data<String>> baseResult);
        void UpdateAccountAddress(BaseResult<Data<String>> baseResult);
        void DeleteAccountAddress(BaseResult<Data<String>> baseResult);
        void GetAccountAddress(BaseResult<List<AddressList>> baseResult);
        void GetProvince(BaseResult<List<Province>> baseResult);
        void GetCity(BaseResult<Data<List<City>>> baseResult);
        void GetArea(BaseResult<Data<List<Area>>> baseResult);
        void GetDistrict(BaseResult<Data<List<District>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddAccountAddress(
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        public abstract void UpdateAccountAddress(
                String ID,
                String UserID,
                String Province,
                String City,
                String Area,
                String District,
                String Address,
                String Default,
                String UserName,
                String Phone
        );
        public abstract void DeleteAccountAddress(
                String ID
        );
        public abstract void GetAccountAddress(String UserId);
        public abstract void GetProvince();

        public abstract void GetCity(String parentcode);

        public abstract void GetArea(String parentcode);

        public abstract void GetDistrict(String parentcode);
    }
}
