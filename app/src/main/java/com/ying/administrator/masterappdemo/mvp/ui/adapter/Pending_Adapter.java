package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Pending_Appointment_Entity;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class Pending_Adapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();
    private CountdownView countdownView;

    //    public Pending_Appointment_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data,UserInfo.UserInfoDean userInfo) {
//    private UserInfo.UserInfoDean userInfo;
    private long now;
    private long cancel;
    private long leftTime;

    public Pending_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data, UserInfo.UserInfoDean userInfo) {
        super(layoutResId, data);
        this.userInfo=userInfo;
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {

        helper.setText(R.id.tv_pending_appointment,item.getStateStr());
        helper.setText(R.id.tv_pending_appointment_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());
        helper.setText(R.id.tv_loaction_appointment,"距离 "+item.getDistance()+"Km");
        helper.setText(R.id.tv_reason_pending_appointment,item.getMemo());//原因
        helper.setText(R.id.tv_address_pending_appointment,item.getAddress()); //地址
        helper.setText(R.id.tv_num,"数量:"+item.getNum()+"台");
        helper.setText(R.id.tv_pending_appointment_job_number,"工单号:"+item.getOrderID());
        countdownView =helper.getView(R.id.countdownview);
        now =TimeUtils.getNowMills();
        cancel =TimeUtils.string2Millis(item.getAudDate())+3600000;
        leftTime=cancel-now;
        if (leftTime > 0) {
            countdownView.start(leftTime);
        } else {
            countdownView.stop();
            countdownView.allShowZero();
        }


        if (userInfo.getParentUserID()==null){//如果没有父账号说明自己是父账号 显示 转派
            //helper.setGone(R.id.tv_pending_appointment_redeploy,true);


//            if (subuserlist.size()!=0){ //有子账号
//                helper.setGone(R.id.tv_pending_appointment_redeploy,true);
//
//            }else {//没有子账号
                helper.setGone(R.id.tv_pending_appointment_redeploy,false);
//            }



        }else {
            helper.setGone(R.id.tv_pending_appointment_redeploy,false);
        }


        helper.addOnClickListener(R.id.img_pending_appointment_phone);//拨打电话事件
        helper.addOnClickListener(R.id.tv_pending_appointment_success); //预约成功
        helper.addOnClickListener(R.id.tv_pending_appointment_failure);//预约不成功
        helper.addOnClickListener(R.id.tv_pending_appointment_redeploy);//转派
        helper.addOnClickListener(R.id.tv_cancel_order);//取消工单
//        helper.addOnClickListener(R.id.rl_pending_appointment);
    }


}
