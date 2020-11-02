package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.v3.bean.OrderListResult;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<OrderListResult.DataBeanX.DataBean, BaseViewHolder> {

    private List<OrderListResult.DataBeanX.DataBean.OrderProductModelsBean> models;
    private OrderListResult.DataBeanX.DataBean.OrderProductModelsBean model;

    public HomeAdapter(int layoutResId, @Nullable List<OrderListResult.DataBeanX.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListResult.DataBeanX.DataBean item) {
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
                .setText(R.id.tv_address,"地址:"+item.getAddress())
                .setText(R.id.tv_order,"工单号:"+item.getOrderID());


        helper.setText(R.id.tv_type,item.getGuaranteeText()+"/"+item.getTypeName());

        helper.setGone(R.id.iv_location,false);
        helper.setGone(R.id.tv_location,false);
    }
}
