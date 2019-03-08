package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class Return_Sheet_Adapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {

    private TextView tv_returnedparts_apply_parts;
    private TextView tv_continue_service;
    private TextView tv_reminder;
    private CountdownView countdownview;
    private LinearLayout ll_pending_appointment_cancel;
    private TextView tv_hint_returnedparts;
    private long now;
    private long cancel;
    private long leftTime;

    public Return_Sheet_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {

        baseViewHolder.setText(R.id.tv_returnedparts_status_install,item.getTypeName());//安装or维修
        if ("安装".equals(item.getTypeName())){
            baseViewHolder.setBackgroundColor(R.id.tv_returnedparts_status_install, Color.parseColor("#1690FF"));
        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_returnedparts_status_install,Color.parseColor("#FF0000"));
        }
        baseViewHolder.setText(R.id.tv_returnedparts_job_number,"工单号:"+item.getOrderID());//工单号
        baseViewHolder.setText(R.id.tv_reason_returnedparts,item.getMemo());//memo
        baseViewHolder.setText(R.id.tv_loaction_returnedparts,"距离："+item.getDistance()+"km");//距离
        baseViewHolder.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        baseViewHolder.setText(R.id.tv_address_returnedparts,item.getAddress());//地址
        tv_returnedparts_apply_parts =baseViewHolder.getView(R.id.tv_returnedparts_apply_parts);
        tv_continue_service =baseViewHolder.getView(R.id.tv_continue_service);
        tv_reminder =baseViewHolder.getView(R.id.tv_reminder);

        tv_hint_returnedparts =baseViewHolder.getView(R.id.tv_hint_returnedparts);
        ll_pending_appointment_cancel =baseViewHolder.getView(R.id.ll_pending_appointment_cancel);
        countdownview =baseViewHolder.getView(R.id.countdownview);
        now = TimeUtils.getNowMills();
        cancel =TimeUtils.string2Millis(item.getAudDate())+15*24*60*60*1000;
        leftTime = cancel - now;
        if (leftTime > 0) {
            countdownview.start(leftTime);
        } else {
            countdownview.stop();
            countdownview.allShowZero();
        }
        if ("G".equals(item.getAccessorySendState())){//已返件显示申请配件和继续服务
            tv_returnedparts_apply_parts.setVisibility(View.VISIBLE);
            tv_continue_service.setVisibility(View.VISIBLE);
            tv_reminder.setVisibility(View.GONE);
            ll_pending_appointment_cancel.setVisibility(View.INVISIBLE);
            tv_hint_returnedparts.setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.tv_returnedparts,"已返件");
        }else  if ("N".equals(item.getAccessorySendState())){//待返件显示催件
            tv_returnedparts_apply_parts.setVisibility(View.GONE);
            tv_continue_service.setVisibility(View.GONE);
            tv_reminder.setVisibility(View.VISIBLE);
            ll_pending_appointment_cancel.setVisibility(View.VISIBLE);
            tv_hint_returnedparts.setVisibility(View.INVISIBLE);
            baseViewHolder.setText(R.id.tv_returnedparts,"待返件");
        }else if ("Y".equals(item.getAccessorySendState())){//返件中 也就是物流中 显示申请配件
            tv_returnedparts_apply_parts.setVisibility(View.VISIBLE);
            tv_continue_service.setVisibility(View.GONE);
            tv_reminder.setVisibility(View.GONE);
            ll_pending_appointment_cancel.setVisibility(View.INVISIBLE);
            tv_hint_returnedparts.setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.tv_returnedparts,"返件中");
        }
        baseViewHolder.addOnClickListener(R.id.tv_returnedparts_apply_parts);//申请配件
        baseViewHolder.addOnClickListener(R.id.tv_continue_service);//继续服务
        baseViewHolder.addOnClickListener(R.id.tv_reminder);//催件
    }
}
