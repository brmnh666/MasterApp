package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;

import java.util.List;




public class MyRecyclerAdapter extends BaseQuickAdapter<Accessory, BaseViewHolder> {
   // AddSubUtils list_item_utils;

    public MyRecyclerAdapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Accessory item) {


        helper.setText(R.id.tv_accessory_name,item.getAccessoryName());
        helper.addOnClickListener(R.id.tv_accessory_name);
        helper.addOnClickListener(R.id.rl_item_addaccessory);
        helper.addOnClickListener(R.id.img_ac_unselect);
        helper.addOnClickListener(R.id.img_ac_select);
        helper.addOnClickListener(R.id.tv_accessory_name);
    }
}
