package com.ying.administrator.masterappdemo.v3.adapter;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Track;
import com.ying.administrator.masterappdemo.util.DensityUtil;
import com.ying.administrator.masterappdemo.viewholder.LayoutParamsViewHolder;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class TrackAdapter extends BaseQuickAdapter<Track, LayoutParamsViewHolder> {
    public TrackAdapter(int layoutResId, @Nullable List<Track> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, Track item) {
        if(item.getUserID().contains(":")){
            String type = item.getUserID().substring(0, item.getUserID().indexOf(":"));
            String name = item.getUserID().substring(item.getUserID().lastIndexOf(":") + 1);
            if ("工厂".equals(type)) {
                helper.setText(R.id.tv_time, type);
            } else if ("师傅".equals(type)) {
                helper.setText(R.id.tv_time, name);
            } else {
                helper.setText(R.id.tv_time, type);
            }
        }else{
            helper.setText(R.id.tv_time, item.getUserID());
        }
        StringBuilder stringBuilder = new StringBuilder(item.getCreateDate());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_date, time)
                .setText(R.id.tv_status, item.getStateName());
        int position = helper.getAdapterPosition();
        if (position == 0) {
            helper.setImageResource(R.id.iv_status, R.drawable.blue_bot);
            RelativeLayout.LayoutParams pointParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status, pointParams);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line, lineParams);
        } else if (position == getItemCount() - 1) {
            helper.setImageResource(R.id.iv_status, R.drawable.gray_bot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status, lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.ABOVE, R.id.iv_status);//让直线置于圆点上面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line, lineParams);

        } else {
            helper.setImageResource(R.id.iv_status, R.drawable.gray_bot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status, lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line, lineParams);
        }
    }
}
