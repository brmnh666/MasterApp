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
      baseViewHolder.setText(R.id.tv_reason,item.getMemo()); //故障原因
      if (item.getTypeID().equals("1")){ //维修
            baseViewHolder.setVisible(R.id.tv_grabsheet_status_repair,true);
          baseViewHolder.setVisible(R.id.tv_grabsheet_status_install,false);

      }else{//安装
          baseViewHolder.setVisible(R.id.tv_grabsheet_status_repair,false);
          baseViewHolder.setVisible(R.id.tv_grabsheet_status_install,true);

      }

      baseViewHolder.setText(R.id.tv_address,item.getAddress()); //地址
      baseViewHolder.setText(R.id.tv_grabsheet_time, MyUtils.getTimebefore(item.getCreateDate()));//将订单生产的时间传入


      baseViewHolder.addOnClickListener(R.id.img_grabsheet);
    }
}
