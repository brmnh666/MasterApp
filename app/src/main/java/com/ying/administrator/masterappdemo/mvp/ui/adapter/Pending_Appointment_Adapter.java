package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Pending_Appointment_Entity;

import java.util.List;

public class Pending_Appointment_Adapter extends BaseQuickAdapter<Pending_Appointment_Entity,BaseViewHolder> {
    public Pending_Appointment_Adapter(int layoutResId, @Nullable List<Pending_Appointment_Entity> data) {
        super(layoutResId, data);
        List<Pending_Appointment_Entity> list=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Pending_Appointment_Entity item) {
baseViewHolder.setText(R.id.tv_pending_appointment_job_number,item.getJob_number())
        .setText(R.id.tv_address_pending_appointment,item.getAddress());
        baseViewHolder.addOnClickListener(R.id.tv_pending_appointment_success);//预约成功点击事件
        baseViewHolder.addOnClickListener(R.id.tv_pending_appointment_failure);//未预约成功点击事件
        baseViewHolder.addOnClickListener(R.id.img_pending_appointment_phone);//预约按键

    }
}
