package com.ying.administrator.masterappdemo.mvp.ui.adapter;


import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.util.MyUtils;

import java.util.IdentityHashMap;
import java.util.List;

public class GrabsheetAdapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {


    public GrabsheetAdapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {

        baseViewHolder.setText(R.id.tv_loaction,"距离:"+item.getDistance()+"Km");
        baseViewHolder.setText(R.id.tv_orderid,"工单号:"+item.getOrderID());
        baseViewHolder.setText(R.id.tv_brand_name,item.getCategoryName()+" "+item.getBrandName());

      baseViewHolder.setText(R.id.tv_grabsheet_status_repair,item.getTypeName()+"/"+item.getGuaranteeText());
      baseViewHolder.setText(R.id.tv_address,"地址:"+item.getAddress()); //地址
      baseViewHolder.setText(R.id.tv_grabsheet_time, MyUtils.getTimebefore(item.getCreateDate()));//将订单生产的时间传入
      baseViewHolder.addOnClickListener(R.id.img_grabsheet);
      if ("维修".equals(item.getTypeName())){
          baseViewHolder.setText(R.id.tv_reason,"故障:"+item.getMemo()); //故障原因
      }else {
          baseViewHolder.setText(R.id.tv_reason,item.getMemo()); //故障原因
      }
    }
}
