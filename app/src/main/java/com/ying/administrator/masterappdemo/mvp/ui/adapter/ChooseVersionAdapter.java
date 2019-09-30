package com.ying.administrator.masterappdemo.mvp.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.ShopVersion;

import java.util.List;

public class ChooseVersionAdapter extends BaseQuickAdapter<ShopVersion, BaseViewHolder> {
    public ChooseVersionAdapter(int layoutResId, @Nullable List<ShopVersion> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopVersion item) {
        helper.setText(R.id.tv_version,item.getValue());
        helper.addOnClickListener(R.id.rl_choose);
    }
}
