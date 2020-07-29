package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class ProdAdapter extends BaseQuickAdapter<WorkOrder.OrderProductModelsBean, BaseViewHolder> {


    public ProdAdapter(int layoutResId, @Nullable List<WorkOrder.OrderProductModelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.OrderProductModelsBean item) {
        String name=item.getSubCategoryName()+"（编号："+item.getOrderProdcutID()+")";
        helper.setText(R.id.tv_product_name,name);
        if(item.getAccessoryData()!=null){
            if (item.getAccessoryData().size()>0){
                helper.setGone(R.id.ll_acc,true);
                String acc="";
                for (int i = 0; i < item.getAccessoryData().size(); i++) {
                    acc+=item.getAccessoryData().get(i).getFAccessoryName()+"   x"+item.getAccessoryData().get(i).getQuantity()+"、";
                }
                if (acc.contains("、")){
                    acc=acc.substring(0,acc.lastIndexOf("、"));
                }
                helper.setText(R.id.tv_acc,acc);
            }else{
                helper.setGone(R.id.ll_acc,false);
            }
        }else{
            helper.setGone(R.id.ll_acc,false);
        }

    }
}
