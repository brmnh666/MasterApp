package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GService;

import java.util.List;

public class ServicesAdapter extends BaseQuickAdapter<GService, BaseViewHolder> {
    public ServicesAdapter(int layoutResId, @Nullable List<GService> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GService item) {
        helper.setText(R.id.tv_name,item.getServiceName());
        helper.setGone(R.id.tv_number,false);
    }
}
