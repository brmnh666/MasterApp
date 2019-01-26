package com.ying.administrator.masterappdemo.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Accessory;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Add_Ac_Adapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.MyRecyclerAdapter;

import java.util.List;

/*添加配件dialog*/
public class CustomDialog_Add_Accessory extends AlertDialog {
    private TextView yes;
    private TextView titleTv;
    private String titleStr;
    private String yesStr;
    private Context context;
    private RecyclerView recyclerView_custom_add_accessory;
    private Add_Ac_Adapter mAdd_Ac_Adapter;
    private boolean[] ischeck;
    List<Accessory> mList;
    /*  -------------------------------- 接口监听 -------------------------------------  */

    private onYesOnclickListener yesOnclickListener;

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
    public CustomDialog_Add_Accessory(Context context) {
        super(context);
        this.context=context;
    }

    public CustomDialog_Add_Accessory(Context context, int themeResId) {
        super(context,R.style.MyDialog);
    }

    protected CustomDialog_Add_Accessory(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog_add_accessory);//自定义布局

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
  /*       *//*加载弹出配件列表的数据*//*
    private void initAccessory() {
        ischeck=new boolean[mList.size()];
        recyclerView_custom_add_accessory=findViewById(R.id.recyclerView_custom_add_accessory);
         recyclerView_custom_add_accessory.setLayoutManager(new LinearLayoutManager(context));
        mAdd_Ac_Adapter=new Add_Ac_Adapter(R.layout.item_addaccessory,mList);
        recyclerView_custom_add_accessory.setAdapter(mAdd_Ac_Adapter);

        mAdd_Ac_Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView img_ac_select = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_select); //选中图片
                ImageView img_ac_unselect = (ImageView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.img_ac_unselect);//未选中图片
                adderView adderView =(adderView)adapter.getViewByPosition(recyclerView_custom_add_accessory, position, R.id.adderView);
                switch (view.getId()){
                    case R.id.img_ac_unselect:
                    case R.id.img_ac_select:
                    case R.id.tv_accessory_name:
                        if (ischeck[position]==false){ //如果是为选中的状态点击  变为红色 选中状态 出现 数量选择器
                            //viewadd.setVisibility(View.VISIBLE); //数量选择器出现
                            adderView.setVisibility(View.VISIBLE);
                            img_ac_unselect.setVisibility(View.INVISIBLE);
                            img_ac_select.setVisibility(View.VISIBLE);
                            ischeck[position]=true;

                            //没选选择时间默认数量为1
                            //accessory=(Accessory)adapter.getItem(position);
                            //fAccessory=new FAccessory();
                            //fAccessory.setFAccessoryName(accessory.getAccessoryName());
                            //fAccessory.setQuantity("1"); //默认数字为1
                            //fAccessory.setDiscountPrice(accessory.getAccessoryPrice());
                            //map.put(position,fAccessory);
                            //选择了时间数量根据输入框中的来
                            adderView.setOnValueChangeListene(new adderView.OnValueChangeListener() {
                                @Override
                                public void onValueChange(int value) {
                                    //没选选择时间默认数量为1
                                   // accessory=(Accessory)adapter.getItem(position);
                                   // fAccessory=new FAccessory();
                                   // fAccessory.setFAccessoryName(accessory.getAccessoryName());
                                   // fAccessory.setQuantity(String.valueOf(value));
                                   // fAccessory.setDiscountPrice(accessory.getAccessoryPrice()*value);
                                   // map.put(position,fAccessory);
                                }
                            });

                        }else {
                            //viewadd.setVisibility(View.INVISIBLE); //数量选择器消失
                            adderView.setVisibility(View.INVISIBLE);
                            img_ac_unselect.setVisibility(View.VISIBLE);
                            img_ac_select.setVisibility(View.INVISIBLE);
                            adderView.setValue(1); //但用户取消时将值设置为默认为1
                            ischeck[position]=false;
                            //map.remove(position);

                        }


                        break;





                }



            }
        });
    }*/

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
        yes = findViewById(R.id.tv_add_accessory_submit);
        titleTv = findViewById(R.id.add_accessory_title);

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
