package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Message;

import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {

    public MessageAdapter(int layoutResId, @Nullable List<Message> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        helper.setText(R.id.tv_order_message,"工单消息:"+item.getContent());
        StringBuilder stringBuilder = new StringBuilder(item.getNowtime());
        String time = "" + stringBuilder.replace(10, 11, " "); //替换"T"为" "
        helper.setText(R.id.tv_order_time,"时间:"+time);

        if ("2".equals(item.getIsLook())){//已读
            helper.setGone(R.id.iv_new,false);
        }else if ("1".equals(item.getIsLook())){
            helper.setGone(R.id.iv_new,true);
        }else{
            helper.setGone(R.id.iv_new,false);
        }
        helper.addOnClickListener(R.id.ll_order_message);
    }
}
