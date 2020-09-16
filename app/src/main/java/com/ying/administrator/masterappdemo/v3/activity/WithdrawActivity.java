package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.IDCard;
import com.ying.administrator.masterappdemo.entity.WithDrawMoney;
import com.ying.administrator.masterappdemo.mvp.contract.WithDrawContract;
import com.ying.administrator.masterappdemo.mvp.model.WithDrawModel;
import com.ying.administrator.masterappdemo.mvp.presenter.WithDrawPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.activity.CardList_Activity;
import com.ying.administrator.masterappdemo.mvp.ui.activity.VerifiedPhotoActivity;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class WithdrawActivity extends BaseActivity<WithDrawPresenter, WithDrawModel> implements View.OnClickListener, WithDrawContract.View {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.ll_bank_card_one)
    LinearLayout mLlBankCardOne;
    @BindView(R.id.iv_brank_logo)
    ImageView mIvBrankLogo;
    @BindView(R.id.tv_bank_name)
    TextView mTvBankName;
    @BindView(R.id.tv_tail_number)
    TextView mTvTailNumber;
    @BindView(R.id.tv_card_type)
    TextView mTvCardType;
    @BindView(R.id.ll_bank_card)
    LinearLayout mLlBankCard;
    @BindView(R.id.et_withdrawal_amount)
    EditText mEtWithdrawalAmount;
    @BindView(R.id.tv_available_balance)
    TextView mTvAvailableBalance;
    @BindView(R.id.btn_confirm_withdrawal)
    Button mBtnConfirmWithdrawal;
    @BindView(R.id.iv_bank)
    ImageView mIvBank;
    @BindView(R.id.ll_bank_choose)
    LinearLayout mLlBankChoose;
    @BindView(R.id.iv_alipay)
    ImageView mIvAlipay;
    @BindView(R.id.ll_alipay_choose)
    LinearLayout mLlAlipayChoose;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    @BindView(R.id.ll_bank_info)
    LinearLayout mLlBankInfo;
    private String userId;
    private boolean ifTrue;
    private List<BankCard> brankList;
    private String money;
    private WithDrawMoney withDrawMoney;
    private String bankNo;
    private String payName;
    private String endNum;
    private int digits = 2;//两位小数

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_withdraw;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("提现");
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetDepositMoneyDisplay(userId);
        mPresenter.GetAccountPayInfoList(userId);
        mPresenter.GetIDCardImg(userId);
    }

    @Override
    protected void setListener() {
        mIvBank.setSelected(true);
        mIvAlipay.setSelected(false);
        mLlBankInfo.setVisibility(View.VISIBLE);
        mLlAlipay.setVisibility(View.GONE);

        mIvBack.setOnClickListener(this);
        mBtnConfirmWithdrawal.setOnClickListener(this);
        mLlBankCard.setOnClickListener(this);
        mLlBankChoose.setOnClickListener(this);
        mLlAlipayChoose.setOnClickListener(this);
        mLlBankCardOne.setOnClickListener(this);
        mEtWithdrawalAmount.addTextChangedListener(new TextWatcher() {

            private String str;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                str = s.toString();
                //只能输入一个小数点
                if (str.contains(".")) {
                    if (str.lastIndexOf(".") != str.indexOf(".")) {
                        s = str.subSequence(0,
                                str.lastIndexOf("."));
                        mEtWithdrawalAmount.setText(s);
                        mEtWithdrawalAmount.setSelection(s.length()); //光标移到最后
                    }
                }
                //删除“.”后面超过2位后的数据
                if (str.contains(".")) {
                    if (s.length() - 1 - str.indexOf(".") > digits) {
                        s = str.subSequence(0,
                                str.indexOf(".") + digits + 1);
                        mEtWithdrawalAmount.setText(s);
                        mEtWithdrawalAmount.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (str.trim().equals(".")) {
                    s = "0" + s;
                    mEtWithdrawalAmount.setText(s);
                    mEtWithdrawalAmount.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (str.startsWith("0")
                        && str.trim().length() > 1) {
                    if (!str.substring(1, 2).equals(".")) {
                        mEtWithdrawalAmount.setText(s.subSequence(0, 1));
                        mEtWithdrawalAmount.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_bank_choose:
                mIvBank.setSelected(true);
                mIvAlipay.setSelected(false);
                mLlBankInfo.setVisibility(View.VISIBLE);
                mLlAlipay.setVisibility(View.GONE);
                break;
            case R.id.ll_alipay_choose:
                mIvBank.setSelected(false);
                mIvAlipay.setSelected(true);
                mLlBankInfo.setVisibility(View.GONE);
                mLlAlipay.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_confirm_withdrawal:
                money = mEtWithdrawalAmount.getText().toString();
                if (ifTrue == true) {
                    if (mIvBank.isSelected()){
                        if (bankNo == null || "".equals(bankNo)) {
                            MyUtils.showToast(mActivity, "请选择银行卡");
                            return;
                        }
                    }else{

                    }
                    if ("0".equals(money)) {
                        MyUtils.showToast(mActivity, "提现金额必须大于0");
                        return;
                    }
                    if (money == null || "".equals(money)) {
                        MyUtils.showToast(mActivity, "请输入提现金额");
                        return;
                    }
                    if (Double.parseDouble(money) > Double.parseDouble(withDrawMoney.getKtx())) {
                        MyUtils.showToast(mActivity, "超出可提现金额");
                        return;
                    }
                    mPresenter.WithDraw(money, bankNo, userId, payName);
                } else {
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("您实名认证不完善，不能提现，是否去完善并提现")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setNegtive("否")
                            .setPositive("是")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//添加银行卡
                            dialog.dismiss();
                            startActivity(new Intent(mActivity, VerifiedPhotoActivity.class));
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
                break;
            case R.id.ll_bank_card_one:
            case R.id.ll_bank_card:
                startActivityForResult(new Intent(mActivity, CardList_Activity.class), 2000);
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
    public void GetDepositMoneyDisplay(BaseResult<WithDrawMoney> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    withDrawMoney = baseResult.getData();
                    mTvAvailableBalance.setText("可提现余额" + withDrawMoney.getKtx() + "元");
                }
                break;
        }
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    mLlBankCardOne.setVisibility(View.VISIBLE);
                    mLlBankCard.setVisibility(View.GONE);
                    return;
                } else {
                    brankList = baseResult.getData();
                    if (brankList.size() > 0) {
                        mLlBankCard.setVisibility(View.VISIBLE);
                        mLlBankCardOne.setVisibility(View.GONE);
                        bankNo = baseResult.getData().get(0).getPayNo();
                        payName = baseResult.getData().get(0).getPayName();
                        mTvBankName.setText(baseResult.getData().get(0).getPayInfoName());
                        int length = baseResult.getData().get(0).getPayNo().length();
                        if (length > 4) {
                            endNum = baseResult.getData().get(0).getPayNo().substring(length - 4, length);
                        }
                        mTvTailNumber.setText(endNum);
                        switch (baseResult.getData().get(0).getPayInfoName()) {
                            case "光大银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.gaungda)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "广发银行股份有限公司":
                                Glide.with(mActivity)
                                        .load(R.mipmap.gaungfa)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "工商银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.gongshang)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "中国工商银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.gongshang)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "华夏银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.huaxia)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;

                            case "中国建设银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.jianshe)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "建设银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.jianshe)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "中国交通银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.jiaotong)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "民生银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.minsheng)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "宁波银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.ningbo)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "农业银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.nongye)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "中国农业银行贷记卡":
                                Glide.with(mActivity)
                                        .load(R.mipmap.nongye)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "浦发银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.pufa)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "兴业银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.xinye)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "邮政储蓄银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.youzheng)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "邮储银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.youzheng)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "招商银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.zhaoshan)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);

                                break;
                            case "浙商银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.zheshang)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "中国银行":

                                Glide.with(mActivity)
                                        .load(R.mipmap.zhongguo)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;
                            case "中信银行":
                                Glide.with(mActivity)
                                        .load(R.mipmap.zhongxin)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;

                            default:
                                Glide.with(mActivity)
                                        .load(R.drawable.avatar)
                                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                        .into(mIvBrankLogo);
                                break;

                        }
                    } else {
                        mLlBankCardOne.setVisibility(View.VISIBLE);
                        mLlBankCard.setVisibility(View.GONE);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void WithDraw(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage(baseResult.getData().getItem2())
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                            EventBus.getDefault().post("GetUserInfoList");
                            finish();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            EventBus.getDefault().post("GetUserInfoList");
                            finish();
                        }
                    }).show();
                } else {
                    MyUtils.showToast(mActivity, baseResult.getData().getItem2());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void GetIDCardImg(BaseResult<List<IDCard.IDCardBean>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().size() == 0) {
                    ifTrue = false;
                } else {
                    ifTrue = true;
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2000:
                if (data == null) {
                    return;
                }
                String bankName = data.getStringExtra("bankName");
                bankNo = data.getStringExtra("bankNo");
                payName = data.getStringExtra("payName");
//                ToastUtils.showShort(bankNo);
                int length = bankNo.length();
                if (length > 4) {
                    endNum = bankNo.substring(length - 4, length);
                }
                mLlBankCard.setVisibility(View.VISIBLE);
                mLlBankCardOne.setVisibility(View.GONE);
                mTvBankName.setText(bankName);
                mTvTailNumber.setText(endNum);
                switch (bankName) {
                    case "光大银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gaungda)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "广发银行股份有限公司":
                        Glide.with(mActivity)
                                .load(R.mipmap.gaungfa)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "工商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gongshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国工商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gongshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "华夏银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.huaxia)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                    case "中国建设银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jianshe)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "建设银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jianshe)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国交通银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jiaotong)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "民生银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.minsheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "宁波银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.ningbo)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "农业银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.nongye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国农业银行贷记卡":
                        Glide.with(mActivity)
                                .load(R.mipmap.nongye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "浦发银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.pufa)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "兴业银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.xinye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "邮政储蓄银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.youzheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "邮储银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.youzheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "招商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zhaoshan)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);

                        break;
                    case "浙商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zheshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国银行":

                        Glide.with(mActivity)
                                .load(R.mipmap.zhongguo)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中信银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zhongxin)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                    default:
                        Glide.with(mActivity)
                                .load(R.drawable.avatar)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if ("verified".equals(message)) {
            mPresenter.GetIDCardImg(userId);
        }
        if (!"GetAccountPayInfoList".equals(message)) {
            return;
        }
        mPresenter.GetAccountPayInfoList(userId);
    }
}

