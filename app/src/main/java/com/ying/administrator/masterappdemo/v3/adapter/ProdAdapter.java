package com.ying.administrator.masterappdemo.v3.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.AccessoriesNoEvent;
import com.ying.administrator.masterappdemo.entity.WorkOrder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ProdAdapter extends BaseQuickAdapter<WorkOrder.OrderProductModelsBean, BaseViewHolder> {


    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProdAdapter(int layoutResId, @Nullable List<WorkOrder.OrderProductModelsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.OrderProductModelsBean item) {
        String name=item.getSubCategoryName()+"（编号："+item.getOrderProdcutID()+")";
        helper.setText(R.id.tv_product_name,name);
        if(item.getAccessoryData()!=null){
            if (item.getAccessoryData().size()>0){
                helper.setGone(R.id.ll_acc,true);
                RecyclerView rv=helper.getView(R.id.rv_acc);
                rv.setLayoutManager(new LinearLayoutManager(mContext));
                AccNoAdapter adapter = new AccNoAdapter(R.layout.acc_no_item,item.getAccessoryData());
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        EventBus.getDefault().post(new AccessoriesNoEvent((AccNoAdapter) adapter,position));
                    }
                });
            }else{
                helper.setGone(R.id.ll_acc,false);
            }
        }else{
            helper.setGone(R.id.ll_acc,false);
        }

    }
}
