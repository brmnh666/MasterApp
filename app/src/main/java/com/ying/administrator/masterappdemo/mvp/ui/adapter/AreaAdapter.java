package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Area;

import java.util.List;

public class AreaAdapter extends BaseQuickAdapter<Area,BaseViewHolder> {
    public AreaAdapter(int layoutResId, List<Area> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Area item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_category,item.getName());
    }
}
