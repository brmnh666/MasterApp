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
        if ("Y".equals(item.getExtra())&&!"0".equals(item.getExtraTime())){
            helper.setText(R.id.tv_complete_status_repair,item.getTypeName()+"/"+item.getGuaranteeText()+"/加急");
        }else {
            helper.setText(R.id.tv_complete_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());
        }
//        helper.setText(R.id.tv_complete_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());//安装or维修
        if ("安装".equals(item.getTypeName())){
            helper.setBackgroundColor(R.id.tv_complete_status_repair, Color.parseColor("#1690FF"));
            helper.setText(R.id.tv_malfunction, "安装备注:"+item.getMemo());//原因

        }else{
            helper.setBackgroundColor(R.id.tv_complete_status_repair,Color.parseColor("#FF0000"));
            helper.setText(R.id.tv_malfunction,"故障:"+item.getMemo());//memo

        }
        helper.setText(R.id.tv_complete_job_number,"工单号:"+item.getOrderID());//工单号
        helper.setText(R.id.tv_reason_complete,item.getBrandName() + " " + item.getSubCategoryName()+" "+item.getProductType());//memo
        helper.setText(R.id.tv_loaction_complete,"距离："+item.getDistance()+"km");//距离
        helper.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        helper.setText(R.id.tv_address_complete,"地址:"+item.getAddress());//地址
        helper.addOnClickListener(R.id.tv_detail);
        helper.addOnClickListener(R.id.tv_cancel_order);
        if ("1".equals(item.getIsLook())){
            helper.setTextColor(R.id.tv_reason_complete,Color.BLACK);
        }else{
            helper.setTextColor(R.id.tv_reason_complete,Color.GRAY);
        }

    }
}
