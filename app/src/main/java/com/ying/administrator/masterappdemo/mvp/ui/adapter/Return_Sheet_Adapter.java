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
    private TextView tv_telephone_reminder;
    private long cancel2;
    private long leftTime2;

    public Return_Sheet_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {

        baseViewHolder.setText(R.id.tv_orderid,"工单号："+item.getOrderID());
        baseViewHolder.setText(R.id.tv_returnedparts,item.getStateStr());
        if ("Y".equals(item.getExtra()) && !"0".equals(item.getExtraTime())) {
            baseViewHolder.setText(R.id.tv_returnedparts_status_repair, item.getTypeName() + "/" + item.getGuaranteeText() + "/加急");
        } else {
            baseViewHolder.setText(R.id.tv_returnedparts_status_repair, item.getTypeName() + "/" + item.getGuaranteeText());
        }
//        baseViewHolder.setText(R.id.tv_returnedparts_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());//安装or维修
        if ("安装".equals(item.getTypeName())){
            baseViewHolder.setBackgroundColor(R.id.tv_returnedparts_status_repair, Color.parseColor("#1690FF"));
            baseViewHolder.setText(R.id.tv_malfunction,"安装备注:"+item.getMemo());//memo
        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_returnedparts_status_repair,Color.parseColor("#FF0000"));
            baseViewHolder.setText(R.id.tv_malfunction,"故障:"+item.getMemo());//memo
        }
        baseViewHolder.setText(R.id.tv_returnedparts_job_number,"工单号:"+item.getOrderID());//工单号
        baseViewHolder.setText(R.id.tv_reason_returnedparts,item.getBrandName() + " " + item.getSubCategoryName()+" "+item.getProductType());//memo
        baseViewHolder.setText(R.id.tv_loaction_returnedparts,"距离："+item.getDistance()+"km");//距离
        baseViewHolder.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        baseViewHolder.setText(R.id.tv_address_returnedparts,"地址:"+item.getAddress());//地址
        tv_returnedparts_apply_parts =baseViewHolder.getView(R.id.tv_returnedparts_apply_parts);
        tv_continue_service =baseViewHolder.getView(R.id.tv_continue_service);
        tv_reminder =baseViewHolder.getView(R.id.tv_reminder);
        if ("Y".equals(item.getIsPressFactory())){
            tv_reminder.setText("再次催件");
        }else{
            tv_reminder.setText("催件");

        }

        tv_hint_returnedparts =baseViewHolder.getView(R.id.tv_hint_returnedparts);
        ll_pending_appointment_cancel =baseViewHolder.getView(R.id.ll_pending_appointment_cancel);
        countdownview =baseViewHolder.getView(R.id.countdownview);
        tv_telephone_reminder = baseViewHolder.getView(R.id.tv_telephone_reminder);
        now = TimeUtils.getNowMills();
        cancel =TimeUtils.string2Millis(item.getAudDate())+15*24*60*60*1000;
        leftTime = cancel - now;
        if (leftTime > 0) {
            countdownview.start(leftTime);
        } else {
            countdownview.stop();
            countdownview.allShowZero();
        }

        cancel2 = TimeUtils.string2Millis(item.getAudDate())+20*24*60*60*1000;
        leftTime2 = cancel2 -now;
        if (item.getIsLate()!=null){
            baseViewHolder.setGone(R.id.tv_apply_for_an_extension,false);
            if (leftTime > 0) {
                countdownview.start(leftTime2);
            } else {
                countdownview.stop();
                countdownview.allShowZero();
            }
        }

//        N未返件 Y已返件 P部分返件
        /*if ("N".equals(item.getAccessorySendState())||"P".equals(item.getAccessorySendState())){//未返件显示催件
            tv_returnedparts_apply_parts.setVisibility(View.GONE);
            tv_continue_service.setVisibility(View.GONE);
            tv_reminder.setVisibility(View.VISIBLE);
            tv_telephone_reminder.setVisibility(View.VISIBLE);
            ll_pending_appointment_cancel.setVisibility(View.VISIBLE);
            tv_hint_returnedparts.setVisibility(View.INVISIBLE);
            baseViewHolder.setText(R.id.tv_returnedparts,"未返件");
        }else {//已返件 也就是物流中 显示申请配件，完成工单
            tv_returnedparts_apply_parts.setVisibility(View.VISIBLE);
            tv_continue_service.setVisibility(View.VISIBLE);
            tv_reminder.setVisibility(View.GONE);
            tv_telephone_reminder.setVisibility(View.GONE);
            ll_pending_appointment_cancel.setVisibility(View.INVISIBLE);
            tv_hint_returnedparts.setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.tv_returnedparts,"已返件");
        }*/
//        baseViewHolder.addOnClickListener(R.id.tv_returnedparts_apply_parts);//申请配件
//        baseViewHolder.addOnClickListener(R.id.tv_continue_service);//完成工单
//        baseViewHolder.addOnClickListener(R.id.tv_reminder);//催件
        baseViewHolder.addOnClickListener(R.id.tv_see_detail);//查看详情
        baseViewHolder.addOnClickListener(R.id.tv_apply_for_an_extension);//申请延期
//        baseViewHolder.addOnClickListener(R.id.tv_telephone_reminder);//电话催件
        baseViewHolder.addOnClickListener(R.id.tv_complaint);
        if("".equals(item.getAccessoryAndServiceApplyState())){
            baseViewHolder.setText(R.id.tv_review,"");
            baseViewHolder.getView(R.id.tv_review).setVisibility(View.GONE);
        } else if ("0".equals(item.getAccessoryAndServiceApplyState())) {
            baseViewHolder.setText(R.id.tv_review,"审核中");
        } else if ("1".equals(item.getAccessoryAndServiceApplyState())) {
            baseViewHolder.setText(R.id.tv_review,"审核通过");
        } else if ("2".equals(item.getAccessoryAndServiceApplyState())) {
            baseViewHolder.setText(R.id.tv_review,"厂家寄件");
        } else {
            baseViewHolder.setText(R.id.tv_review,"被拒");
        }
//        if("".equals(item.getServiceApplyState())){
//            baseViewHolder.setText(R.id.tv_review2,"");
//        } else if ("0".equals(item.getServiceApplyState())) {
//            baseViewHolder.setText(R.id.tv_review2,"服务审核中");
//        } else if ("1".equals(item.getServiceApplyState())) {
//            baseViewHolder.setText(R.id.tv_review2,"服务审核通过");
//        } else {
//            baseViewHolder.setText(R.id.tv_review2,"服务被拒");
//        }

        if (item.getBeyondState()==null){
            baseViewHolder.setText(R.id.tv_review3,"");
            baseViewHolder.getView(R.id.tv_review3).setVisibility(View.INVISIBLE);
        }else if ("0".equals(item.getBeyondState())) {
            baseViewHolder.setText(R.id.tv_review3,"远程费审核中");
        } else if ("1".equals(item.getBeyondState())) {
            baseViewHolder.setText(R.id.tv_review3,"远程费通过");

        }  else if ("2".equals(item.getBeyondState())) {
            baseViewHolder.setText(R.id.tv_review3,"远程费通过");

        } else {
            baseViewHolder.setText(R.id.tv_review3,"远程费被拒");
        }

        if ("1".equals(item.getIsLook())){
            baseViewHolder.setTextColor(R.id.tv_reason_returnedparts,Color.BLACK);
        }else{
            baseViewHolder.setTextColor(R.id.tv_reason_returnedparts,Color.GRAY);
        }
    }


}
