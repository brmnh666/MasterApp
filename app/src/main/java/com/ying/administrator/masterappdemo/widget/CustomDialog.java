package com.ying.administrator.masterappdemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;

public class CustomDialog extends Dialog {
    private TextView yes;
    private TextView no;
    private TextView titleTv;
    private TextView messageTv;
    private String titleStr;
    private String messageStr;
    private String yesStr, noStr;
    private ImageView img_authentication_cancel;

    /*  -------------------------------- 接口监听 -------------------------------------  */

    private onNoOnclickListener noOnclickListener;
    private onYesOnclickListener yesOnclickListener;

    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }

    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }



    /*  ---------------------------------- 构造方法 -------------------------------------  */
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context,R.style.MyDialog);
    }

    protected CustomDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);//自定义布局

        //按空白处不能取消动画
       setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */

    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });

        img_authentication_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }



    }
    /**
     * 初始化界面控件
     */
    private void initView() {
        yes =  findViewById(R.id.yes);
        no = findViewById(R.id.no);
        titleTv = findViewById(R.id.title);
        messageTv =  findViewById(R.id.message);
        img_authentication_cancel=findViewById(R.id.img_authentication_cancel);

    }
    /*  ---------------------------------- set方法 传值-------------------------------------  */
//为外界设置一些public 公开的方法，来向自定义的dialog传递值
    public void setTitle(String title) {
        titleStr = title;
    }

    public void setMessage(String message) {
        messageStr = message;
    }


}
