package com.ying.administrator.masterappdemo.mvp.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import java.util.List;

public class GrabsheetAdapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {


    public GrabsheetAdapter(int layoutResId, @Nullable List<WorkOrder.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WorkOrder.DataBean item) {
      baseViewHolder.setText(R.id.tv_reason,item.getMemo());
      baseViewHolder.addOnClickListener(R.id.img_grabsheet);
    }
}
