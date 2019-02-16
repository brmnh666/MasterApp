package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.FAccessory;

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

       if (item.isIscheck()){ //是被选中的状态  红钩显示  加减器显示
           helper.setVisible(R.id.img_ac_select,true);
           helper.setVisible(R.id.img_ac_unselect,false);
           helper.setVisible(R.id.adderView,true);

        }else { //未选中状态
           helper.setVisible(R.id.img_ac_select,false);
           helper.setVisible(R.id.img_ac_unselect,true);
           helper.setVisible(R.id.adderView,false);

       }

        helper.addOnClickListener(R.id.tv_accessory_name);
        helper.addOnClickListener(R.id.rl_item_addaccessory);
        helper.addOnClickListener(R.id.img_ac_unselect);
        helper.addOnClickListener(R.id.img_ac_select);
       helper.addOnClickListener(R.id.rl_item_addaccessory);
    }
}
