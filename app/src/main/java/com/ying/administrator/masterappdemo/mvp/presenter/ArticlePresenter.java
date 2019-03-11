package com.ying.administrator.masterappdemo.mvp.presenter;


import com.ying.administrator.masterappdemo.base.BaseObserver;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Article;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.ArticleContract;

import java.util.List;

public class ArticlePresenter extends ArticleContract.Presenter {

    @Override
    public void GetListCategoryContentByCategoryID(String CategoryID) {
        mModel.GetListCategoryContentByCategoryID(CategoryID)
                .subscribe(new BaseObserver<Article>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Article> value) {
                        mView.GetListCategoryContentByCategoryID(value);
                    }
                });
    }
}
