package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.viewholder.LayoutParamsViewHolder;
import com.ying.administrator.masterappdemo.widget.GlideUtil;
import com.ying.administrator.masterappdemo.widget.MyImageView;

import java.util.List;

public class PicAdapter extends BaseQuickAdapter<String, LayoutParamsViewHolder> {
    public PicAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, String item) {

        GlideUtil.loadImageViewLoding(mContext, item, (MyImageView) helper.getView(R.id.img), R.mipmap.upload,R.mipmap.upload);
        helper.addOnClickListener(R.id.img);

    }
}
