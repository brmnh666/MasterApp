package com.ying.administrator.masterappdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.Appointedsheet_Fragement;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.Grabsheet_Fragement;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.InService_Fragement;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.Receivedsheet_Fragement;
import com.ying.administrator.masterappdemo.fragment.ReceivingFragment.Returnedparts_Fragement;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.adapter.TabLayoutViewPagerAdapter;
import com.ying.administrator.masterappdemo.widget.BadgeView;

import java.util.ArrayList;

public class Order_Receiving_Activity extends AppCompatActivity implements DefineView {

     private TabLayout tab_Receiving_layout;
     private ViewPager receiving_viewpager;
     private ArrayList<Fragment> fragmentList =new ArrayList<>();
     //tablout的内容
     private  ArrayList<String> title=new ArrayList<>();
     private Grabsheet_Fragement grabsheet_fragement;
     private Receivedsheet_Fragement receivedsheet_fragement;
     private InService_Fragement inService_fragement;
     private Appointedsheet_Fragement appointedsheet_fragement;
     private Returnedparts_Fragement returnedparts_fragement;


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

    }

    @Override
    public void initValidata() {
        //显示未读消息红点
        BadgeView badgeView =new BadgeView(this);
        badgeView.setTargetView(findViewById(R.id.img_actionbar_message));
        badgeView.setBadgeCount(8);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {
         //为tablayout添加内容
         title.add("抢单");
         title.add("已接订单");
         title.add("服务中");
         title.add("指派订单");
         title.add("待返件");
        grabsheet_fragement=new Grabsheet_Fragement();
        receivedsheet_fragement=new Receivedsheet_Fragement();
        inService_fragement=new InService_Fragement();
        appointedsheet_fragement=new Appointedsheet_Fragement();
        returnedparts_fragement=new Returnedparts_Fragement();
        fragmentList.add(grabsheet_fragement);
        fragmentList.add(receivedsheet_fragement);
        fragmentList.add(inService_fragement);
        fragmentList.add(appointedsheet_fragement);
        fragmentList.add(returnedparts_fragement);
        TabLayoutViewPagerAdapter tabLayoutViewPagerAdapter=new TabLayoutViewPagerAdapter(getSupportFragmentManager(),fragmentList,title);
        tab_Receiving_layout.setTabMode(TabLayout.MODE_FIXED);
        receiving_viewpager.setAdapter(tabLayoutViewPagerAdapter);
        tab_Receiving_layout.setupWithViewPager(receiving_viewpager);

        receiving_viewpager.setCurrentItem(0);//默认第一个


    }
}
