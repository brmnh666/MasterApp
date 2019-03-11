package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Article;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<Article.DataBean,BaseViewHolder> {
    public ArticleAdapter(int layoutResId, List<Article.DataBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Article.DataBean item) {
        helper.setText(R.id.iv_message_name,item.getTitle());
    }
}
