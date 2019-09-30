package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.List;

public class LeaveMessageImgAdapter extends BaseQuickAdapter<WorkOrder.LeavemessageimgListBean, BaseViewHolder> {
    public LeaveMessageImgAdapter(int layoutResId, @Nullable List<WorkOrder.LeavemessageimgListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.LeavemessageimgListBean item) {
        ImageView icon=helper.getView(R.id.iv_image);
        GlideUtil.loadImageViewLoding(mContext, Config.Leave_Message_URL +item.getUrl(),icon, R.drawable.image_loading,R.drawable.image_loading);
        helper.addOnClickListener(R.id.iv_image);
    }
}
