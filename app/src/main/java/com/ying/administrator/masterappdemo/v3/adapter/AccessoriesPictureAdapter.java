package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;

import java.util.List;

public class AccessoriesPictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AccessoriesPictureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_picture=helper.getView(R.id.iv_picture);
        Glide.with(mContext)
                .load("https://img.xigyu.com/Pics/Accessory/" +  item)
                .into(iv_picture);
    }
}
