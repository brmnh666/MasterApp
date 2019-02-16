package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.FAccessory;

import java.util.List;
import java.util.Map;

/*预接单的添加配置*/
public class Pre_order_Add_Ac_Adapter extends BaseQuickAdapter<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean, BaseViewHolder>  {

    public Pre_order_Add_Ac_Adapter(int layoutResId, @Nullable List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean item) {
        helper.setText(R.id.tv_accessories_name,item.getFAccessoryName());
        helper.setText(R.id.tv_accessories_number,item.getQuantity());
        helper.addOnClickListener(R.id.iv_accessories_delete);
    }



   /* @Override
    protected void convert(BaseViewHolder helper, FAccessory item) {
     helper.setText(R.id.tv_accessories_name,item.);
     helper.setText(R.id.tv_accessories_number,item.getQuantity());
     helper.addOnClickListener(R.id.iv_accessories_delete);
    }*/
}
