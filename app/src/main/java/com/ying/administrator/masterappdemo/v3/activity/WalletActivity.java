package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.ToBepresent;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CardList_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.RechargeActivity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedActivity2;
import com.ying.administrator.masterappdemo.mvp.ui.activity.Verified_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.WithDrawActivity;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.WalletPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.WalletContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.WalletModel;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.VerifiedDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletActivity extends BaseActivity<WalletPresenter, WalletModel> implements View.OnClickListener, WalletContract.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.tv_cumulative_income)
    TextView mTvCumulativeIncome;
    @BindView(R.id.tv_withdrawing)
    TextView mTvWithdrawing;
    @BindView(R.id.tv_confirmed)
    TextView mTvConfirmed;
    @BindView(R.id.tv_warranty)
    TextView mTvWarranty;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    private String userId;
    private UserInfo.UserInfoDean userInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_wallet;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的钱包");
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.ToBepresent(userId,"0","0","0");
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mIvSetting.setOnClickListener(this);
        mTvWithdraw.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_setting:
                if (userInfo.getIfAuth() == null) {
//                    return;
                    final VerifiedDialog dialog = new VerifiedDialog(mActivity);
                    dialog.setMessage("未实名认证不能绑定银行卡")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new VerifiedDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//去实名认证
                            startActivity(new Intent(mActivity, VerifiedActivity2.class));
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                } else if (userInfo.getIfAuth().equals("1")) {
                    startActivity(new Intent(this, CardList_Activity.class));

                } else {
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("未实名认证不能绑定银行卡")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//去实名认证
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();


                }

                break;
            case R.id.tv_withdraw:
                startActivity(new Intent(mActivity, WithdrawActivity.class));
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    userInfo = baseResult.getData().getData().get(0);
                    String CanWithdraw = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                    mTvMoney.setText(CanWithdraw);
                    String Unfinished = String.format("%.2f", userInfo.getUnfinishedAmount());
                    mTvConfirmed.setText("￥"+Unfinished );
                    mTvCumulativeIncome.setText("￥"+String.format("%.2f",userInfo.getServiceTotalMoney()));
                    mTvWarranty.setText("￥"+String.format("%.2f",userInfo.getDepositMoney()));
                }

                break;
            case 401:
                break;
        }
    }

    @Override
    public void AccountBill(BaseResult<Data<Bill>> baseResult) {

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }

    @Override
    public void ToBepresent(BaseResult<Data<ToBepresent>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    mTvWithdrawing.setText("￥"+String.format("%.2f",baseResult.getData().getItem2().getData()));
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        switch (name) {
            case "GetUserInfoList":
                mPresenter.GetUserInfoList(userId, "1");
                break;
        }
    }
}
