package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.MainContract;
import com.ying.administrator.masterappdemo.mvp.model.MainModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MainPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BlankFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Home_Fragment;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Me_Fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.NewsFragment;
import com.ying.administrator.masterappdemo.widget.BadgeView;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    SPUtils spUtils = SPUtils.getInstance("token");
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();

    private String userID;//用户id
    private long mExittime;//声明一个long类型的变量 用于存放上一次点击 返回键的时刻
    private View under_review;
    private View btnConfirm;
    private AlertDialog underReviewDialog;
    private CustomDialog customDialog;

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
        userID = spUtils.getString("userName"); //获取用户id
        mPresenter.GetUserInfoList(userID,"1");

    }
    public void showVerifiedDialog(){
        customDialog = new CustomDialog(mActivity);
        customDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        customDialog.setTitle("实名认证");
        customDialog.show();

        customDialog.setYesOnclickListener("确定", new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                //Toast.makeText(getContext(), "点击了--去认证--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
                startActivity(new Intent(mActivity, Verified_Activity.class));
            }
        });

        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                //Toast.makeText(getContext(), "点击了--再想想--按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                // Toast.makeText(getContext(), "点击了--关闭-按钮", Toast.LENGTH_LONG).show();
                customDialog.dismiss();
            }
        });
    }
    public void initView() {
       final CommonDialog_Home dialog = new CommonDialog_Home(this); //弹出框
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
                if (userInfo!=null){
                    if (userInfo.getIfAuth().equals("1")){
                        Intent intent = new Intent(MainActivity.this, Order_Receiving_Activity.class);
                        intent.putExtra("intent", "pending_appointment");
                        startActivity(intent);
                    }else if (userInfo.getIfAuth().equals("0")){
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review,null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                            }
                        });
                        underReviewDialog =new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    }else if (userInfo.getIfAuth().equals("-1")){
                        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure,null);
                        btnConfirm = under_review.findViewById(R.id.btn_confirm);
                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                underReviewDialog.dismiss();
                                startActivity(new Intent(mActivity, Verified_Activity.class));
                            }
                        });
                        underReviewDialog =new AlertDialog.Builder(mActivity).setView(under_review).create();
                        underReviewDialog.show();
                    }else {
                        showVerifiedDialog();
                    }
                }else{
                    showVerifiedDialog();
                }
            }

        });

      /*  //显示未读消息红点
        BadgeView badgeView =new BadgeView(this);
        badgeView.setTargetView(findViewById(R.id.btn_message_count));
        badgeView.setBadgeCount(99);*/

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


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                userInfo = baseResult.getData().getData().get(0);


                break;

                default:
                    break;

        }
    }
}
