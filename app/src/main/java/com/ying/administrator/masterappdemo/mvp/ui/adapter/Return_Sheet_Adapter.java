package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GrabSheet_Entity;

import java.util.List;

public class Return_Sheet_Adapter extends BaseQuickAdapter<GrabSheet_Entity,BaseViewHolder> {
    public Return_Sheet_Adapter(int layoutResId, @Nullable List<GrabSheet_Entity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GrabSheet_Entity item) {
           baseViewHolder.setText(R.id.tv_address_returnedparts,item.getAddress());
        baseViewHolder.addOnClickListener(R.id.tv_returnedparts_apply_parts);
        baseViewHolder.addOnClickListener(R.id.tv_continue_service);
    }
}
