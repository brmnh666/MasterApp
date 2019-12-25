package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.WorkOrder;
import com.ying.administrator.masterappdemo.mvp.ui.activity.PhotoViewActivity;
import com.ying.administrator.masterappdemo.util.DensityUtil;
import com.ying.administrator.masterappdemo.viewholder.LayoutParamsViewHolder;
import com.ying.administrator.masterappdemo.widget.GlideUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaveMessageAdapter extends BaseQuickAdapter<WorkOrder.LeavemessageListBean, LayoutParamsViewHolder> {

    private RecyclerView rv_img;
    private String photo;

    private ImgPicAdapter picAdapter;

    public LeaveMessageAdapter(int layoutResId, @Nullable List<WorkOrder.LeavemessageListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, WorkOrder.LeavemessageListBean item) {
        StringBuilder stringBuilder = new StringBuilder(item.getCreateDate());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_status,item.getContent())
                .setText(R.id.tv_date,time)
                .setText(R.id.tv_time,item.getUserName());
        rv_img =helper.getView(R.id.rv_img);
        ArrayList<String> imglist=new ArrayList<>();
        if(item.getPhoto()==null){
            rv_img.setVisibility(View.GONE);
        }else{
            rv_img.setVisibility(View.VISIBLE);
            photo=item.getPhoto();
            if (photo.contains(",")){
                String[] s=photo.split(",");
                imglist.addAll(Arrays.asList(s));
            }else{
                imglist.add(photo);
            }
        }
        picAdapter = new ImgPicAdapter(R.layout.item_picture, imglist);
        rv_img.setLayoutManager(new GridLayoutManager(mContext,5));
        rv_img.setAdapter(picAdapter);
        picAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putExtra("PhotoUrl", Config.Leave_Message_URL+adapter.getData().get(position));
                mContext.startActivity(intent);
            }
        });

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
