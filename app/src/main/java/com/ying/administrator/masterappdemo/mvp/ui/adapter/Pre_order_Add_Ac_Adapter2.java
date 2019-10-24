package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.baidu.ocr.sdk.utils.LogUtil;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.FAccessory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*预接单的添加配置*/
public class Pre_order_Add_Ac_Adapter2 extends BaseQuickAdapter<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean, BaseViewHolder>  {
    private String AccessoryState;
    private String select_state;
    public List<String> contents = new ArrayList<>();
    public Pre_order_Add_Ac_Adapter2(int layoutResId, @Nullable List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> data, String AccessoryState, String select_state) {
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
        if ("0".equals(select_state)){
            helper.getView(R.id.et_price).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.et_price).setVisibility(View.VISIBLE);
        }

        EditText et_price=helper.getView(R.id.et_price);
        et_price.addTextChangedListener(new MyTextChangedListener(helper,contents));

    }


    public class MyTextChangedListener implements TextWatcher {

        public BaseViewHolder holder;
        public List<String> contents;

        public MyTextChangedListener(BaseViewHolder holder,List<String> contents){
            this.holder = holder;
            this.contents = contents;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(holder != null && contents != null){
                int adapterPosition = holder.getAdapterPosition();
                LogUtil.d(TAG,"adapterPosition==================="+adapterPosition);
//                contents.put(editable.toString());
                contents.add(editable.toString());
            }
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }
}
