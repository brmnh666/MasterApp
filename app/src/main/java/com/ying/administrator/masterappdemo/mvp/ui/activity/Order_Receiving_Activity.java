package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Appointment_failure_fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Complete_wait_fetch_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Completed_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.InService_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Quality_sheet_Fragement;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Pending_appointment_fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.ReceivingFragment.Returnedparts_Fragement;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.TabLayoutViewPagerAdapter;
import com.ying.administrator.masterappdemo.widget.BadgeView;

import java.util.ArrayList;

public class Order_Receiving_Activity extends AppCompatActivity implements DefineView {

     private TabLayout tab_Receiving_layout;
     private ViewPager receiving_viewpager;
     private ArrayList<Fragment> fragmentList =new ArrayList<>();
     private LinearLayout ll_return;
     //tablout的内容
     private  ArrayList<String> title=new ArrayList<>();
    // private Grabsheet_Fragement grabsheet_fragement;

     private Pending_appointment_fragment pending_appointment_fragment; //已接订单待预约
     private InService_Fragement inService_fragement; //服务中
     private Quality_sheet_Fragement quality_sheet_fragement; //质保单
     private Returnedparts_Fragement returnedparts_fragement;//待返件
     private Complete_wait_fetch_Fragement complete_wait_fetch_fragement;//完成待取机
     private Completed_Fragement completed_fragement; //已完成
     private Appointment_failure_fragment appointment_failure_fragment;//预约失败



     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order_receiving);
        initView();
        initValidata();
        initListener();
        bindData();
    }

    @Override
    public void initView() {
        tab_Receiving_layout=findViewById(R.id.tab_receiving_layout);
        receiving_viewpager=findViewById(R.id.receiving_viewpager);
        ll_return=findViewById(R.id.ll_return);

    }

    @Override
    public void initValidata() {
        //显示未读消息红点
    /*    BadgeView badgeView =new BadgeView(this);
        badgeView.setTargetView(findViewById(R.id.img_actionbar_message));
        badgeView.setBadgeCount(8);*/
    }

    @Override
    public void initListener() {
      ll_return.setOnClickListener(new CustomOnclickListner());
    }

    @Override
    public void bindData() {
         //为tablayout添加内容
         title.add("已接待预约");
         title.add("服务中");
         title.add("返件单");
         title.add("质保单");
         title.add("完成待取机");
         title.add("已完成");
         title.add("预约不成功");

        //grabsheet_fragement=new Grabsheet_Fragement();
        pending_appointment_fragment=new Pending_appointment_fragment(); //已接待预约
        inService_fragement=new InService_Fragement(); //服务中
        returnedparts_fragement=new Returnedparts_Fragement();//返件单
        quality_sheet_fragement=new Quality_sheet_Fragement();//质保单
        complete_wait_fetch_fragement=new Complete_wait_fetch_Fragement();//完成待取机
        completed_fragement=new Completed_Fragement();//已完成
        appointment_failure_fragment=new Appointment_failure_fragment();//预约不成功



        //fragmentList.add(grabsheet_fragement);
        fragmentList.add(pending_appointment_fragment);
        fragmentList.add(inService_fragement);
        fragmentList.add(returnedparts_fragement);
        fragmentList.add(quality_sheet_fragement);
        fragmentList.add(complete_wait_fetch_fragement);
        fragmentList.add(completed_fragement);
        fragmentList.add(appointment_failure_fragment);


        TabLayoutViewPagerAdapter tabLayoutViewPagerAdapter=new TabLayoutViewPagerAdapter(getSupportFragmentManager(),fragmentList,title);
        tab_Receiving_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        receiving_viewpager.setAdapter(tabLayoutViewPagerAdapter);
        tab_Receiving_layout.setupWithViewPager(receiving_viewpager);

        receiving_viewpager.setCurrentItem(0);//默认第一个
        /*显示哪一个fragment*/
        String intent = getIntent().getStringExtra("intent");
        switch (intent){


            case "pending_appointment"://已接待预约
                receiving_viewpager.setCurrentItem(0);
                break;

            case "in_service":  //服务中
               receiving_viewpager.setCurrentItem(1);
            break;

            case "return"://返件单
                receiving_viewpager.setCurrentItem(2);
                break;

            case "quality"://质保单
                receiving_viewpager.setCurrentItem(3);
                break;

            case "complete_wait_fetch"://完成待取机
                receiving_viewpager.setCurrentItem(4);
                break;

            case "completed"://已完成
                receiving_viewpager.setCurrentItem(5);
                break;
            case "appointment_failure"://预约失败
                receiving_viewpager.setCurrentItem(6);
                break;



            default:
             break;
        }



    }

    public class CustomOnclickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                Order_Receiving_Activity.this.finish();
                break;

        }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("====>","调用了activity里main的onActivityResult");

        if (resultCode==10001){    //到服务中去
            receiving_viewpager.setCurrentItem(1);
            Log.d("====>","10001");
        }else if (resultCode==10002){ //到返件中去
            receiving_viewpager.setCurrentItem(2);
        }
    }
}
