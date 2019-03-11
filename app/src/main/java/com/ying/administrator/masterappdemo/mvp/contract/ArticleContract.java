package com.ying.administrator.masterappdemo.mvp.contract;


import com.ying.administrator.masterappdemo.base.BaseModel;
import com.ying.administrator.masterappdemo.base.BasePresenter;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.base.BaseView;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.District;

import java.util.List;

import io.reactivex.Observable;


public interface ArticleContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(String CategoryID);
    }

    interface View extends BaseView {
        void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetListCategoryContentByCategoryID(String CategoryID);
    }
}
