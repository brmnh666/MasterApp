package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Order;
import com.ying.administrator.masterappdemo.mvp.ui.activity.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class GoodsorderListAdapter extends BaseQuickAdapter<Order.OrdersBean, BaseViewHolder> {
    String name;
    private OrderListAdapter2 orderListAdapter2;
    private Intent intent;

    public GoodsorderListAdapter(int layoutResId, @Nullable List<Order.OrdersBean> data, String name) {
        super(layoutResId, data);
        this.name=name;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Order.OrdersBean item) {
        List<Order.itemInfoBean> list=new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add(new Order.itemInfoBean());
        }
        orderListAdapter2 = new OrderListAdapter2(R.layout.item_goods_order_list,list);
//        orderListAdapter2 = new OrderListAdapter2(R.layout.item_goods_order_list,item.getItemInfo());
        helper.setText(R.id.tv_store_name,item.getShopname())
         .setText(R.id.tv_goods_number,"共"+item.getProductCount()+"件商品")
         .setText(R.id.tv_goods_price,"合计："+item.getOrderTotalAmount());
        helper.setText(R.id.tv_trading_status,item.getStatus());
        helper.addOnClickListener(R.id.tv_delete2);//删除订单
        helper.addOnClickListener(R.id.tv_delete);//删除订单
        helper.addOnClickListener(R.id.tv_delete_order);//取消订单
        helper.addOnClickListener(R.id.tv_confirm_receipt);//确认收货
        helper.addOnClickListener(R.id.tv_payment);//付款
        helper.addOnClickListener(R.id.tv_friend_pay);//朋友代付
        helper.addOnClickListener(R.id.tv_buy_again);//再次购买
        helper.addOnClickListener(R.id.tv_buy);//再次购买
        helper.addOnClickListener(R.id.tv_extended_receipt);//延长收货
        helper.addOnClickListener(R.id.tv_see_logistics);//查看物流
        helper.addOnClickListener(R.id.tv_view_logistics);//查看物流
        helper.addOnClickListener(R.id.tv_logistics);//查看物流
        helper.addOnClickListener(R.id.tv_evaluation);//评价
        helper.addOnClickListener(R.id.tv_change_address);//修改地址

//        helper.addOnClickListener(R.id.tv_sendorder);//发单
        RecyclerView rv=helper.getView(R.id.rv_order_list);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(orderListAdapter2);

        if (item.getOrderStatus()==5||item.getOrderStatus()==7){//已完成||未评价

            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,true);
            helper.setVisible(R.id.ll_to_be_delivered,false);
            helper.setVisible(R.id.ll_close,false);
        }

        if (item.getOrderStatus()==1){//待付款
            helper.setVisible(R.id.ll_pending_payment,true);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,false);
            helper.setVisible(R.id.ll_close,false);
        }

        if (item.getOrderStatus()==2){//待发货
            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,true);
            helper.setVisible(R.id.ll_close,false);
        }

        if (item.getOrderStatus()==3||item.getOrderStatus()==6){//待收货带自提
            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,true);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,false);
            helper.setVisible(R.id.ll_close,false);
        }
        if (item.getOrderStatus()==4){//已关闭，被取消的订单
            helper.setVisible(R.id.ll_pending_payment,false);
            helper.setVisible(R.id.ll_pending_receipt,false);
            helper.setVisible(R.id.ll_all_orders,false);
            helper.setVisible(R.id.ll_to_be_delivered,false);
            helper.setVisible(R.id.ll_close,true);
        }


        orderListAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_goods:
                        intent =new Intent(mContext, OrderDetailActivity.class);
//                        intent.putExtra("orderId",item.getId());
                        startActivity(intent);
                        break;
                    case R.id.tv_apply_refund:
//                        intent =new Intent(mContext, AfterSalesTypeActivity.class);
//                        intent.putExtra("storeName", item.getShopname());
//                        intent.putExtra("product", item.getItemInfo().get(position));
//                        intent.putExtra("order", item);
//                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
