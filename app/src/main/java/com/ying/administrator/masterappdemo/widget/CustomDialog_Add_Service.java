package com.ying.administrator.masterappdemo.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Ac_Adapter;

import java.util.List;

/*添加配件dialog*/
public class CustomDialog_Add_Service extends AlertDialog {
    private TextView yes;
    private TextView titleTv;
    private String titleStr;
    private String yesStr;
    private Context context;
    /*  -------------------------------- 接口监听 -------------------------------------  */

    private onYesOnclickListener yesOnclickListener;

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public interface onYesOnclickListener {
        void onYesClick();
    }


    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }



    /*  ---------------------------------- 构造方法 -------------------------------------  */
    public CustomDialog_Add_Service(Context context) {
        super(context);
        this.context=context;
    }

    public CustomDialog_Add_Service(Context context, int themeResId) {
        super(context,R.style.MyDialog);
    }

    protected CustomDialog_Add_Service(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog_add_service);//自定义布局

        //按空白处不能取消动画
         setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
        //加载弹出配件列表的数据
        //initAccessory();


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



    }

    /**
     * 初始化界面控件
     */
    private void initView(){
        yes = findViewById(R.id.tv_add_service_submit);

    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }

        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }



    }

    public TextView getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(TextView titleTv) {
        this.titleTv = titleTv;
    }
}
