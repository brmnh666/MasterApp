package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.accessoryDataBean;

import java.util.List;

public class V4_AccessoriesAdapter extends BaseQuickAdapter<accessoryDataBean.AccessoryDetailModelsBean, BaseViewHolder> {
    private String accState="0";
    public V4_AccessoriesAdapter(int layoutResId, @Nullable List<accessoryDataBean.AccessoryDetailModelsBean> data,String AccState) {
        super(layoutResId, data);
        this.accState=AccState;
    }

    @Override
    protected void convert(BaseViewHolder helper, accessoryDataBean.AccessoryDetailModelsBean item) {

        helper.setText(R.id.tv_name,item.getFAccessoryName())
                .setText(R.id.tv_number,"0".equals(accState)?"x"+item.getQuantity():"x"+item.getQuantity()+"（¥"+item.getPrice()+"）");
        helper.addOnClickListener(R.id.ll_delete);
        helper.addOnClickListener(R.id.btn_edit);
    }
}
