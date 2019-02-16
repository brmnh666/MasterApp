package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.FService;

import java.util.List;

/*预接单的添加配置*/
public class Pre_order_Add_Service_Adapter extends BaseQuickAdapter<FService.OrderServiceStrBean.OrderServiceBean, BaseViewHolder> {
    public Pre_order_Add_Service_Adapter(int layoutResId, @Nullable List<FService.OrderServiceStrBean.OrderServiceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FService.OrderServiceStrBean.OrderServiceBean item) {
        helper.setText(R.id.tv_service_name,item.getServiceName()); //模拟

        helper.addOnClickListener(R.id.iv_service_delete);
    }


    /* helper.setText(R.id.tv_service_name,item.getOrderServiceStr()); //模拟

     helper.addOnClickListener(R.id.iv_service_delete);*/
}
