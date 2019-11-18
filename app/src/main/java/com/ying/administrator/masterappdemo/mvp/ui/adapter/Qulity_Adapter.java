package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class Qulity_Adapter extends BaseQuickAdapter<WorkOrder.DataBean, BaseViewHolder> {
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();
    private CountdownView countdownView;

    //    public Pending_Appointment_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data,UserInfo.UserInfoDean userInfo) {
//    private UserInfo.UserInfoDean userInfo;
    private ArrayList<SubUserInfo.SubUserInfoDean> subuserlist;
    private long now;
    private long cancel;
    private long leftTime;
    public Qulity_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data,UserInfo.UserInfoDean userInfo,ArrayList subuserlist) {
        super(layoutResId, data);
        this.userInfo=userInfo;
        this.subuserlist=subuserlist;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {
        //        baseViewHolder.setText(R.id.tv_quality,item.getAddress());//已完成
        baseViewHolder.setText(R.id.tv_orderid,"工单号："+item.getOrderID());
        baseViewHolder.setText(R.id.tv_quality,item.getStateStr());
        if ("Y".equals(item.getExtra()) && !"0".equals(item.getExtraTime())) {
            baseViewHolder.setText(R.id.tv_quality_status_repair, item.getTypeName() + "/" + item.getGuaranteeText() + "/加急");
        } else {
            baseViewHolder.setText(R.id.tv_quality_status_repair, item.getTypeName() + "/" + item.getGuaranteeText());
        }
        baseViewHolder.setText(R.id.tv_quality_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());//安装or维修
        if ("安装".equals(item.getTypeName())){
            baseViewHolder.setBackgroundColor(R.id.tv_quality_status_repair,Color.parseColor("#1690FF"));
            baseViewHolder.setText(R.id.tv_malfunction,"安装备注:"+item.getMemo());//memo
        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_quality_status_repair, Color.parseColor("#FF0000"));
            baseViewHolder.setText(R.id.tv_malfunction,"故障:"+item.getMemo());//memo
        }
        baseViewHolder.setText(R.id.tv_quality_job_number,"工单号:"+item.getOrderID());//工单号
        baseViewHolder.setText(R.id.tv_reason_quality,item.getBrandName() + " " + item.getSubCategoryName()+" "+item.getProductType());//memo

        baseViewHolder.setText(R.id.tv_loaction_quality,"距离："+item.getDistance()+"km");//距离
        baseViewHolder.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        baseViewHolder.setText(R.id.tv_address_quality,"地址:"+item.getAddress());//地址
        baseViewHolder.addOnClickListener(R.id.tv_quality_apply_parts);
        baseViewHolder.addOnClickListener(R.id.tv_quality_finish);
        baseViewHolder.addOnClickListener(R.id.img_pending_appointment_phone);//拨打电话事件
        baseViewHolder.addOnClickListener(R.id.tv_pending_appointment_success); //预约成功
        baseViewHolder.addOnClickListener(R.id.tv_pending_appointment_failure);//预约不成功
        baseViewHolder.addOnClickListener(R.id.tv_pending_appointment_redeploy);//转派
        baseViewHolder.addOnClickListener(R.id.tv_cancel_order);//取消工单
        baseViewHolder.addOnClickListener(R.id.rl_qulity);
        if (userInfo.getParentUserID()==null){//如果没有父账号说明自己是父账号 显示 转派
            //helper.setGone(R.id.tv_pending_appointment_redeploy,true);


            if (subuserlist.size()!=0){ //有子账号
                baseViewHolder.setGone(R.id.tv_pending_appointment_redeploy,true);

            }else {//没有子账号
                baseViewHolder.setGone(R.id.tv_pending_appointment_redeploy,false);
            }



        }else {
            baseViewHolder.setGone(R.id.tv_pending_appointment_redeploy,false);
        }
        if ("1".equals(item.getIsLook())){
            baseViewHolder.setTextColor(R.id.tv_reason_quality,Color.BLACK);
        }else{
            baseViewHolder.setTextColor(R.id.tv_reason_quality,Color.GRAY);
        }
    }
}
