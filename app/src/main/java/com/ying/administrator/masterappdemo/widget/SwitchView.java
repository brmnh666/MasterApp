package com.ying.administrator.masterappdemo.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewSwitcher;

import com.ying.administrator.masterappdemo.R;

public class SwitchView extends ViewSwitcher implements ViewSwitcher.ViewFactory {
    private int layoutId;
    private ViewBuilder viewInterface;
    private boolean enable = true;

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View makeView() {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //加载新界面
            if (enable) {
                viewInterface.initView(getNextView());
                setInAnimation(getContext(), R.anim.slide_in);
                setOutAnimation(getContext(), R.anim.slide_out);
                showNext();
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public void initView(int layoutId, ViewBuilder viewInterface) {
        this.layoutId = layoutId;
        this.viewInterface = viewInterface;
        //设置ViewFactory后会调用makeView()
        setFactory(this);
        //发送启动消息
        handler.removeMessages(0);
        handler.sendEmptyMessage(0);
    }

    public void enable(boolean enable) {
        this.enable = enable;
        if (enable)
            handler.sendEmptyMessage(0);
    }

    public interface ViewBuilder {
        void initView(View view);
    }
}
