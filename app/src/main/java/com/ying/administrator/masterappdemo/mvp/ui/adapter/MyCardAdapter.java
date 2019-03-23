package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.BankCard;

import java.util.List;

public class MyCardAdapter extends BaseQuickAdapter<BankCard, BaseViewHolder> {
    private Context context;
    public MyCardAdapter(int layoutResId, @Nullable List<BankCard> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCard item) {

        switch (item.getPayInfoName()){
            case "光大银行":
                Glide.with(context)
                        .load(R.mipmap.gaungda)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                       helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "广发银行":
                Glide.with(context)
                        .load(R.mipmap.gaungfa)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "中国工商银行":
                Glide.with(context)
                        .load(R.mipmap.gongshang)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "华夏银行":
                Glide.with(context)
                        .load(R.mipmap.huaxia)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;

            case "中国建设银行":
                Glide.with(context)
                        .load(R.mipmap.jianshe)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "中国交通银行":
                Glide.with(context)
                        .load(R.mipmap.jiaotong)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "中国民生银行":
                Glide.with(context)
                        .load(R.mipmap.minsheng)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "宁波银行":
                Glide.with(context)
                        .load(R.mipmap.ningbo)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                        helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "中国农业银行":
                Glide.with(context)
                        .load(R.mipmap.nongye)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                        helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "浦发银行":
                Glide.with(context)
                        .load(R.mipmap.pufa)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                        helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
           case "中国兴业银行":
               Glide.with(context)
                       .load(R.mipmap.xinye)
                       .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                       .into((ImageView) helper.getView(R.id.iv_bank_card));
                       helper.setText(R.id.tv_bank_name,item.getPayInfoName());
           break;
            case "中国邮政储蓄":
                Glide.with(context)
                        .load(R.mipmap.youzheng)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                     helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "中国招商银行":
                Glide.with(context)
                        .load(R.mipmap.zhaoshan)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());

                break;
            case "浙商银行":
                Glide.with(context)
                        .load(R.mipmap.zheshang)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());

                break;
            case "中国银行":

                Glide.with(context)
                        .load(R.mipmap.zhongguo)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
            case "中国中信银行":
                Glide.with(context)
                        .load(R.mipmap.zhongxin)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into((ImageView) helper.getView(R.id.iv_bank_card));
                helper.setText(R.id.tv_bank_name,item.getPayInfoName());
                break;
         default:
             break;



        }

        int length = item.getPayNo().length();
        String bankNo;
        if (length > 4) {
            String startNum = item.getPayNo().substring(0, 4);
            String endNum = item.getPayNo().substring(length - 4, length);
            bankNo = startNum + " **** **** " + endNum;
            helper.setText(R.id.tv_card_num,bankNo);
        }




    }
}
