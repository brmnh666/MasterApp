package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.entity.SubAccount;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;

import java.util.List;

public class SubAccountAdapter extends BaseQuickAdapter<SubUserInfo.SubUserInfoDean,BaseViewHolder> {
    private Context context;
private ImageView img_avatar;

    public SubAccountAdapter(int layoutResId, @Nullable List<SubUserInfo.SubUserInfoDean> data, Context context) {
        super(layoutResId, data);
         this.context=context;
      }

    @Override
    protected void convert(BaseViewHolder helper, SubUserInfo.SubUserInfoDean item) {
        img_avatar=helper.getView(R.id.img_avatar);
         if (item.getAvator()==null){

             RequestOptions options=new RequestOptions().error(R.mipmap.ic_launcher).bitmapTransform(new CircleCrop());
             Glide.with(context)
                     .load(Config.HEAD_URL+item.getAvator())
                     .apply(options)
                     .into(img_avatar);

         }else {
             helper.setVisible(R.id.img_avatar2,false);
             helper.setVisible(R.id.img_avatar3,false);

             Glide.with(context)
                     .load(Config.HEAD_URL+item.getAvator())
                     .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                     .into(img_avatar);
         }

        if (item.getTrueName()==null){
            helper.setText(R.id.tv_name,"暂未实名认证");
        }else {
            helper.setText(R.id.tv_name,item.getTrueName());
        }

        if (item.getServiceTotalMoney()==null){
            helper.setText(R.id.tv_completion_amount,"0");
        }else {

            helper.setText(R.id.tv_completion_amount,item.getServiceTotalMoney());
        }

        if (item.getServiceTotalOrderNum()==null){
            helper.setText(R.id.tv_amount_completed,"0");
        }else {
            helper.setText(R.id.tv_amount_completed,item.getServiceComplaintNum());
        }

        if (item.getServiceComplaintNum()==null){
            helper.setText(R.id.tv_being_complain,"0");
        }else {
            helper.setText(R.id.tv_being_complain,item.getServiceComplaintNum());
        }

        helper.addOnClickListener(R.id.tv_close_account).addOnClickListener(R.id.img_tv_close_account);//注销账号

    }
}
