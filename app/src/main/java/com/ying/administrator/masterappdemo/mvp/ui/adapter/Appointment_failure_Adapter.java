package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.view.Window;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class Appointment_failure_Adapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    public Appointment_failure_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {
//baseViewHolder.setText(R.id.tv_appointment_failure,item.getState());//预约不成功
        baseViewHolder.setText(R.id.tv_appointment_failure_status_install,item.getTypeName());//安装or维修
        if ("安装".equals(item.getTypeName())){
            baseViewHolder.setBackgroundColor(R.id.tv_appointment_failure_status_install, Color.parseColor("#1690FF"));
        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_appointment_failure_status_install,Color.parseColor("#FF0000"));
        }
        baseViewHolder.setText(R.id.tv_reason_appointment_failure,item.getMemo());//memo
        baseViewHolder.setText(R.id.tv_appointment_failure_job_number,"工单号："+item.getOrderID());//工单号
        baseViewHolder.setText(R.id.tv_loaction_appointment_failure,"距离"+item.getDistance()+"km");//距离
        baseViewHolder.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        baseViewHolder.setText(R.id.tv_address_appointment_failure,item.getAddress());//地址
//baseViewHolder.setText(R.id.tv_appointment_failure_reason,item.getMemo());//原因
        baseViewHolder.addOnClickListener(R.id.img_pending_appointment_failure_phone);
    }
}
