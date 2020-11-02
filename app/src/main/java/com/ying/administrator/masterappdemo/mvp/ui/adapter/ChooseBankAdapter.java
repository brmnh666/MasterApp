package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Bank;

import java.util.List;

public class ChooseBankAdapter extends BaseQuickAdapter<Bank, BaseViewHolder> {
    private Context context;
    public ChooseBankAdapter(int layoutResId, @Nullable List<Bank> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Bank item) {

        Glide.with(context)
                .load(item.getImageView())
                .into((ImageView) helper.getView(R.id.img_bank));
        helper.setText(R.id.tv_bank_name,item.getBankname());


        helper.addOnClickListener(R.id.ll_choose_bank);

    }
}
