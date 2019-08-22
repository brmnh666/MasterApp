package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.SubsidiaryAccount;

import java.util.List;

public class SubsidiaryAccountAdapter extends BaseQuickAdapter<SubsidiaryAccount, BaseViewHolder> {
    public SubsidiaryAccountAdapter(int layoutResId, @Nullable List<SubsidiaryAccount> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubsidiaryAccount item) {
        helper.addOnClickListener(R.id.ll_subsidiary_account);
    }
}
