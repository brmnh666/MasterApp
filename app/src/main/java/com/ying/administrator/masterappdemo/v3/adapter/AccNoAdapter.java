package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.accessoryDataBean;

import java.util.List;

public class AccNoAdapter extends BaseQuickAdapter<accessoryDataBean, BaseViewHolder> {


    private accessoryDataBean.AccessoryDetailModelsBean acc;

    public AccNoAdapter(int layoutResId, @Nullable List<accessoryDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, accessoryDataBean item) {
        String state="";
        if ("-2".equals(item.getState())){
            state="终止";
        }else if ("-1".equals(item.getState())){
            state="审核拒绝";
        }else if ("0".equals(item.getState())){
            state="审核中";
        }else if ("1".equals(item.getState())){
            state="审核通过";
        }else if ("2".equals(item.getState())){
            state="已发货";
        }else if ("3".equals(item.getState())){
            state="已收货";
        }else if ("4".equals(item.getState())){
            state="待返件";
        }else if ("5".equals(item.getState())){
            state="已返件";
        }else if ("6".equals(item.getState())){
            state="待收件";
        }else if ("7".equals(item.getState())){
            state="已收件";
        }else if ("8".equals(item.getState())){
            state="已完结";
        }
        String acc_state="";
        if ("0".equals(item.getAccessoryState())){
            acc_state="厂家寄件";
        }else{
            acc_state="师傅自购件";
        }
        String accStr="";
        for (int i = 0; i < item.getAccessoryDetailModels().size(); i++) {
            acc =item.getAccessoryDetailModels().get(i);
            accStr +=acc.getFAccessoryName()+"  x"+acc.getQuantity()+"、";
        }
        if (accStr.contains("、")){
            accStr=accStr.substring(0,accStr.lastIndexOf("、"));
        }
        helper.setText(R.id.tv_acc_no,"配件单号（"+item.getAccessoryNo()+"）："+accStr+"（"+acc_state+"）----"+state);

    }
}
