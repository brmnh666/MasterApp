package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.location.Address;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.entity.SubAccount;

import java.util.List;

public class SubAccountAdapter extends BaseQuickAdapter<SubAccount, BaseViewHolder> {
    public SubAccountAdapter(int layoutResId, @Nullable List<SubAccount> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubAccount item) {

    }
}
