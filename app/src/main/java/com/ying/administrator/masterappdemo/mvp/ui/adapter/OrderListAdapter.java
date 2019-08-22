package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.OrderList;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderList, BaseViewHolder> {
    public OrderListAdapter(int layoutResId, @Nullable List<OrderList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderList item) {
        helper.addOnClickListener(R.id.tv_detail);
    }
}
