package com.ying.administrator.masterappdemo.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;

/*转派dialog*/
public class CustomDialog_ChooseBank extends AlertDialog {
    private TextView yes;
    private TextView no;
    private TextView titleTv;
    private String titleStr;
    private String yesStr;
    private String noStr;
    private Context context;

    /*  -------------------------------- 接口监听 -------------------------------------  */


    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }







    /*  ---------------------------------- 构造方法 -------------------------------------  */
    public CustomDialog_ChooseBank(Context context) {
        super(context);
        this.context=context;
    }

    public CustomDialog_ChooseBank(Context context, int themeResId) {
        super(context,R.style.MyDialog);
    }

    protected CustomDialog_ChooseBank(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog_choosebank);//自定义布局
        setCanceledOnTouchOutside(true);

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


    }

    /**
     * 初始化界面控件
     */
    private void initView(){

    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {




    }


}
