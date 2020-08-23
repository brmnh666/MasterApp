package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;

import java.util.List;

public class ProductTollBeanAdapter extends BaseQuickAdapter<ProductTollResult.DataBeanX.DataBean.OrderProductTollBean, BaseViewHolder> {
    public ProductTollBeanAdapter(int layoutResId, @Nullable List<ProductTollResult.DataBeanX.DataBean.OrderProductTollBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductTollResult.DataBeanX.DataBean.OrderProductTollBean item) {
        helper.setText(R.id.tv_name,item.getTypeName());
        helper.setText(R.id.tv_price,"¥"+item.getStandardPrice());
    }
}
