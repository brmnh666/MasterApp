package com.ying.administrator.masterappdemo.v3.activity;

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
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.v3.mvp.Presenter.WalletPresenter;
import com.ying.administrator.masterappdemo.v3.mvp.contract.WalletContract;
import com.ying.administrator.masterappdemo.v3.mvp.model.WalletModel;

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
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
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
                    mTvWarranty.setText("￥"+userInfo.getDepositMoney());
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
}
