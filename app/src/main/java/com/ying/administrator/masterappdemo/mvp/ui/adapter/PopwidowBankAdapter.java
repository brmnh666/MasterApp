package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.BankCard;

import java.util.List;

public class PopwidowBankAdapter extends BaseQuickAdapter<BankCard, BaseViewHolder>  {
    public PopwidowBankAdapter(int layoutResId, @Nullable List<BankCard> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCard item) {
       helper.setText(R.id.tv_bank_name,item.getPayInfoName());
       String s=item.getPayNo();
       helper.setText(R.id.tv_bank_num,"("+ s.substring(s.length()-4,s.length())+")");
        ImageView imagecheck= helper.getView(R.id.img_bankcheck);
       if (item.isIscheck()){
           imagecheck.setSelected(true);
       }else {
           imagecheck.setSelected(false);
       }
       helper.addOnClickListener(R.id.rl_popwindow_bank);




    }

}
