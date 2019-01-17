package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;

import java.util.List;

public class Appointment_failure_Adapter extends BaseQuickAdapter<GrabSheet_Entity,BaseViewHolder> {
    public Appointment_failure_Adapter(int layoutResId, @Nullable List<GrabSheet_Entity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GrabSheet_Entity item) {
baseViewHolder.setText(R.id.tv_address_appointment_failure,item.getAddress());
baseViewHolder.addOnClickListener(R.id.img_pending_appointment_failure_phone);
    }
}
