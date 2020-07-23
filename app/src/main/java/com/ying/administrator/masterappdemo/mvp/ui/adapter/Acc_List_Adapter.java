package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;

import java.util.List;


/*dialog添加配件 的adpter*/
public class Acc_List_Adapter extends BaseQuickAdapter<Accessory, BaseViewHolder> {

    private OnItemEditTextChangedListener mListener;
    private String discountStr;

    public void setListener(OnItemEditTextChangedListener listener) {
        mListener = listener;
    }

    public Acc_List_Adapter(int layoutResId, @Nullable List<Accessory> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final Accessory item) {
        helper.setText(R.id.tv_name,item.getAccessoryName());
        helper.setText(R.id.tv_count,item.getCount()+"");
        helper.setText(R.id.et_price,item.getAccessoryPrice()==0?"":item.getAccessoryPrice()+"");
        if (item.getCount()==0){
            helper.setGone(R.id.btn_reduce,false);
            helper.setGone(R.id.tv_count,false);
            helper.setGone(R.id.et_price,false);
        }else{
            helper.setGone(R.id.btn_reduce,true);
            helper.setGone(R.id.tv_count,true);
            if (item.getCj_or_zg()==0){
                helper.setGone(R.id.et_price,false);
            }else{
                helper.setGone(R.id.et_price,true);
            }
        }
        helper.addOnClickListener(R.id.btn_reduce);
        helper.addOnClickListener(R.id.btn_add);
        final EditText et_price=helper.getView(R.id.et_price);
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

