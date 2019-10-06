package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.GAccessory;

import java.util.List;

public class ReturnAccessoryAdapter extends BaseQuickAdapter<GAccessory,BaseViewHolder> {
    private String state;
    private String content;
    private String[] money;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyDataSetChanged();
    }

    public ReturnAccessoryAdapter(int layoutResId, List<GAccessory> data, String state, String content,String[] money) {
        super(layoutResId, data);
        this.state=state;
        this.content=content;
        this.money=money;
    }
    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        Glide.with(mContext).load("https://img.xigyu.com/Pics/Accessory/"+item.getPhoto1()).into((ImageView) helper.getView(R.id.photo1));
        Glide.with(mContext).load("https://img.xigyu.com/Pics/Accessory/"+item.getPhoto2()).into((ImageView) helper.getView(R.id.photo2));
        Glide.with(mContext).load("https://img.xigyu.com/Pics/Accessory/"+item.getPhoto1()).into((ImageView) helper.getView(R.id.iv_photo1));
        Glide.with(mContext).load("https://img.xigyu.com/Pics/Accessory/"+item.getPhoto2()).into((ImageView) helper.getView(R.id.iv_photo2));
        if ("0".equals(state)){
            helper.getView(R.id.ll_state_y).setVisibility(View.VISIBLE);
            helper.getView(R.id.ll_state_n).setVisibility(View.VISIBLE);
            if ("Y".equals(item.getSendState())){
                helper.getView(R.id.ll_y).setVisibility(View.VISIBLE);
                helper.getView(R.id.ll_n).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.ll_y).setVisibility(View.GONE);
                helper.getView(R.id.ll_n).setVisibility(View.VISIBLE);
            }
            helper.setText(R.id.tv_accessory_name,item.getFAccessoryName()+"  "+item.getQuantity()+"个");
            helper.setText(R.id.tv_accessory_name_n,item.getFAccessoryName()+"  "+item.getQuantity()+"个");

        }else{
            helper.getView(R.id.ll_state_y).setVisibility(View.GONE);
            helper.getView(R.id.ll_state_n).setVisibility(View.GONE);
            helper.getView(R.id.ll_y).setVisibility(View.GONE);
            helper.getView(R.id.ll_n).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_accessory_name,item.getFAccessoryName()+"  ¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
            helper.setText(R.id.tv_accessory_name_n,item.getFAccessoryName()+"  ¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
        }

//
        if ("1".equals(item.getState())){
            helper.setVisible(R.id.tv_sure,true);
            helper.setText(R.id.tv_sure,"审核通过");
        }else if ("-1".equals(item.getState())){
            helper.setText(R.id.tv_sure,"审核拒绝");
            helper.setVisible(R.id.tv_sure,true);
        }else {
            helper.setText(R.id.tv_sure,"审核中");
            helper.setVisible(R.id.tv_sure,true);
        }
        helper.setText(R.id.tv_content,content);
        helper.addOnClickListener(R.id.tv_reject)
                .addOnClickListener(R.id.tv_pass);
        if (money!=null){
            for (int i=0;i<money.length;i++){
                if (money[i].equals(String.valueOf(item.getId()))){
                    helper.setVisible(R.id.tv_reject,true);
                    helper.setVisible(R.id.tv_pass,true);
                    return;
                }else {
                    helper.setGone(R.id.tv_reject,false);
                    helper.setGone(R.id.tv_pass,false);
                }
            }
        }

    }
}
