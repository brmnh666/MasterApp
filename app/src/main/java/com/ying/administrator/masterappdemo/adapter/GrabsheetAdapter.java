package com.ying.administrator.masterappdemo.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;

import java.util.List;

public class GrabsheetAdapter extends BaseQuickAdapter<GrabSheet_Entity,BaseViewHolder> {


    public GrabsheetAdapter(int layoutResId, @Nullable List<GrabSheet_Entity> data) {
        super(layoutResId, data);
        List<GrabSheet_Entity> list=data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GrabSheet_Entity item) {
     baseViewHolder.setText(R.id.tv_grabsheet_time,item.getTime())
                   .setText(R.id.tv_username,item.getUsername())
                   .setText(R.id.tv_loaction,item.getDistance())
                   .setText(R.id.tv_reason,item.getReason())
                   .setText(R.id.tv_address,item.getAddress());


    }
}
