package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.FAccessory;

import java.util.List;

/*预接单的添加配置*/
public class Pre_order_Add_Ac_Adapter2 extends BaseQuickAdapter<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean, BaseViewHolder>  {
    private String AccessoryState;
    private int select_state;
    public Pre_order_Add_Ac_Adapter2(int layoutResId, @Nullable List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> data, String AccessoryState, Integer select_state) {
        super(layoutResId, data);
        this.AccessoryState=AccessoryState;
        this.select_state=select_state;
    }

    @Override
    protected void convert(BaseViewHolder helper, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean item) {
        helper.setText(R.id.tv_accessories_name,item.getFAccessoryName());
        if ("0".equals(AccessoryState)){
            helper.setText(R.id.tv_accessories_number,"数量："+item.getQuantity());
        }else {
//            helper.setText(R.id.tv_accessories_number,"¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
            helper.setText(R.id.tv_accessories_number,"数量："+item.getQuantity());

        }
//        Glide.with(mContext).load("https://img.xigyu.com/Pics/Accessory/"+item.getPhoto1()).into((ImageView) helper.getView(R.id.photo1));
//        Glide.with(mContext).load("https://img.xigyu.com/Pics/Accessory/"+item.getPhoto2()).into((ImageView) helper.getView(R.id.photo2));
        helper.addOnClickListener(R.id.iv_accessories_delete);


    }



   /* @Override
    protected void convert(BaseViewHolder helper, FAccessory item) {
     helper.setText(R.id.tv_accessories_name,item.);
     helper.setText(R.id.tv_accessories_number,item.getQuantity());
     helper.addOnClickListener(R.id.iv_accessories_delete);
    }*/
}
