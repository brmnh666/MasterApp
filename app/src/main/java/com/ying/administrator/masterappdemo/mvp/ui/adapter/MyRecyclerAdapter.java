package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jmf.addsubutils.AddSubUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;

import java.util.List;




public class MyRecyclerAdapter extends BaseQuickAdapter<Accessory, BaseViewHolder> {
    AddSubUtils list_item_utils;

    public MyRecyclerAdapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Accessory item) {
        list_item_utils=helper.getView(R.id.addaccessory_addsubutil);
        list_item_utils.setStep(1)
                .setPosition(10000)
                .setCurrentNumber(0)
                .setBuyMin(0)
                .setInventory(36)//库存
                .setOnWarnListener(new AddSubUtils.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        Toast.makeText(mContext, "当前库存:" + inventory, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWarningForBuyMax(int max) {
                        Toast.makeText(mContext, "超过最大购买数:" + max, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWarningForBuyMin(int min) {
                        Toast.makeText(mContext, "低于最小购买数:" + min, Toast.LENGTH_SHORT).show();
                    }
                });


        helper.setText(R.id.tv_accessory_name,item.getAccessoryName());
        helper.addOnClickListener(R.id.tv_accessory_name);
        helper.addOnClickListener(R.id.rl_item_addaccessory);
        helper.addOnClickListener(R.id.img_ac_unselect);
        helper.addOnClickListener(R.id.img_ac_select);
    }
}
