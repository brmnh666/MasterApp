package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;

import java.util.List;


/*dialog添加配件 的adpter*/
public class Ac_List_Adapter extends BaseQuickAdapter<Accessory, BaseViewHolder> {
    public Ac_List_Adapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final Accessory item) {
        helper.setText(R.id.tv_accessory_name,item.getCj_or_zg()==0?item.getAccessoryName()+"  x"+item.getCount():item.getAccessoryName()+"  x"+item.getCount()+"    ¥"+item.getAccessoryPrice());
    }

}

