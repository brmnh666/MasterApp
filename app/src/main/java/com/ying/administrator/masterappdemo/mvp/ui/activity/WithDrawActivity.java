package com.ying.administrator.masterappdemo.mvp.ui.activity;


/*提现activity*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithDrawActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.all_withdraw_tv)  //累计提现
            TextView mAllWithdrawTv;
    @BindView(R.id.withdraw_ll)
    LinearLayout mWithdrawLl;
    @BindView(R.id.income_tv)  //累计收入
            TextView mIncomeTv;
    @BindView(R.id.income_ll)
    LinearLayout mIncomeLl;
    @BindView(R.id.margin_amount_tv)  //保证金金额
            TextView mMarginAmountTv;
    @BindView(R.id.cash_withdrawal_amount_tv) //可提现金额
            TextView mCashWithdrawalAmountTv;
    @BindView(R.id.withdraw_tv)   //提现中
            TextView mWithdrawTv;
    @BindView(R.id.amount_to_be_confirmed_tv) //待确认
            TextView mAmountToBeConfirmedTv;



    @BindView(R.id.money_et)
    EditText mMoneyEt;
    @BindView(R.id.full_withdrawal_tv)
    TextView mFullWithdrawalTv;
    @BindView(R.id.confirm_withdrawal_btn)
    Button mConfirmWithdrawalBtn;
    /*提现到*/
    @BindView(R.id.img_withdraw_wechat)
    ImageView mImgWithdrawWechat;
    @BindView(R.id.img_withdraw_alipay)
    ImageView mImgWithdrawAlipay;
    @BindView(R.id.img_withdraw_bank)
    ImageView mImgWithdrawBank;

/*
    @BindView(R.id.img_wechat)
    ImageView mImgWechat;
    @BindView(R.id.img_alipay)
    ImageView mImgAlipay;
    @BindView(R.id.img_bank)
    ImageView mImgBank;
*/



    @BindView(R.id.wechat_pay_ll)
    LinearLayout mWechatPayLl;
    @BindView(R.id.alipay_ll)
    LinearLayout mAlipayLl;
    @BindView(R.id.unionpay_ll)
    LinearLayout mUnionpayLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
        mImgWithdrawWechat.setOnClickListener(this);
        mImgWithdrawAlipay.setOnClickListener(this);
        mImgWithdrawBank.setOnClickListener(this);
        mWechatPayLl.setOnClickListener(this);
        mAlipayLl.setOnClickListener(this);
        mUnionpayLl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_actionbar_return:
                WithDrawActivity.this.finish();
                break;
            case R.id.wechat_pay_ll:
                if (mImgWithdrawWechat.isSelected()) {
                    mImgWithdrawWechat.setSelected(false);
                    mImgWithdrawAlipay.setSelected(false);
                    mImgWithdrawBank.setSelected(false);
                } else {
                    mImgWithdrawWechat.setSelected(true);
                    mImgWithdrawAlipay.setSelected(false);
                    mImgWithdrawBank.setSelected(false);
                }
                break;
            case R.id.alipay_ll:
                if (mImgWithdrawAlipay.isSelected()) {
                    mImgWithdrawAlipay.setSelected(false);
                    mImgWithdrawWechat.setSelected(false);
                    mImgWithdrawBank.setSelected(false);
                } else {
                    mImgWithdrawAlipay.setSelected(true);
                    mImgWithdrawWechat.setSelected(false);
                    mImgWithdrawBank.setSelected(false);
                }

                break;
            case R.id.unionpay_ll:
                if (mImgWithdrawBank.isSelected()) {
                    mImgWithdrawBank.setSelected(false);
                    mImgWithdrawAlipay.setSelected(false);
                    mImgWithdrawWechat.setSelected(false);
                } else {
                    mImgWithdrawBank.setSelected(true);
                    mImgWithdrawAlipay.setSelected(false);
                    mImgWithdrawWechat.setSelected(false);
                }

                break;


        }
    }
}
