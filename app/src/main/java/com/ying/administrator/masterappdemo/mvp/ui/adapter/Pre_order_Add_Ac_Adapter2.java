package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.FAccessory;

import java.util.ArrayList;
import java.util.List;

/*预接单的添加配置*/
public class Pre_order_Add_Ac_Adapter2 extends BaseQuickAdapter<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean, BaseViewHolder>  {
    private OnItemEditTextChangedListener mListener;
    private String discountStr;

    public void setListener(OnItemEditTextChangedListener listener) {
        mListener = listener;
    }
    private String AccessoryState;
    private String select_state;
    public List<String> contents = new ArrayList<>();
    public Pre_order_Add_Ac_Adapter2(int layoutResId, @Nullable List<FAccessory.OrderAccessoryStrBean.OrderAccessoryBean> data, String AccessoryState, String select_state) {
        super(layoutResId, data);
        this.AccessoryState=AccessoryState;
        this.select_state=select_state;
    }

    @Override
    protected void convert(final BaseViewHolder helper, FAccessory.OrderAccessoryStrBean.OrderAccessoryBean item) {
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

        final EditText et_price=helper.getView(R.id.et_price);
        if (item.getPrice()==0){
            et_price.setText("");
        }else{
            et_price.setText(item.getPrice()+"");
        }
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                discountStr = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = s.toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    if (trim.contains(".")) {
                        String[] split = trim.split("\\.");
                        if (split.length > 1) {
                            String s1 = split[1];
                            if (!TextUtils.isEmpty(s1)) {
                                if (s1.length() == 2) {
                                    et_price.setText(discountStr);
                                    try {
                                        String trim1 = et_price.getText().toString().trim();
                                        et_price.setSelection(trim1.length());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return;
                                }
                            }
                        }
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                String value;
                int len = text.length();
                if (len > 1 && text.startsWith("0")) {
                    value = s.replace(0, 1, "").toString();
                }else if(text.startsWith(".")){
                    value = s.replace(0, 1, "0").toString();
                }else if (text.endsWith(".")){
                    value=s.replace(len-1, len, "").toString();
                }else if(len==0){
                    value="0";
                }else{
                    value=text;
                }
                mListener.onEditTextAfterTextChanged(value, helper.getLayoutPosition());
            }
        });

    }
    public interface OnItemEditTextChangedListener {
        void onEditTextAfterTextChanged(String s, int position);
    }
}
