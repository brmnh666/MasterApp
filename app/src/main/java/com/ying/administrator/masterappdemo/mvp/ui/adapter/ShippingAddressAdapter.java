package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Address;
import com.ying.administrator.masterappdemo.entity.AddressList;

import java.util.List;

public class ShippingAddressAdapter extends BaseQuickAdapter<AddressList, BaseViewHolder> {
    public ShippingAddressAdapter(int layoutResId, List<AddressList> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, AddressList item) {
        helper.setText(R.id.tv_name_first,item.getUserName().substring(0,1));
        helper.setText(R.id.tv_name,item.getUserName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.setText(R.id.tv_address,item.getProvince()+item.getCity()+ item.getArea()+item.getDistrict()+item.getAddress());
        helper.addOnClickListener(R.id.tv_edit);
        if ("1".equals(item.getIsDefault())){
            helper.setGone(R.id.tv_default,true);
        }else{
            helper.setGone(R.id.tv_default,false);
        }
    }
}
