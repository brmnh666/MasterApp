package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;

import java.util.List;

/**
 * Data:2019/7/30
 * Time:15:33
 * author:ying
 **/
public class MyPackageAdapter extends BaseQuickAdapter<Accessory,BaseViewHolder> {

    public MyPackageAdapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Accessory item) {
        helper.setText(R.id.tv_ac_name,item.getAccessoryName());
        helper.setText(R.id.tv_num,"数量:"+item.getCount()+"件");
    }
}
