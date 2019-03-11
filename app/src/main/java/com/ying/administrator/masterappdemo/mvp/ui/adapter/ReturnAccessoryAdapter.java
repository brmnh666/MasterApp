package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GAccessory;

import java.util.List;

public class ReturnAccessoryAdapter extends BaseQuickAdapter<GAccessory,BaseViewHolder> {
    public ReturnAccessoryAdapter(int layoutResId, List<GAccessory> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        if ("Y".equals(item.getSendState())){
            helper.getView(R.id.ll_y).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_n).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.ll_y).setVisibility(View.GONE);
            helper.getView(R.id.ll_n).setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_accessory_name,item.getFAccessoryName());
        helper.setText(R.id.tv_accessory_name_n,item.getFAccessoryName());
    }
}
