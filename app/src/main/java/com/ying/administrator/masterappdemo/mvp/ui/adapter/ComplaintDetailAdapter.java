package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.ComplaintList;
import com.ying.administrator.masterappdemo.util.DensityUtil;
import com.ying.administrator.masterappdemo.viewholder.LayoutParamsViewHolder;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class ComplaintDetailAdapter extends BaseQuickAdapter<ComplaintList, LayoutParamsViewHolder> {
    public ComplaintDetailAdapter(int layoutResId, @Nullable List<ComplaintList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, ComplaintList item) {
        String time=item.getCreateTime().replace("T"," ");
        helper.setText(R.id.tv_time,"投诉账号："+item.getUserID())
                .setText(R.id.tv_date,time)
                .setText(R.id.tv_status,item.getContent());
        if (item.getPhoto()==null){
            helper.setGone(R.id.iv_image,false);
        }else{
            helper.setGone(R.id.iv_image,true);
        }
        helper.addOnClickListener(R.id.iv_image);
        GlideUtil.loadImageViewLoding(mContext, Config.ComPlaint_URL +item.getPhoto(), (ImageView) helper.getView(R.id.iv_image), R.drawable.image_loading,R.drawable.image_loading);
        int position=helper.getAdapterPosition();
        if (position==0){
            helper.setImageResource(R.id.iv_status,R.drawable.blue_bot);
            RelativeLayout.LayoutParams pointParams=new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext,15),DensityUtil.dp2px(mContext, 15));
            pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,pointParams);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);
        }else if(position==getItemCount()-1){
            helper.setImageResource(R.id.iv_status,R.drawable.gray_bot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.ABOVE, R.id.iv_status);//让直线置于圆点上面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);

        }else{
            helper.setImageResource(R.id.iv_status,R.drawable.gray_bot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);
        }
    }
}
