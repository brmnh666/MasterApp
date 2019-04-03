package com.ying.administrator.masterappdemo.widget;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.paradigm.botkit.message.MessageItemProvider;
import com.paradigm.botlib.Message;
import com.paradigm.botlib.MessageContentText;
import com.ying.administrator.masterappdemo.R;

/*智能客服*/
/*视图生成器类*/
public class MyTextMessageItemProvider extends MessageItemProvider {
    @Override
    public View createContentView(Context context) {
       View conetentView= LayoutInflater.from(context).inflate(R.layout.my_item_message_text, null);
        return conetentView;
    }

    @Override
    public void bindContentView(View v, Message message) {
        TextView contentView = (TextView)v;
        MessageContentText content = (MessageContentText) message.getContent();
        contentView.setText(content.getText());
    }


}
