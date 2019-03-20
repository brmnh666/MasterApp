package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GService;

import java.util.List;

public class GServiceAdapter extends BaseQuickAdapter<GService,BaseViewHolder> {
    public GServiceAdapter(int layoutResId, List<GService> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, GService item) {
        helper.setText(R.id.tv_service_name,item.getServiceName());
    }
}
