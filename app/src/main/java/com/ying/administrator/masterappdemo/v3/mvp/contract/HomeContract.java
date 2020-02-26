package com.ying.administrator.masterappdemo.v3.mvp.contract;

import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import io.reactivex.Observable;

public interface HomeContract {
    interface Model extends BaseModel{
        Observable<BaseResult<WorkOrder>> WorkerGetOrderList(String UserID, String State, String page, String limit);
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID, String page, String limit);
    }

    interface View extends BaseView{
        void WorkerGetOrderList(BaseResult<WorkOrder> baseResult);
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void WorkerGetOrderList(String UserID, String State, String page,String limit);
        public abstract void GetListCategoryContentByCategoryID(String CategoryID,String page, String limit);
    }
}