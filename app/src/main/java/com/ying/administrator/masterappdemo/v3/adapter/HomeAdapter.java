package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.util.MyUtils;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<WorkOrder.DataBean, BaseViewHolder> {

    private List<WorkOrder.OrderProductModelsBean> models;
    private WorkOrder.OrderProductModelsBean model;

    public HomeAdapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        models =item.getOrderProductModels();
        String name="";
        if (models!=null){
            for (int i = 0; i < models.size(); i++) {
                model =models.get(i);
                name+=model.getBrandName()+"("+model.getSubCategoryName()+")"+model.getProdModelName()+"、";
            }
        }
        if (name.contains("、")){
            name=name.substring(0,name.lastIndexOf("、"));
        }
        helper.addOnClickListener(R.id.tv_cancel)
                .addOnClickListener(R.id.tv_orders)
                .addOnClickListener(R.id.iv_copy);
        helper.setText(R.id.tv_time, MyUtils.getTimebefore(item.getCreateDate()))
                .setText(R.id.tv_name,name)
                .setText(R.id.tv_location,"距离:"+item.getDistance()+"Km")
                .setText(R.id.tv_malfunction,"故障:"+item.getMemo())
                .setText(R.id.tv_address,"地址:"+item.getAddress())
                .setText(R.id.tv_order,"工单号:"+item.getOrderID());


        helper.setText(R.id.tv_type,item.getGuaranteeText()+"/"+item.getTypeName());

        helper.setGone(R.id.iv_location,false);
        helper.setGone(R.id.tv_location,false);

//        if ("1".equals(item.getPartyNo())){
//            tv_orders.setText("报价");
//            tv_orders.setBackgroundResource(R.drawable.v3_yellow_shape);
//            tv_orders.setTextColor(Color.parseColor("#000000"));
//            helper.setText(R.id.tv_type,"用户发单/"+item.getTypeName());
//            helper.setBackgroundRes(R.id.tv_type,R.drawable.v3_yellow_shape);
//            helper.setTextColor(R.id.tv_type, Color.parseColor("#000000"));
//        }else {
//            if ("0".equals(item.getPartyNo())){
//                helper.setGone(R.id.iv_location,false);
//                helper.setGone(R.id.tv_location,false);
//            }else {
//                helper.setVisible(R.id.iv_location,false);
//                helper.setVisible(R.id.tv_location,false);
//            }
//            tv_orders.setText("接单");
//            tv_orders.setBackgroundResource(R.drawable.v3_orders_shape);
//            tv_orders.setTextColor(Color.parseColor("#ffffff"));
//            if ("Y".equals(item.getExtra())&&!"0".equals(item.getExtraTime())){
//                helper.setText(R.id.tv_type,item.getGuaranteeText()+"/"+item.getTypeName()+"/加急");
//            }else {
//                helper.setText(R.id.tv_type,item.getGuaranteeText()+"/"+item.getTypeName());
//            }
//            helper.setBackgroundRes(R.id.tv_type,R.drawable.v3_home_bg);
//            helper.setTextColor(R.id.tv_type, Color.parseColor("#01B1D2"));
//        }
    }
}
