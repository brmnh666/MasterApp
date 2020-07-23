package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;

import java.util.List;

public class AccessoriesPictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AccessoriesPictureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_picture);
        ImageView iv_picture=helper.getView(R.id.iv_picture);
        Glide.with(mContext)
                .load(item)
                .into(iv_picture);
    }
}
