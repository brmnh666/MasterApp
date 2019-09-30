package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.CommodityBean;
import com.ying.administrator.masterappdemo.widget.AdderView;
import com.ying.administrator.masterappdemo.widget.GlideRoundCropTransform;

import java.util.List;

/*购物车二级列表adapter*/
public class RecyclerCommodityAdapter extends RecyclerView.Adapter<RecyclerCommodityAdapter.MyHolder> {
    private List<CommodityBean> list;
    private Context mContext;

    public RecyclerCommodityAdapter(List<CommodityBean> list, Context context) {
        this.list = list;
        this.mContext=context;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_choose;
       // private final TextView down;
       // private final TextView up;
       // private final EditText et_count;
        private TextView tv_goods_name;
        private TextView tv_goods_type;
        private TextView tv_price;
        private LinearLayout ll_goods_type;
        private AdderView adderView;
        private TextView tv_lose;
        private ImageView iv_goods_picture;

        private LinearLayout ll_goods;

        public CheckBox getCheckBox() {
            return cb_choose;
        }

        public AdderView getAdderView() {
            return adderView;
        }

        public void setAdderView(AdderView adderView) {
            this.adderView = adderView;
        }

        public LinearLayout getLl_goods() {
            return ll_goods;
        }

        public void setLl_goods(LinearLayout ll_goods) {
            this.ll_goods = ll_goods;
        }

        public LinearLayout getLl_goods_type() {
            return ll_goods_type;
        }

        public void setLl_goods_type(LinearLayout ll_goods_type) {
            this.ll_goods_type = ll_goods_type;
        }

        public MyHolder(View itemView) {
            super(itemView);
            cb_choose = (CheckBox) itemView.findViewById(R.id.cb_choose);
            tv_goods_name=itemView.findViewById(R.id.tv_goods_name);
            tv_goods_type=itemView.findViewById(R.id.tv_goods_type);
            tv_price=itemView.findViewById(R.id.tv_price);
            ll_goods_type=itemView.findViewById(R.id.ll_goods_type);
            adderView=itemView.findViewById(R.id.adderview);
            tv_lose=itemView.findViewById(R.id.tv_lose);
            iv_goods_picture=itemView.findViewById(R.id.iv_goods_picture);
            ll_goods=itemView.findViewById(R.id.ll_goods);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_type, parent,false);//解决显示不全
        MyHolder holder = new MyHolder(view);
        return holder;*/
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_shop_type,parent,false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv_goods_name.setText(list.get(position).getName());
        holder.adderView.setValue(Integer.parseInt(list.get(position).getCount()));
        SpannableString spannableString = new SpannableString(String.valueOf(list.get(position).getPrice()));
        if (String.valueOf(list.get(position).getPrice()).contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), String.valueOf(list.get(position).getPrice()).indexOf("."), String.valueOf(list.get(position).getPrice()).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        holder.tv_price.setText(spannableString);


        Glide.with(mContext).load(list.get(position).getImgUrl())
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mContext, 5)))
                .into(holder.iv_goods_picture);

        if (list.get(position).getStatus()==0){//失效
           // holder.cb_choose.setVisibility(View.INVISIBLE);
            holder.tv_lose.setVisibility(View.VISIBLE);
            holder.adderView.setVisibility(View.GONE);
            holder.tv_lose.setVisibility(View.VISIBLE);
            holder.tv_goods_name.setTextColor(Color.RED);
        }



       String type="";
       if (list.get(position).getSize()!=null||list.get(position).getColor()!=null||list.get(position).getVersion()!=null){
           holder.ll_goods_type.setVisibility(View.VISIBLE);
         if (list.get(position).getColor()!=null){
             type=list.get(position).getColor();
            holder.tv_goods_type.setText(type);
         }
         if (list.get(position).getSize()!=null){
             type=type+" "+list.get(position).getSize();
            holder.tv_goods_type.setText(type);
         }
         if (list.get(position).getVersion()!=null){
             type=type+" "+list.get(position).getVersion();
             holder.tv_goods_type.setText(type);
         }



       }


        holder.getCheckBox().setChecked(list.get(position).isIscheck());

        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mCallBack != null) {
                    mCallBack.OnCheckListener(isChecked, position);
                }
            }
        });
        holder.itemView.setId(position);

        holder.getAdderView().setOnValueChangeListene(new AdderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {

                if (mCallBack!=null){
                    mCallBack.OnAddReduceListner(value,position);
                }
            }
        });

        holder.getLl_goods().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack!=null){
                    mCallBack.OnItemClickDetailListner(v,position);
                }

            }
        });

        holder.getLl_goods().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mCallBack!=null){
                    mCallBack.OnItemClickLongListner(v,position);
                }
                return true;
            }
        });

        holder.getLl_goods_type().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack!=null){
                    mCallBack.OnItemClickListner(v,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck {
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        public void OnCheckListener(boolean isChecked, int childpostion);

        public void OnAddReduceListner(int value, int childposition);

        public void OnItemClickDetailListner(View view, int childposition);

        void OnItemClickListner(View view, int childposition);

        void OnItemClickLongListner(View view, int childposition);
    }
}
