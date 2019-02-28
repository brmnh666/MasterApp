package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Area;
import com.ying.administrator.masterappdemo.entity.ServiceAddress;

import java.util.List;

public class ServiceAddressAdapter extends BaseQuickAdapter<ServiceAddress,BaseViewHolder> {
    public ServiceAddressAdapter(int layoutResId, List<ServiceAddress> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ServiceAddress item) {
        // 加载网络图片
//        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.icon));
        helper.setText(R.id.tv_region,item.getName());
        helper.addOnClickListener(R.id.iv_delete);
    }
}
