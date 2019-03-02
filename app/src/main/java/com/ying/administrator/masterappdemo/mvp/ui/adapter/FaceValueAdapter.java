package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.FaceValue;

import java.util.List;

public class FaceValueAdapter extends BaseQuickAdapter<FaceValue,BaseViewHolder> {
    public FaceValueAdapter(int layoutResId, List<FaceValue> data) {
        super(layoutResId, data);
    }
    public  void setSelect(List<FaceValue> data,int position){
        for (int i = 0; i <data.size() ; i++) {
            if (i==position){
                data.get(position).setSelect(true);
            }else{
                data.get(position).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, FaceValue item) {
        TextView tv_initials=helper.getView(R.id.tv_initials);
        tv_initials.setSelected(item.isSelect());
        helper.setText(R.id.tv_initials,item.getValue());
        // 加载网络图片
        /*helper.setText(R.id.name,item.getUsername());
        helper.setText(R.id.phone,item.getTelphone());
        helper.setText(R.id.address,item.getString());
        helper.addOnClickListener(R.id.edit_ll);
        helper.addOnClickListener(R.id.delete_ll);
        helper.addOnClickListener(R.id.default_ll);
        if ("1".equals(item.getIs_default())){
            helper.setChecked(R.id.check,true);
        }else{
            helper.setChecked(R.id.check,false);
        }*/
    }
}
