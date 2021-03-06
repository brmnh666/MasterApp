package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class Complete_wait_fetch_Adapter extends BaseQuickAdapter<WorkOrder.DataBean, BaseViewHolder> {

    public Complete_wait_fetch_Adapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {
                baseViewHolder.setText(R.id.tv_orderid,"工单号："+item.getOrderID());
                baseViewHolder.setText(R.id.tv_complete_wait_fetch,item.getStateStr());
        if ("Y".equals(item.getExtra()) && !"0".equals(item.getExtraTime())) {
            baseViewHolder.setText(R.id.tv_complete_status_repair, item.getTypeName() + "/" + item.getGuaranteeText() + "/加急");
        } else {
            baseViewHolder.setText(R.id.tv_complete_status_repair, item.getTypeName() + "/" + item.getGuaranteeText());
        }
//        baseViewHolder.setText(R.id.tv_complete_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());//安装or维修
        if ("安装".equals(item.getTypeName())){
            baseViewHolder.setBackgroundColor(R.id.tv_complete_wait_fetch_status_repair, Color.parseColor("#1690FF"));
            baseViewHolder.setText(R.id.tv_malfunction, "安装备注:"+item.getMemo());//原因

        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_complete_wait_fetch_status_repair,Color.parseColor("#FF0000"));
            baseViewHolder.setText(R.id.tv_malfunction,"故障:"+item.getMemo());//memo

        }
        baseViewHolder.setText(R.id.tv_complete_wait_fetch_job_number,"工单号:"+item.getOrderID());//工单号
        baseViewHolder.setText(R.id.tv_reason_complete_wait_fetch,item.getBrandName() + " " + item.getSubCategoryName()+" "+item.getProductType());//memo
        baseViewHolder.setText(R.id.tv_loaction_complete_wait_fetch,"距离："+item.getDistance()+"km");//距离
        baseViewHolder.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        baseViewHolder.setText(R.id.tv_address_complete_wait_fetch,"地址:"+item.getAddress());//地址
        if ("1".equals(item.getIsLook())){
            baseViewHolder.setTextColor(R.id.tv_reason_complete_wait_fetch,Color.BLACK);
        }else{
            baseViewHolder.setTextColor(R.id.tv_reason_complete_wait_fetch,Color.GRAY);
        }
    }
}
