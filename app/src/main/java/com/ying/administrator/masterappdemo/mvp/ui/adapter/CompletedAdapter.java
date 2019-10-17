package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class CompletedAdapter extends BaseQuickAdapter<WorkOrder.DataBean, BaseViewHolder> {
    public CompletedAdapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {
        baseViewHolder.setText(R.id.tv_complete,item.getStateStr());//已完成
        baseViewHolder.setText(R.id.tv_complete_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());//安装or维修
        if ("安装".equals(item.getTypeName())){
            baseViewHolder.setBackgroundColor(R.id.tv_complete_status_repair, Color.parseColor("#1690FF"));
            baseViewHolder.setText(R.id.tv_malfunction,item.getMemo());//memo
        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_complete_status_repair,Color.parseColor("#FF0000"));
            baseViewHolder.setText(R.id.tv_malfunction,"故障:"+item.getMemo());//memo

        }
        baseViewHolder.setText(R.id.tv_complete_job_number,"工单号:"+item.getOrderID());//工单号
        baseViewHolder.setText(R.id.tv_reason_complete,item.getCategoryName() + " " + item.getBrandName() + " " + item.getSubCategoryName());//memo
        baseViewHolder.setText(R.id.tv_loaction_complete,"距离："+item.getDistance()+"km");//距离
        baseViewHolder.setText(R.id.tv_num,"数量："+item.getNum()+"台");//数量
        baseViewHolder.setText(R.id.tv_address_complete,"地址:"+item.getAddress());//地址
        if ("5".equals(item.getState())){
            baseViewHolder.setText(R.id.tv_review,"已完成待确认");
            baseViewHolder.getView(R.id.tv_review).setVisibility(View.VISIBLE);
        }else {
            baseViewHolder.setText(R.id.tv_review,"结算完成");
            baseViewHolder.getView(R.id.tv_review).setVisibility(View.VISIBLE);
        }

    }
}
