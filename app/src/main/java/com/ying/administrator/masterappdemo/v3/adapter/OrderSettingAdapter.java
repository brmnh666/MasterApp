package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.v3.bean.OrderSettingBean;

import java.util.List;

public class OrderSettingAdapter extends BaseQuickAdapter<OrderSettingBean, BaseViewHolder> {
    public OrderSettingAdapter(int layoutResId, @Nullable List<OrderSettingBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSettingBean item) {

    }
}
