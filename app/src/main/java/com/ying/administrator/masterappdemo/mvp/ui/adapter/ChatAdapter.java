package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Chat;
import com.ying.administrator.masterappdemo.entity.SubAccount;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseQuickAdapter<Chat, BaseViewHolder> {

    public ChatAdapter(int layoutResId, @Nullable List<Chat> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Chat item) {
        helper.setText(R.id.tv_question, item.getQuestion());
    }
}
