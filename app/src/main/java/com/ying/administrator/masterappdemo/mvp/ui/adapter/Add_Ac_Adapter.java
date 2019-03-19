package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.entity.FAccessory;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.widget.adderView;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;


/*dialog添加配件 的adpter*/
public class Add_Ac_Adapter extends BaseQuickAdapter<Accessory, BaseViewHolder> {
    private EditText et_amount;
    private LinearLayout ll_price;
    private int state;
    // AddSubUtils list_item_utils;

    public Add_Ac_Adapter(int layoutResId, @Nullable List<Accessory> data, int state) {
        super(layoutResId, data);
        this.state=state;
    }


    @Override
    protected void convert(BaseViewHolder helper, Accessory item) {

        /* helper.setIsRecyclable(false);*/
        helper.setText(R.id.tv_accessory_name,item.getAccessoryName());

     if (item.isIscheck()){ //是被选中的状态  红钩显示  加减器显示
           helper.setVisible(R.id.img_ac_select,true);
           helper.setVisible(R.id.img_ac_unselect,false);
           helper.setVisible(R.id.adderView,true);
           adderView adderView = helper.getView(R.id.adderView);
           adderView.setValue(item.getCheckedcount());
       }else { //未选中状态
           helper.setVisible(R.id.img_ac_select,false);
           helper.setVisible(R.id.img_ac_unselect,true);
           helper.setVisible(R.id.adderView,false);
       }

        ll_price =helper.getView(R.id.ll_price);
        switch(state){
            case 0:
                ll_price.setVisibility(View.GONE);
                break;
            case 1:
                ll_price.setVisibility(View.VISIBLE);
                break;
            case 2:
                ll_price.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        helper.addOnClickListener(R.id.tv_accessory_name);
        helper.addOnClickListener(R.id.img_ac_unselect);
        helper.addOnClickListener(R.id.img_ac_select);
        helper.addOnClickListener(R.id.et_amount);
        /*TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setAccessoryPrice(Double.parseDouble(et_amount.getText().toString().trim()));
            }
        };
        et_amount =helper.getView(R.id.et_amount);
        et_amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et_amount.requestFocus();
                return false;
            }
        });
        if (et_amount.getTag() instanceof TextWatcher){
            et_amount.removeTextChangedListener((TextWatcher) et_amount.getTag());
        }
        et_amount.addTextChangedListener(textWatcher);
        et_amount.setTag(textWatcher);*/
    }

}

