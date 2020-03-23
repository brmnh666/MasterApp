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
import com.ying.administrator.masterappdemo.entity.Bank;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;

import java.util.List;

/*选择子账号的adapter*/
public class Redeploy_Adapter extends BaseQuickAdapter<SubUserInfo.SubUserInfoDean,BaseViewHolder> {
private Context context;

    public Redeploy_Adapter(int layoutResId, @Nullable List<SubUserInfo.SubUserInfoDean> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }



    @Override
    protected void convert(BaseViewHolder helper, SubUserInfo.SubUserInfoDean item) {

        /*设置头像*/
        if (item.getAvator()==null){
         /*默认没有头像*/
          Glide.with(context)
                    .load(R.drawable.avatar)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into((ImageView) helper.getView(R.id.subaccount_head));
        }else {
            Glide.with(context)
                    .load(Config.HEAD_URL+item.getAvator())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into((ImageView) helper.getView(R.id.subaccount_head));
        }

        /*设置姓名*/
        if (item.getTrueName()==null){
            helper.setText(R.id.tv_subaccount_name,"未实名认证");
        }else {
            helper.setText(R.id.tv_subaccount_name,item.getTrueName());
        }
        /*设置手机号*/

        if (item.getPhone()==null){
            helper.setText(R.id.tv_subaccount_phone,"");
        }else {
            helper.setText(R.id.tv_subaccount_phone,item.getPhone());
        }

        if (item.isIscheck()==false){//未选中
          helper.setVisible(R.id.img_redeploy_unselect,true);
          helper.setVisible(R.id.img_redeploy_select,false);

        }else {//选中
            helper.setVisible(R.id.img_redeploy_unselect,false);
            helper.setVisible(R.id.img_redeploy_select,true);

        }


        //helper.addOnClickListener(R.id.img_redeploy_unselect);
        //helper.addOnClickListener(R.id.img_redeploy_select);
       helper.addOnClickListener(R.id.rl_item_redeploy);

    }
}
