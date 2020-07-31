package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.accessoryDataBean;

import java.util.List;

public class V4_AccessoriesAdapter extends BaseQuickAdapter<accessoryDataBean.AccessoryDetailModelsBean, BaseViewHolder> {
    public V4_AccessoriesAdapter(int layoutResId, @Nullable List<accessoryDataBean.AccessoryDetailModelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, accessoryDataBean.AccessoryDetailModelsBean item) {

        helper.setText(R.id.tv_name,item.getFAccessoryName())
                .setText(R.id.tv_number,"x"+item.getQuantity());
        helper.addOnClickListener(R.id.ll_delete);
    }
}
