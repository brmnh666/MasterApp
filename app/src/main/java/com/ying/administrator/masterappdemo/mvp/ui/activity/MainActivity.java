package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.umeng.socialize.UMShareAPI;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Message;
import com.ying.administrator.masterappdemo.entity.MessageData;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.MainContract;
import com.ying.administrator.masterappdemo.mvp.model.MainModel;
import com.ying.administrator.masterappdemo.mvp.presenter.MainPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.BlankFragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Home_Fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Me_Fragment;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.NewsFragment;
import com.ying.administrator.masterappdemo.widget.CustomDialog;
import com.ying.administrator.masterappdemo.widget.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, View.OnClickListener {

    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.img_home)
    ImageView mImgHome;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.img_message)
    ImageView mImgMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;


    @BindView(R.id.img_message_invisible)
    ImageView mImg_message_invisible;
    @BindView(R.id.img_cate)
    ImageView mImgCate;
    @BindView(R.id.tv_cate)
    TextView mTvCate;
    @BindView(R.id.ll_category)
    LinearLayout mLlCategory;
    @BindView(R.id.img_cart)
    ImageView mImgCart;
    @BindView(R.id.tv_cart)
    TextView mTvCart;
    @BindView(R.id.ll_car)
    LinearLayout mLlCar;
    @BindView(R.id.img_my)
    ImageView mImgMy;
    @BindView(R.id.tv_my)
    TextView mTvMy;
    @BindView(R.id.ll_mine)
    LinearLayout mLlMine;
    @BindView(R.id.tab_menu)
    LinearLayout mTabMenu;
    @BindView(R.id.ll_order)
    LinearLayout mLlOrder;
    @BindView(R.id.rootview)
    FrameLayout rootview;
    private List<Fragment> fragmentList;
    SPUtils spUtils = SPUtils.getInstance("token");
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();

    private String userID;//用户id
    private long mExittime;//声明一个long类型的变量 用于存放上一次点击 返回键的时刻
    private View under_review;
    private View btnConfirm;
    private AlertDialog underReviewDialog;
    private CustomDialog customDialog;
    private Button btn_verified_update;
    private QBadgeView qBadgeView;
    private TextView content;

    private SkeletonScreen skeletonScreen;
    private Window window;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {


        userID = spUtils.getString("userName"); //获取用户id
        mPresenter.GetUserInfoList(userID, "1");
        qBadgeView = new QBadgeView(mActivity);
        qBadgeView.bindTarget(mImg_message_invisible);
        qBadgeView.setBadgeGravity(Gravity.END | Gravity.TOP);
        qBadgeView.setBadgeTextSize(0, false);

        mPresenter.GetMessageList(userID, "0", "999", "0");
        mPresenter.GetTransactionMessageList(userID, "0", "999", "0");


    }

    public void showVerifiedDialog() {
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

    public void showRejectDialog() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audit_failure, null);
        content = under_review.findViewById(R.id.tv_content);
        content.setText(userInfo.getAuthMessage() + ",有疑问请咨询客服电话。");
        btnConfirm = under_review.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
                startActivity(new Intent(mActivity, Verified_Activity.class));
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
    }

    public void showUnderDialog() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_under_review, null);
        btnConfirm = under_review.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
    }

    public void showPassDialog() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_successful_review, null);
        btnConfirm = under_review.findViewById(R.id.btn_confirm);
        btn_verified_update = under_review.findViewById(R.id.btn_verified_update);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });
        btn_verified_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
                startActivity(new Intent(mActivity, VerifiedUpdateActivity.class));
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
    }

    public void showAd() {
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_tixing, null);
        TextView tv_know = under_review.findViewById(R.id.tv_know);
        ImageView iv_close = under_review.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });

        tv_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });

        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                .create();
        underReviewDialog.show();
        window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
        WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;
        // 也可按屏幕宽高比例进行设置宽高
