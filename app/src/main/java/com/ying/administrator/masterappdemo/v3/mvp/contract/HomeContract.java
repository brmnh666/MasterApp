package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.CodeMoney;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel{
        Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit);
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
        //接单操作
        Observable<BaseResult<Data>> UpdateSendOrderState(String OrderID,String State,String Reason);
        Observable<BaseResult<Data<String>>> messgIsOrNo(String UserID, String limit, String page);
        Observable<BaseResult<List<CodeMoney>>> GetCodeList(String Code, String limit, String page);
    }

    interface View extends BaseView{
        void WorkerGetOrderList(BaseResult<WorkOrder> baseResult);
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
        //接单操作
        void UpdateSendOrderState(BaseResult<Data> baseResult);
        void messgIsOrNo(BaseResult<Data<String>> baseResult);
        void GetCodeList(BaseResult<List<CodeMoney>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void WorkerGetOrderList(String UserID, String State, String page,String limit);
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
        //接单操作
        public abstract void UpdateSendOrderState(String OrderID,String State,String Reason);
        public abstract void messgIsOrNo(String UserID, String limit, String page);
        public abstract void GetCodeList(String Code, String limit, String page);
    }
}
