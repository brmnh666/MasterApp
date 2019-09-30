package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.StoreBean;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {
    private List<StoreBean> list;
    private Context mcontext;

    public CartAdapter(List<StoreBean> list, Context context){
        this.list = list;
        this.mcontext=context;
    }


    public static class MyHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView ShopName;
        private CheckBox checkBox;
        private RecyclerCommodityAdapter adapter;
        private TextView tv_coupon;


        public CheckBox getCheckBox() {
            return checkBox;
        }


        public MyHolder(View view) {
            super(view);
            ShopName=view.findViewById(R.id.tv_shop_name);
            recyclerView = view.findViewById(R.id.rv_shop_type);
            checkBox =  view.findViewById(R.id.cb_choose_all);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            tv_coupon=view.findViewById(R.id.tv_coupon);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    /*    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,null);//解决嵌套显示条目不全
        MyHolder holder = new MyHolder(view);
        return holder;*/
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_cart, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.ShopName.setText(list.get(position).getShopName());
        holder.adapter = new RecyclerCommodityAdapter(list.get(position).getList(),mcontext);
        holder.recyclerView.setAdapter(holder.adapter);

        holder.tv_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (mCallBack!=null){
                  mCallBack.OnCheckCoupListner(position);
              }
            }
        });

        holder.ShopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack!=null){
                    mCallBack.OnShopNameListner(position);
                }
            }
        });

        holder.getCheckBox().setChecked(list.get(position).isIscheck());
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将店铺的checkbox的点击变化事件进行回调
                if (mCallBack!=null){
                    mCallBack.OnCheckListener(isChecked,position);
                }
            }
        });

        //实现第二层RecyclerView的回调接口
        holder.adapter.setCallBack(new RecyclerCommodityAdapter.allCheck() {
            @Override
            public void OnCheckListener(boolean isChecked, int childpostion) {
                //将店铺商品的checkbox的点击变化事件进行回调
                if (mCallBack!=null){
                    mCallBack.OnItemCheckListener(isChecked,position,childpostion);
                }
            }

            @Override
            public void OnAddReduceListner(int value, int childposition) {
                if (mCallBack!=null){
                    mCallBack.OnItemAddReduceListener(value,position,childposition);
                }

            }

            @Override
            public void OnItemClickDetailListner(View view, int childposition) {
                if (mCallBack!=null){
                    mCallBack.OnItemClickDetailListner(view,position,childposition);
                }

            }

            @Override
            public void OnItemClickListner(View view, int childposition) {
                if (mCallBack!=null){
                    mCallBack.OnItemClickListner(view,position,childposition);
                }

            }

            @Override
            public void OnItemClickLongListner(View view, int childposition) {
                if (mCallBack!=null){
                    mCallBack.OnItemClickLongListner(view,position,childposition);
                }
            }
        });
        holder.itemView.setTag(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck{
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        public void OnCheckListener(boolean isSelected, int position);
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        public void OnItemCheckListener(boolean isSelected, int parentposition, int chaildposition);

        public void OnItemAddReduceListener(int value, int parentposition, int chaildposition);

        public void OnItemClickDetailListner(View view, int parentposition, int chaildposition);

        public void OnItemClickListner(View view, int parentposition, int chaildposition);
        public void OnItemClickLongListner(View view, int parentposition, int chaildposition);
        public void OnCheckCoupListner(int parentposition);

        public void OnShopNameListner(int parentposition);
    }


}
