package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GAccessory;

import java.util.List;

public class AccessoriesAdapter extends BaseQuickAdapter<GAccessory, BaseViewHolder> {
    public AccessoriesAdapter(int layoutResId, @Nullable List<GAccessory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        helper.setText(R.id.tv_name,item.getFAccessoryName())
                .setText(R.id.tv_number,item.getQuantity()+"");
    }
}
