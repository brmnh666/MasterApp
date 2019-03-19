package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Bill;

import java.util.List;

public class Wallet_record_Adapter extends BaseQuickAdapter<Bill.DataBean, BaseViewHolder> {
    public Wallet_record_Adapter(int layoutResId, @Nullable List<Bill.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bill.DataBean item) {
    //充值


    if (item.getState().equals("1")){
        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_record_time,time);
        helper.setText(R.id.tv_record_happen,"充值");
        helper.setText(R.id.tv_record_add_reduce,"+");
        helper.setText(R.id.tv_record_money,"¥"+item.getPayMoney());
        helper.setTextColor(R.id.tv_record_money, Color.GREEN);

    }
        //提现
        if (item.getState().equals("3")){
            StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
            String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
            helper.setText(R.id.tv_record_time,time);
            helper.setText(R.id.tv_record_happen,"提现");
            helper.setText(R.id.tv_record_add_reduce,"-");
            helper.setText(R.id.tv_record_money,"¥"+item.getPayMoney());
            helper.setTextColor(R.id.tv_record_money, Color.RED);

        }

        //收入或支出

        if (item.getState().equals("2")||item.getState().equals("5")){
            StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
            String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
            helper.setText(R.id.tv_record_time,time);

            if (item.getState().equals("2")){
                helper.setText(R.id.tv_record_happen,"收入");
                helper.setText(R.id.tv_record_add_reduce,"+");
                helper.setText(R.id.tv_record_money,"¥"+item.getPayMoney());
                helper.setTextColor(R.id.tv_record_money, Color.GREEN);
            }else {
                helper.setText(R.id.tv_record_happen,"支出");
                helper.setText(R.id.tv_record_add_reduce,"-");
                helper.setText(R.id.tv_record_money,"¥"+item.getPayMoney());
                helper.setTextColor(R.id.tv_record_money, Color.RED);
            }

        }





    }
}
