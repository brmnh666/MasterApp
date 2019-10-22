package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.AccessoriesName;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.List;

public class AccessoriesPictureAdapter extends BaseQuickAdapter<AccessoriesName, BaseViewHolder> {
    public AccessoriesPictureAdapter(int layoutResId, @Nullable List<AccessoriesName> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccessoriesName item) {
        helper.setText(R.id.tv_accessories_name,item.getName());
        helper.addOnClickListener(R.id.iv_host);
        if (item.getPic()!=null){
            Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.iv_host));
        }
    }
}
