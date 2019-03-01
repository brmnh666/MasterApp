package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class In_Service_Adapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    public In_Service_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        if (item.getTypeID()==1){//维修
            helper.setVisible(R.id.tv_in_service_status_repair,true);
            helper.setVisible(R.id.tv_in_service_status_install,false);

        }else {
            helper.setVisible(R.id.tv_in_service_status_repair,false);
            helper.setVisible(R.id.tv_in_service_status_install,true);
        }
        helper.setText(R.id.tv_loaction_in_service,"距离 "+item.getDistance()+"Km");
        helper.setText(R.id.tv_reason_in_service,item.getMemo());//原因
        helper.setText(R.id.tv_address_in_service,item.getAddress()); //地址
        helper.setText(R.id.tv_in_service_job_number,"工单号:"+item.getOrderID());


      helper.addOnClickListener(R.id.tv_in_service_finish);//完成工单
      helper.addOnClickListener(R.id.tv_in_service_apply_parts);//申请配件
    }




}
