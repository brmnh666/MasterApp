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

/*转派dialog*/
public class CustomDialog_Redeploy extends AlertDialog {
    private TextView yes;
    private TextView no;
    private TextView titleTv;
    private String titleStr;
    private String yesStr;
    private String noStr;
    private Context context;

    /*  -------------------------------- 接口监听 -------------------------------------  */

    private onYesOnclickListener yesOnclickListener;
    private onNoOnclickListener onNoOnclickListener;

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener{
        void onNoOnclick();
    }


    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.onNoOnclickListener = onNoOnclickListener;
    }






    /*  ---------------------------------- 构造方法 -------------------------------------  */
    public CustomDialog_Redeploy(Context context) {
        super(context);
        this.context=context;
    }

    public CustomDialog_Redeploy(Context context, int themeResId) {
        super(context,R.style.MyDialog);
    }

    protected CustomDialog_Redeploy(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog_redeploy);//自定义布局

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
      no.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (onNoOnclickListener!=null){
                  onNoOnclickListener.onNoOnclick();
              }

          }
      });


    }

    /**
     * 初始化界面控件
     */
    private void initView(){
        yes = findViewById(R.id.tv_redeploy_submit);
        no=findViewById(R.id.tv_redeploy_cancle);

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


}
