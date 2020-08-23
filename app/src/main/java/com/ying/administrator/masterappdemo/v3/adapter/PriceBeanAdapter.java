package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.v3.bean.GetOrderMoneyDetailResult;

import java.util.List;

public class PriceBeanAdapter extends BaseQuickAdapter<GetOrderMoneyDetailResult.DataBeanX.DataBean.Item1Bean, BaseViewHolder> {
    public PriceBeanAdapter(int layoutResId, @Nullable List<GetOrderMoneyDetailResult.DataBeanX.DataBean.Item1Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrderMoneyDetailResult.DataBeanX.DataBean.Item1Bean item) {
        helper.setText(R.id.tv_name,item.getTypeName()+"("+item.getDescribes()+")");
        helper.setText(R.id.tv_price,"¥"+item.getMasterPrice());
    }
}
