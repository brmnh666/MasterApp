package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.accessoryDataBean;

import java.util.List;

public class AccNoAdapter extends BaseQuickAdapter<accessoryDataBean, BaseViewHolder> {


    public AccNoAdapter(int layoutResId, @Nullable List<accessoryDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, accessoryDataBean item) {
        String state="";
        if ("0".equals(item.getState())){
            state="审核中";
        }else if ("1".equals(item.getState())||"2".equals(item.getState())){
            state="审核通过";
        }else if ("-1".equals(item.getState())){
            state="审核拒绝";
        }
        String acc_state="";
        if ("0".equals(item.getAccessoryState())){
            acc_state="厂家寄件";
        }else{
            acc_state="师傅自购件";
        }
        helper.setText(R.id.tv_acc_no,item.getAccessoryNo()+"（"+state+"）----"+acc_state);

    }
}
