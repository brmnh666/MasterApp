package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.widget.adderView;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;


/*dialog添加配件 的adpter*/
public class Add_Ac_Adapter extends BaseQuickAdapter<Accessory, BaseViewHolder> {
   // AddSubUtils list_item_utils;

    public Add_Ac_Adapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Accessory item) {


        helper.setText(R.id.tv_accessory_name,item.getAccessoryName());




        helper.addOnClickListener(R.id.tv_accessory_name);
        helper.addOnClickListener(R.id.img_ac_unselect);
        helper.addOnClickListener(R.id.img_ac_select);


    }
}
