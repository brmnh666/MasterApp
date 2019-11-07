package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

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

        helper.setText(R.id.tv_in_service,item.getStateStr());
        helper.setText(R.id.tv_orderid,"工单号："+item.getOrderID());
        if ("Y".equals(item.getExtra()) && !"0".equals(item.getExtraTime())) {
            helper.setText(R.id.tv_in_service_status_repair, item.getTypeName() + "/" + item.getGuaranteeText() + "/加急");
        } else {
            helper.setText(R.id.tv_in_service_status_repair, item.getTypeName() + "/" + item.getGuaranteeText());
        }
//        helper.setText(R.id.tv_in_service_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());
        helper.setText(R.id.tv_number,"数量:"+item.getNum()+"台");
        helper.setText(R.id.tv_loaction_in_service,"距离 "+item.getDistance()+"Km");
        helper.setText(R.id.tv_reason_in_service,item.getBrandName() + " " + item.getSubCategoryName()+" "+item.getProductType());//原因
        if ("维修".equals(item.getTypeName())){
            helper.setText(R.id.tv_malfunction, "故障:"+item.getMemo());//原因
            helper.setBackgroundColor(R.id.tv_in_service_status_repair, Color.parseColor("#FF0000"));
        }else if ("安装".equals(item.getTypeName())){
            helper.setText(R.id.tv_malfunction, "安装备注:"+item.getMemo());//原因
            helper.setBackgroundColor(R.id.tv_in_service_status_repair, Color.parseColor("#1690FF"));
            helper.setText(R.id.tv_hint_in_service,"提示:请务必在维修过程中拍照");
            helper.setTextColor(R.id.tv_hint_in_service,Color.parseColor("#ff0000"));
        }else {
            helper.setText(R.id.tv_malfunction, item.getMemo());//原因
        }
        helper.setText(R.id.tv_address_in_service,"地址:"+item.getAddress()); //地址
        helper.setText(R.id.tv_in_service_job_number,"工单号:"+item.getOrderID());
        if (item.getBeyondState()==null){
            helper.setText(R.id.tv_review,"");
            helper.getView(R.id.tv_review).setVisibility(View.INVISIBLE);
        }else if ("0".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_review,"远程费审核中");
        } else if ("1".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_review,"远程费审核通过");
        }  else if ("2".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_review,"远程费修改通过");
        } else {
            helper.setText(R.id.tv_review,"远程费被拒");
        }

//         if (item.getBeyondState()==null){
//         }else if (item.getBeyondState().equals("0")){//待审核
//             helper.setText(R.id.tv_remote_fee,"远程费审核中");
//         }else if (item.getBeyondState().equals("1")){//审核通过
//             helper.setText(R.id.tv_remote_fee,"远程费审核通过");
//         }else {//审核不通过
//             helper.setText(R.id.tv_remote_fee,"远程费审核不通过");
//         }



      helper.addOnClickListener(R.id.tv_see_detail);//完成工单
      helper.addOnClickListener(R.id.tv_in_service_apply_parts);//申请配件
      helper.addOnClickListener(R.id.tv_cancel_work_order);//取消工单

      helper.addOnClickListener(R.id.img_navigation);//导航图片
        helper.addOnClickListener(R.id.tv_complaint);
    }




}
