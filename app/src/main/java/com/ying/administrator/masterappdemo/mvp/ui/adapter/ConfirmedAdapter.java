package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class ConfirmedAdapter extends BaseQuickAdapter<WorkOrder.DataBean, BaseViewHolder> {
    public ConfirmedAdapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        helper.setText(R.id.tv_complete,item.getStateStr());//已完成
        helper.setText(R.id.tv_complete_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());//安装or维修
        if ("安装".equals(item.getTypeName())){
            helper.setBackgroundColor(R.id.tv_complete_status_repair, Color.parseColor("#1690FF"));
        }else{
            helper.setBackgroundColor(R.id.tv_complete_status_repair,Color.parseColor("#FF0000"));
        }
        helper.setText(R.id.tv_complete_job_number,"工单号:"+item.getOrderID());//工单号
        helper.setText(R.id.tv_reason_complete,item.getMemo());//memo
        helper.setText(R.id.tv_loaction_complete,"距离："+item.getDistance()+"km");//距离
        helper.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        helper.setText(R.id.tv_address_complete,item.getAddress());//地址
        helper.addOnClickListener(R.id.tv_detail);
        helper.addOnClickListener(R.id.tv_cancel_order);

    }
}