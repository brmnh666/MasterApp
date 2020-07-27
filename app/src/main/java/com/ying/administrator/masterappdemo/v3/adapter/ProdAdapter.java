package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class ProdAdapter extends BaseQuickAdapter<WorkOrder.OrderProductModelsBean, BaseViewHolder> {


    public ProdAdapter(int layoutResId, @Nullable List<WorkOrder.OrderProductModelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.OrderProductModelsBean item) {
        String name=item.getSubCategoryName()+"   x"+item.getNum();
        helper.setText(R.id.tv_product_name,name);
    }
}
