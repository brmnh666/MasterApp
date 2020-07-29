package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class V4_AccessoriesAdapter extends BaseQuickAdapter<WorkOrder.accessoryDataBean, BaseViewHolder> {
    public V4_AccessoriesAdapter(int layoutResId, @Nullable List<WorkOrder.accessoryDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.accessoryDataBean item) {
        String state="";
        if ("0".equals(item.getState())){
            state="审核中";
        }else if ("1".equals(item.getState())||"2".equals(item.getState())){
            state="通过";
        }if ("-1".equals(item.getState())){
            state="拒绝";
        }
        helper.setText(R.id.tv_name,item.getFAccessoryName()+"("+state+")")
                .setText(R.id.tv_number,"0".equals(item.getAccessoryState())?"x"+item.getQuantity()+"(厂家寄件)":"x"+item.getQuantity()+"    ¥"+item.getPrice()+"(师傅自购件)");
        helper.addOnClickListener(R.id.ll_delete);
    }
}
