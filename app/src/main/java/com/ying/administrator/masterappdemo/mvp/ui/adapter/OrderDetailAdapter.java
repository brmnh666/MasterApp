package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.OrderDetail;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.List;

public class OrderDetailAdapter extends BaseQuickAdapter<OrderDetail.OrderItemBean, BaseViewHolder> {
    private String status;
    public OrderDetailAdapter(int layoutResId, @Nullable List<OrderDetail.OrderItemBean> data, String status) {
        super(layoutResId, data);
        this.status=status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetail.OrderItemBean item) {
//        helper.setText(R.id.tv_goods_name,item.getProductName())
//                .setText(R.id.tv_goods_type,item.getColor()+item.getSize()+item.getVersion())
//                .setText(R.id.tv_good_money,"¥"+item.getPrice())
//                .setText(R.id.tv_goods_number,"×"+item.getCount());
//        ImageView icon = helper.getView(R.id.iv_goods_picture);
//        GlideUtil.loadImageViewLoding(mContext,item.getProductImage(),icon,R.drawable.avatar,R.drawable.avatar);
//
//        switch(status){
//            case "1"://待付款
//            case "4"://已关闭
//                helper.setGone(R.id.tv_apply_refund,false);
//                break;
//            case "2"://待消费
//            case "3"://待收货
//            case "5"://已完成
//            case "6"://待自提
//            case "7"://未评价
//                helper.setGone(R.id.tv_apply_refund,true);
//
//                if (!item.isIsCanRefund()){
//                    helper.getView(R.id.tv_apply_refund).setClickable(false);
//                    helper.setText(R.id.tv_apply_refund,"售后处理中..");
//                }else {
                    helper.addOnClickListener(R.id.tv_apply_refund);
//                }
//
//
//                break;
//            default:
//                helper.setGone(R.id.tv_apply_refund,false);
//                break;
//        }
//

    }
}
