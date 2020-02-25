package com.ying.administrator.masterappdemo.v3.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

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
        TextView tv_type=helper.getView(R.id.tv_type);
        TextView tv_orders=helper.getView(R.id.tv_orders);
        helper.setText(R.id.tv_time, MyUtils.getTimebefore(item.getCreateDate()))
                .setText(R.id.tv_name,item.getProductType())
                .setText(R.id.tv_location,"距离:"+item.getDistance()+"Km")
                .setText(R.id.tv_malfunction,"故障:"+item.getMemo())
                .setText(R.id.tv_address,"地址:"+item.getAddress());




        if ("1".equals(item.getPartyNo())){
            tv_orders.setText("报价");
            tv_orders.setBackgroundResource(R.drawable.v3_yellow_shape);
            helper.setText(R.id.tv_type,"用户发单/"+item.getTypeName());
            helper.setBackgroundRes(R.id.tv_type,R.drawable.v3_yellow_shape);
            helper.setTextColor(R.id.tv_type, Color.parseColor("#000000"));
        }else {
            tv_orders.setText("接单");
            tv_orders.setBackgroundResource(R.drawable.v3_orders_shape);
            if ("Y".equals(item.getExtra())&&!"0".equals(item.getExtraTime())){
                helper.setText(R.id.tv_type,item.getGuaranteeText()+"/"+item.getTypeName()+"/加急");
            }else {
                helper.setText(R.id.tv_type,item.getGuaranteeText()+"/"+item.getTypeName());
            }
            helper.setBackgroundRes(R.id.tv_type,R.drawable.v3_home_bg);
            helper.setTextColor(R.id.tv_type, Color.parseColor("#01B1D2"));
        }
    }
}
