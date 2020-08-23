package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.v3.bean.ProductTollResult;

import java.util.List;

public class ProductTollAdapter extends BaseQuickAdapter<ProductTollResult.DataBeanX.DataBean, BaseViewHolder> {
    public ProductTollAdapter(int layoutResId, @Nullable List<ProductTollResult.DataBeanX.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductTollResult.DataBeanX.DataBean item) {
        helper.setText(R.id.tv_name,item.getProductType());
        RecyclerView rv_list=helper.getView(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        ProductTollBeanAdapter adapter=new ProductTollBeanAdapter(R.layout.price_type_item,item.getOrderProductToll());
        rv_list.setAdapter(adapter);
    }
}
