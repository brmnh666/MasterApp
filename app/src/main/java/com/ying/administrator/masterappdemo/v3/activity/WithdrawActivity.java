package com.ying.administrator.masterappdemo.v3.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.ying.administrator.masterappdemo.widget.TradeTextWatcher;

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
    @BindView(R.id.tv_withdrawal_amount)
    TextView mTvWithdrawalAmount;
    @BindView(R.id.tv_available_balance)
    TextView mTvAvailableBalance;
    @BindView(R.id.btn_confirm_withdrawal)
    Button mBtnConfirmWithdrawal;
    @BindView(R.id.btn_price_1)
    TextView mBtnPrice1;
    @BindView(R.id.btn_price_2)
    TextView mBtnPrice2;
    @BindView(R.id.btn_price_3)
    TextView mBtnPrice3;
    @BindView(R.id.btn_price_4)
    TextView mBtnPrice4;
    @BindView(R.id.btn_price_5)
    TextView mBtnPrice5;
    @BindView(R.id.btn_price_6)
    TextView mBtnPrice6;
    @BindView(R.id.btn_price_7)
    TextView mBtnPrice7;
    @BindView(R.id.btn_price_8)
    TextView mBtnPrice8;
    @BindView(R.id.btn_price_9)
    TextView mBtnPrice9;
    @BindView(R.id.btn_price_point)
    TextView mBtnPricePoint;
    @BindView(R.id.btn_price_0)
    TextView mBtnPrice0;
    @BindView(R.id.ll_hide)
    LinearLayout mLlHide;
    @BindView(R.id.btn_price_del)
    LinearLayout mBtnPriceDel;
    @BindView(R.id.btn_price_shoukuan)
    TextView mBtnPriceShoukuan;
    @BindView(R.id.linearlayout)
    LinearLayout mLinearlayout;
    @BindView(R.id.rl_keyboard)
    RelativeLayout mRlKeyboard;
    private String userId;
    private boolean ifTrue;
    private List<BankCard> brankList;
    private String money;
    private WithDrawMoney withDrawMoney;
    private String bankNo;
    private String payName;
    private String endNum;

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
        mIvBack.setOnClickListener(this);
        mEtWithdrawalAmount.setEnabled(false);
        mEtWithdrawalAmount.addTextChangedListener(new TradeTextWatcher(mEtWithdrawalAmount, null));
        TextView[] mBtnkey_digits = new TextView[10];
        for (int i = 0; i < 10; i++) {
            String strid = String.format("btn_price_%d", i);
            mBtnkey_digits[i] = (TextView) findViewById(this
                    .getResources().getIdentifier(strid, "id",
                            this.getPackageName()));
            mBtnkey_digits[i].setOnClickListener(mClickListener);
        }
        mLlHide.setOnClickListener(mClickListener);
        mBtnPricePoint.setOnClickListener(mClickListener);
        mBtnPriceDel.setOnClickListener(mClickListener);
        mBtnPriceShoukuan.setOnClickListener(this);


        mEtWithdrawalAmount.setOnClickListener(this);
        mTvWithdrawalAmount.setOnClickListener(this);
        mBtnConfirmWithdrawal.setOnClickListener(this);
        mLlBankCard.setOnClickListener(this);
        mLlBankCardOne.setOnClickListener(this);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.btn_price_1
                    || id == R.id.btn_price_2
                    || id == R.id.btn_price_3
                    || id == R.id.btn_price_4
                    || id == R.id.btn_price_5
                    || id == R.id.btn_price_6
                    || id == R.id.btn_price_7
                    || id == R.id.btn_price_8
                    || id == R.id.btn_price_9
                    || id == R.id.btn_price_0) {
                String input = ((TextView) view).getText().toString();
                if (input == null) {
                    mEtWithdrawalAmount.setText(input);
                } else if (input != null) {
                    String strTmp = mEtWithdrawalAmount.getText().toString();
                    strTmp += input;
                    mEtWithdrawalAmount.setText(strTmp);
                }
                mEtWithdrawalAmount.setTextSize(30);
                mEtWithdrawalAmount.setTextColor(Color.BLACK);
            } else if (id == R.id.btn_price_point)//点
            {
                String inputa = ((TextView) view).getText().toString();
                if (inputa == null) {
                    mEtWithdrawalAmount.setText(inputa);
                } else if (inputa != null) {
                    String strTmp = mEtWithdrawalAmount.getText().toString();
                    strTmp += inputa;
                    mEtWithdrawalAmount.setText(strTmp);
                }
                mEtWithdrawalAmount.setTextSize(30);
                mEtWithdrawalAmount.setTextColor(Color.BLACK);
            } else if (id == R.id.btn_price_del) {//清除
                if (mEtWithdrawalAmount.getText().length() > 0) {
                    String strTmp = mEtWithdrawalAmount.getText().toString();
                    strTmp = strTmp.substring(0, strTmp.length() - 1);
                    mEtWithdrawalAmount.setText(strTmp);
                } else {
                    mEtWithdrawalAmount.setText("");
                }
                mEtWithdrawalAmount.setTextSize(30);
                mEtWithdrawalAmount.setTextColor(Color.BLACK);
            } else if (id == R.id.ll_hide) {
                mRlKeyboard.setVisibility(View.GONE);
            }
        }
    };
    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_withdrawal_amount:
                break;
            case R.id.btn_confirm_withdrawal:
            case R.id.btn_price_shoukuan:
                money= mEtWithdrawalAmount.getText().toString();
                if (ifTrue == true) {
                    if ("0".equals(money)){
                        MyUtils.showToast(mActivity,"提现金额必须大于0");
                    }
                    if (bankNo==null||"".equals(bankNo)){
                        MyUtils.showToast(mActivity,"请选择银行卡");
                    } else if (money==null||"".equals(money)) {
                        MyUtils.showToast(mActivity,"请输入提现金额");
                    }else if (Double.parseDouble(money)>Double.parseDouble(withDrawMoney.getKtx())){
                        MyUtils.showToast(mActivity,"超出可提现金额");
                    } else {
                        mPresenter.WithDraw(money, bankNo, userId, payName);
                    }
                }else {
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
            case R.id.tv_withdrawal_amount:
                mRlKeyboard.setVisibility(View.VISIBLE);
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
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    withDrawMoney = baseResult.getData();
                    mTvAvailableBalance.setText("可提现余额"+withDrawMoney.getKtx()+"元");
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
                    if (brankList.size()>0){
                        mLlBankCard.setVisibility(View.VISIBLE);
                        mLlBankCardOne.setVisibility(View.GONE);
                        bankNo=baseResult.getData().get(0).getPayNo();
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
                    }else {
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
                }else{
                    MyUtils.showToast(mActivity,baseResult.getData().getItem2());
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
                if(data==null){
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

