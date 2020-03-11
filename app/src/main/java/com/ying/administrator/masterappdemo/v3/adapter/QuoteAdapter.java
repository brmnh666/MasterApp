package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;

import java.util.List;

public class QuoteAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public QuoteAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_picture=helper.getView(R.id.iv_picture);
        Glide.with(mContext)
                .load(Config.Leave_quote_URL +item)
                .into(iv_picture);
    }
}
