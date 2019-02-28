package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.switcher_installation_work_order)
    Switch mSwitcherInstallationWorkOrder;
    @BindView(R.id.switcher_repair_work_order)
    Switch mSwitcherRepairWorkOrder;
    @BindView(R.id.switcher_accept_the_repair_work_order)
    Switch mSwitcherAcceptTheRepairWorkOrder;
    @BindView(R.id.switcher_receiving_repair_work_order)
    Switch mSwitcherReceivingRepairWorkOrder;
    @BindView(R.id.switcher_whether_to_allow_the_order)
    Switch mSwitcherWhetherToAllowTheOrder;
    @BindView(R.id.switcher_allow_order)
    Switch mSwitcherAllowOrder;
    @BindView(R.id.ll_clean)
    LinearLayout mLlClean;
    @BindView(R.id.ll_update)
    LinearLayout mLlUpdate;
    @BindView(R.id.btn_sign_out_of_your_account)
    Button mBtnSignOutOfYourAccount;
    private SPUtils spUtils;


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        mTvActionbarTitle.setText("设置");
    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(new CustomOnclickLister());
        mBtnSignOutOfYourAccount.setOnClickListener(new CustomOnclickLister());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public class CustomOnclickLister implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_return:
                    SettingActivity.this.finish();
                    break;
                case R.id.btn_sign_out_of_your_account:
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("是否确认退出")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            dialog.dismiss();
                            spUtils.put("isLogin",false);
                            startActivity(new Intent(mActivity, LoginActivity.class));
                            ActivityUtils.finishAllActivities();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
            }

        }
    }
}
