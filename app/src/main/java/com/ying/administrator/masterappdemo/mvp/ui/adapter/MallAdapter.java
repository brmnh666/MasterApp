package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.ProductList;

import java.util.List;

public class MallAdapter extends BaseQuickAdapter<ProductList, BaseViewHolder> {
    public MallAdapter(int layoutResId, @Nullable List<ProductList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductList item) {
        helper.addOnClickListener(R.id.ll_goods);
    }
}
