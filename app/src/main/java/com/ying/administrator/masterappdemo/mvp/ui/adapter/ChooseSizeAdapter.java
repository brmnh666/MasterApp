package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.ShopSize;
import java.util.List;

public class ChooseSizeAdapter extends BaseQuickAdapter<ShopSize, BaseViewHolder> {
    public ChooseSizeAdapter(int layoutResId, @Nullable List<ShopSize> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopSize item) {

        helper.setText(R.id.tv_size,item.getValue());
        helper.addOnClickListener(R.id.rl_choose);
    }

}
