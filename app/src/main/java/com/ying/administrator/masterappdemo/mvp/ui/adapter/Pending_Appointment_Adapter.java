package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Pending_Appointment_Entity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class Pending_Appointment_Adapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    public Pending_Appointment_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        helper.setText(R.id.tv_reason_pending_appointment,item.getMemo());//原因
     helper.setText(R.id.tv_address_pending_appointment,item.getAddress()); //地址
        helper.setText(R.id.tv_pending_appointment_job_number,"工单号:"+item.getOrderID());
        if (item.getTypeID().equals("1")){//维修
          helper.setVisible(R.id.tv_pending_appointment_status_repair,true);
          helper.setVisible(R.id.tv_pending_appointment_status_install,false);

        }else {
            helper.setVisible(R.id.tv_pending_appointment_status_repair,false);
            helper.setVisible(R.id.tv_pending_appointment_status_install,true);

        }

        helper.addOnClickListener(R.id.img_pending_appointment_phone);//拨打电话事件
        helper.addOnClickListener(R.id.tv_pending_appointment_success); //预约成功
        helper.addOnClickListener(R.id.tv_pending_appointment_failure);//预约不成功
    }


}
