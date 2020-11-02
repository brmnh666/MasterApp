package com.ying.administrator.masterappdemo.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.WithDrawMoney;

import java.util.List;

import io.reactivex.Observable;


public interface WithDrawContract {
    interface Model extends BaseModel{
        Observable<BaseResult<WithDrawMoney>> GetDepositMoneyDisplay(String UserId);
        Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId);
        Observable<BaseResult<Data<String>>> WithDraw(String DrawMoney,String CardNo,String UserID,String CardName);
        /*获取身份证图片*/
        Observable<BaseResult<List<IDCard.IDCardBean>>> GetIDCardImg(String UserID);

    }

    interface View extends BaseView{
        void GetDepositMoneyDisplay(BaseResult<WithDrawMoney> baseResult);
        void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult);
        void WithDraw(BaseResult<Data<String>> baseResult);
        /*获取身份证图片*/
        void GetIDCardImg(BaseResult<List<IDCard.IDCardBean>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetDepositMoneyDisplay(String UserId);
        public abstract void GetAccountPayInfoList(String UserId);
        public abstract void WithDraw(String DrawMoney,String CardNo,String UserID,String CardName);
        /*获取身份证图片*/
        public abstract void GetIDCardImg(String UserID);
    }
}
