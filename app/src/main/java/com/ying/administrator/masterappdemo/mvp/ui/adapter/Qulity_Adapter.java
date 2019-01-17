package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;

import java.util.List;

public class Qulity_Adapter extends BaseQuickAdapter<GrabSheet_Entity, BaseViewHolder> {
    public Qulity_Adapter(int layoutResId, @Nullable List<GrabSheet_Entity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GrabSheet_Entity item) {
helper.setText(R.id.tv_address_quality,item.getAddress());
    }
}
