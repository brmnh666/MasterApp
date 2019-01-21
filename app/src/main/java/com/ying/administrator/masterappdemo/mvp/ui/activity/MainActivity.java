package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BlankFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Home_Fragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Me_Fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.NewsFragment;
import com.ying.administrator.masterappdemo.widget.BadgeView;

public class MainActivity extends BaseActivity {

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    private long mExittime;//声明一个long类型的变量 用于存放上一次点击 返回键的时刻
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    public void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.workbench, Home_Fragment.newInstance("首页"));
        mFragmentSparseArray.append(R.id.message, NewsFragment.newInstance("消息"));
        mFragmentSparseArray.append(R.id.accessories_shop, BlankFragment.newInstance("配件商城"));
        mFragmentSparseArray.append(R.id.me, Me_Fragment.newInstance("我的"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();

            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.workbench)).commit();

        findViewById(R.id.receiving).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, Order_Receiving_Activity.class);
                intent.putExtra("intent","pending_appointment");
                startActivity(intent);
            }

        });

        //显示未读消息红点
        BadgeView badgeView =new BadgeView(this);
        badgeView.setTargetView(findViewById(R.id.btn_message_count));
        badgeView.setBadgeCount(99);

    }

    @Override
    protected void setListener() {

    }

    public void initValidata() {

    }

    public void initListener() {

    }

    public void bindData() {

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*  两秒之内再按一下退出*/
        //判断用户是否点击了返回键
        if (keyCode==KeyEvent.KEYCODE_BACK){
            //与上次点击返回键作差
            if ((System.currentTimeMillis()-mExittime)>2000){
                //大于2秒认为是误操作
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                //并记录记录下本次点击返回键的时刻
                mExittime=System.currentTimeMillis();
            }else {
                //小于2秒 则用户希望退出
                System.exit(0);
            }
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }
}
