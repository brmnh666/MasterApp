package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.util.MyUtils;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<WorkOrder.DataBean, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        helper.addOnClickListener(R.id.tv_orders);
        helper.setText(R.id.tv_time, MyUtils.getTimebefore(item.getCreateDate()))
                .setText(R.id.tv_name,item.getProductType())
                .setText(R.id.tv_location,"距离:"+item.getDistance()+"Km")
                .setText(R.id.tv_malfunction,"故障:"+item.getMemo())
                .setText(R.id.tv_address,"地址:"+item.getAddress());


        if ("Y".equals(item.getExtra())&&!"0".equals(item.getExtraTime())){
            helper.setText(R.id.tv_type,item.getTypeName()+"/"+item.getGuaranteeText()+"/加急");
        }else {
            helper.setText(R.id.tv_type,item.getTypeName()+"/"+item.getGuaranteeText());
        }
    }
}
