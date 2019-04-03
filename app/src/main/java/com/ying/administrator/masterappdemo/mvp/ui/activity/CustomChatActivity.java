package com.ying.administrator.masterappdemo.mvp.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paradigm.botkit.ChatActivity;
import com.paradigm.botlib.Message;
import com.ying.administrator.masterappdemo.widget.MyTextMessageItemProvider;

public class CustomChatActivity extends ChatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        // 为ContentTypeText类型消息注册自定义视图生成器
        getMessageAdapter().setMessageItemProvider(Message.ContentTypeText, new MyTextMessageItemProvider());
    }
}
