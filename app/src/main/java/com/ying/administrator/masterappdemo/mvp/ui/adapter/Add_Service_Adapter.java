package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Service;

import java.util.List;

public class Add_Service_Adapter extends BaseQuickAdapter<Service, BaseViewHolder> {
    public Add_Service_Adapter(int layoutResId, @Nullable List<Service> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Service item) {
        helper.setText(R.id.tv_add_service_name,item.getFServiceName());
         helper.addOnClickListener(R.id.img_add_service_unselect);
         helper.addOnClickListener(R.id.img_add_service_select);
         helper.addOnClickListener(R.id.tv_add_service_name);
         if (item.isIschecked()==false){
             helper.setVisible(R.id.img_add_service_unselect,true);
             helper.setVisible(R.id.img_add_service_select,false);

         }else {
             helper.setVisible(R.id.img_add_service_unselect,false);
             helper.setVisible(R.id.img_add_service_select,true);
         }

    }
}
