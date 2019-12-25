package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.util.DensityUtil;
import com.ying.administrator.masterappdemo.viewholder.LayoutParamsViewHolder;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.List;

public class PicAdapter extends BaseQuickAdapter<String, LayoutParamsViewHolder> {
    public PicAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, String item) {

        GlideUtil.loadImageViewLoding(mContext, item, (ImageView) helper.getView(R.id.img), R.drawable.add_picture,R.drawable.add_picture);
        helper.addOnClickListener(R.id.img);

    }
}
