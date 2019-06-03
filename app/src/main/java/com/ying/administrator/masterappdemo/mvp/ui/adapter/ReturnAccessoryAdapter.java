package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GAccessory;

import java.util.List;

public class ReturnAccessoryAdapter extends BaseQuickAdapter<GAccessory,BaseViewHolder> {
    private int state;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyDataSetChanged();
    }

    public ReturnAccessoryAdapter(int layoutResId, List<GAccessory> data, int state, String content) {
        super(layoutResId, data);
        this.state=state;
        this.content=content;
    }
    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        if (state==0){
            helper.getView(R.id.ll_state_y).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_state_n).setVisibility(View.VISIBLE);
            if ("Y".equals(item.getSendState())){
                helper.getView(R.id.ll_y).setVisibility(View.VISIBLE);
                helper.getView(R.id.ll_n).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.ll_y).setVisibility(View.GONE);
                helper.getView(R.id.ll_n).setVisibility(View.VISIBLE);
            }
        }else{
            helper.getView(R.id.ll_state_y).setVisibility(View.GONE);
            helper.getView(R.id.ll_state_n).setVisibility(View.GONE);
            helper.getView(R.id.ll_y).setVisibility(View.GONE);
            helper.getView(R.id.ll_n).setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_accessory_name,item.getFAccessoryName()+"  ¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
        helper.setText(R.id.tv_accessory_name_n,item.getFAccessoryName()+"  ¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
        helper.setText(R.id.tv_content,content);
    }
}
