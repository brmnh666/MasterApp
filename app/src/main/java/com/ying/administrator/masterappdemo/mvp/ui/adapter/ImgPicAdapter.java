package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.viewholder.LayoutParamsViewHolder;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.List;

public class ImgPicAdapter extends BaseQuickAdapter<String, LayoutParamsViewHolder> {
    public ImgPicAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, String item) {
        GlideUtil.loadImageViewLoding(mContext, Config.Leave_Message_URL +item, (ImageView) helper.getView(R.id.img), R.drawable.image_loading,R.drawable.image_loading);
    }
}