//                Display display = mActivity.getWindowManager().getDefaultDisplay();
//                lp.width = (int) (display.getWidth() * 1);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = 400;
        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
        window.setBackgroundDrawable(new ColorDrawable());

    }

    @Override
    protected void initView() {
//        skeletonScreen=  Skeleton.bind(rootview)
//                .load(R.layout.skeleton_main)
//                .duration(2000)
//                .color(R.color.shimmer_color)
//                .angle(10)
//                .show();
        fragmentList = new ArrayList<>();
        fragmentList.add(Home_Fragment.newInstance(""));
        fragmentList.add(NewsFragment.newInstance(""));
        fragmentList.add(BlankFragment.newInstance(""));
        fragmentList.add(Me_Fragment.newInstance(""));
//        mFragmentSparseArray.append(R.id.workbench, Home_Fragment.newInstance("首页"));
//        mFragmentSparseArray.append(R.id.message, NewsFragment.newInstance("消息"));
//        mFragmentSparseArray.append(R.id.accessories_shop, BlankFragment.newInstance("配件商城"));
//        mFragmentSparseArray.append(R.id.me, Me_Fragment.newInstance("我的"));
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setScroll(false);
        mViewPager.setCurrentItem(0);
        tabSelected(mLlHome);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    protected void setListener() {
        mLlHome.setOnClickListener(this);
        mLlMessage.setOnClickListener(this);
        mLlCar.setOnClickListener(this);
        mLlMine.setOnClickListener(this);
        mLlOrder.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*  两秒之内再按一下退出*/
        //判断用户是否点击了返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键作差
            if ((System.currentTimeMillis() - mExittime) > 2000) {
                //大于2秒认为是误操作
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录记录下本次点击返回键的时刻
                mExittime = System.currentTimeMillis();
            } else {
                //小于2秒 则用户希望退出
                System.exit(0);
            }
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {

                } else {
                    userInfo = baseResult.getData().getData().get(0);
                    if (userInfo != null) {
                        if ("0".equals(userInfo.getIfAuth())) {
                            showUnderDialog();
                        } else if ("-1".equals(userInfo.getIfAuth())) {
                            showRejectDialog();
                        } else if ("1".equals(userInfo.getIfAuth())) {
//                            showPassDialog();
                            showAd();
                        } else {
                            showVerifiedDialog();
                        }
                    }
                    if (skeletonScreen != null) {
                        skeletonScreen.hide();
                    }
                }


                break;

            default:
                if (skeletonScreen != null) {
                    skeletonScreen.hide();
                }
                break;

        }
    }

    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    qBadgeView.hide(true);

                } else {
                    qBadgeView.setBadgeNumber(1);

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    qBadgeView.hide(true);
                } else {
                    qBadgeView.setBadgeNumber(1);
                }
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        switch (message) {
            case "GetUserInfoList":
                mPresenter.GetUserInfoList(userID, "1");
                break;
            case "orderempty":
                mPresenter.GetMessageList(userID, "0", "999", "1");
                break;
            case "transactionempty":
                mPresenter.GetTransactionMessageList(userID, "0", "999", "1");
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        UMShareAPI.get(this).release();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_order:
                if (userInfo.getIfAuth() != null) {
                    if (userInfo.getIfAuth().equals("1")) {
                        Intent intent = new Intent(MainActivity.this, Order_Receiving_Activity.class);
                        intent.putExtra("intent", "confirmedFragement");
                        startActivityForResult(intent, 20202);
                        overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                    } else if (userInfo.getIfAuth().equals("0")) {
                        showUnderDialog();
                    } else if (userInfo.getIfAuth().equals("-1")) {
                        showRejectDialog();
                    } else {
                        showVerifiedDialog();
                    }
                } else {
                    showVerifiedDialog();
                }
                break;
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_message:
                mViewPager.setCurrentItem(1);
                tabSelected(mLlMessage);
                break;
            case R.id.ll_car:
                mViewPager.setCurrentItem(2);
                tabSelected(mLlCar);
                break;
            case R.id.ll_mine:
                mViewPager.setCurrentItem(3);
                tabSelected(mLlMine);
                break;
        }
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlMessage.setSelected(false);
        mLlCar.setSelected(false);
        mLlMine.setSelected(false);
        linearLayout.setSelected(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentList.get(0).onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10202) {
            if (requestCode == 20202) {
                Log.d("=========>", "进入onActivityResult");
                mViewPager.setCurrentItem(1);
                tabSelected(mLlMessage);
            }

        }

    }
}
