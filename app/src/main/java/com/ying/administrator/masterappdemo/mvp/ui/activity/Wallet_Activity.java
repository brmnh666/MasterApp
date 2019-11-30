package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Bill;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.WalletContract;
import com.ying.administrator.masterappdemo.mvp.model.WalletModel;
import com.ying.administrator.masterappdemo.mvp.presenter.WalletPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Wallet_record_Adapter;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.VerifiedDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wallet_Activity extends BaseActivity<WalletPresenter, WalletModel> implements View.OnClickListener, WalletContract.View {


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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView mTvWithdraw;
    @BindView(R.id.tv_unfinished)
    TextView mTvUnfinished;
    @BindView(R.id.tv_free_gift)
    TextView mTvFreeGift;
    @BindView(R.id.rv_income_and_expenditure_details)
    RecyclerView mRvIncomeAndExpenditureDetails;
    @BindView(R.id.iv_bank_no)
    ImageView mIvBankNo; //未绑定警告图片
    @BindView(R.id.tv_bank)
    TextView mTvBank; //未绑定文字
    /*   @BindView(R.id.iv_aplipay_no)
       ImageView mIvAplipayNo;
       @BindView(R.id.tv_aplipay)
       TextView mTvAplipay;
       @BindView(R.id.iv_wechat_no)
       ImageView mIvWechatNo;
       @BindView(R.id.tv_wechat)
       TextView mTvWechat;*/
    @BindView(R.id.rv_withdrawals_record)//提现
            RecyclerView mRvWithdrawalsRecord;

    @BindView(R.id.rv_recharge_record)//充值
            RecyclerView mRv_recharge_record;

    @BindView(R.id.ll_card_list)//跳转到银行卡列表
            LinearLayout ll_card_list;
 /*   @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.empty_view_one)
    LinearLayout mEmptyViewOne;
    @BindView(R.id.empty_view_two)
    LinearLayout mEmptyViewTwo;*/

    @BindView(R.id.ll_wallet_sz_detail)
    LinearLayout mll_wallet_sz_detail;

    @BindView(R.id.ll_withdraw)//提现记录
            LinearLayout mll_withdraw;

    @BindView(R.id.ll_recharge)//充值记录
            LinearLayout mll_recharge;
    @BindView(R.id.tv_can_withdraw)
    TextView mTvCanWithdraw;
    @BindView(R.id.tv_margin)
    TextView mTvMargin;
    @BindView(R.id.ll_card)
    LinearLayout mLlCard;
    @BindView(R.id.tv_wallet_sz_detail)
    TextView mTvWalletSzDetail;
    @BindView(R.id.tv_withdraw_more)
    TextView mTvWithdrawMore;
    @BindView(R.id.tv_recharge_more)
    TextView mTvRechargeMore;
    @BindView(R.id.tv_freeze)
    TextView mTvFreeze;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private Wallet_record_Adapter wallet_record_adapter1;
    private Wallet_record_Adapter wallet_record_adapter2;
    private Wallet_record_Adapter wallet_record_adapter3;

    private String userId;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();

    private List<Bill.DataBean> recharge_list = new ArrayList<>();//充值记录
    private List<Bill.DataBean> withdraw_list = new ArrayList<>();//提现记录
    private List<Bill.DataBean> expend_income_list = new ArrayList<>();//收入支出记录
    private int cardList = 0;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");

        mPresenter.AccountBill(userId, "1", "1", "999");//充值
        mPresenter.AccountBill(userId, "3", "1", "999");//提现
        mPresenter.AccountBill(userId, "2,5", "1", "999");//收入和支出
        //  mPresenter.AccountBill(userId,"4");//待支付
        mPresenter.GetAccountPayInfoList(userId);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetUserInfoList(userId, "1");
                mPresenter.GetAccountPayInfoList(userId);
                mPresenter.AccountBill(userId, "1", "1", "999");//充值
                mPresenter.AccountBill(userId, "3", "1", "999");//提现
                mPresenter.AccountBill(userId, "2,5", "1", "999");//收入和支出
            }
        });
    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("我的钱包");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);

        mRvIncomeAndExpenditureDetails.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvIncomeAndExpenditureDetails.setHasFixedSize(true);
        mRvIncomeAndExpenditureDetails.setNestedScrollingEnabled(false);
        mRv_recharge_record.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv_recharge_record.setHasFixedSize(true);
        mRv_recharge_record.setNestedScrollingEnabled(false);
        mRvWithdrawalsRecord.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvWithdrawalsRecord.setHasFixedSize(true);
        mRvWithdrawalsRecord.setNestedScrollingEnabled(false);
        wallet_record_adapter1 = new Wallet_record_Adapter(R.layout.item_withdrawals_record, expend_income_list);
        wallet_record_adapter2 = new Wallet_record_Adapter(R.layout.item_withdrawals_record, recharge_list);
        wallet_record_adapter3 = new Wallet_record_Adapter(R.layout.item_withdrawals_record, withdraw_list);
        mRvIncomeAndExpenditureDetails.setAdapter(wallet_record_adapter1);
        mRv_recharge_record.setAdapter(wallet_record_adapter2);
        mRvWithdrawalsRecord.setAdapter(wallet_record_adapter3);
        wallet_record_adapter1.setEmptyView(getEmptyViewCz());
        wallet_record_adapter2.setEmptyView(getEmptyViewCz());
        wallet_record_adapter3.setEmptyView(getEmptyViewCz());

    }


    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);
        mTvWithdraw.setOnClickListener(this);
        ll_card_list.setOnClickListener(this);
        mll_wallet_sz_detail.setOnClickListener(this);
        mll_withdraw.setOnClickListener(this);
        mll_recharge.setOnClickListener(this);
        mTvWalletSzDetail.setOnClickListener(this);
        mTvWithdrawMore.setOnClickListener(this);
        mTvRechargeMore.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
            case R.id.tv_withdraw:
                startActivity(new Intent(mActivity, WithDrawActivity.class));
                break;
            case R.id.ll_card_list:
                if (userInfo.getIfAuth() == null) {
//                    return;
                    final VerifiedDialog dialog = new VerifiedDialog(mActivity);
                    dialog.setMessage("未实名认证不能绑定银行卡")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new VerifiedDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//去实名认证
                            startActivity(new Intent(mActivity, Verified_Activity.class));
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
            case R.id.tv_wallet_sz_detail:
            case R.id.ll_wallet_sz_detail:
                Intent intent1 = new Intent(this, DetailRecordActivity.class);
                intent1.putExtra("openwhich", "1");
                startActivity(intent1);
                break;
            case R.id.tv_withdraw_more:
            case R.id.ll_withdraw:
                Intent intent2 = new Intent(this, DetailRecordActivity.class);
                intent2.putExtra("openwhich", "2");
                startActivity(intent2);
                break;
            case R.id.tv_recharge_more:
            case R.id.ll_recharge:
                Intent intent3 = new Intent(this, DetailRecordActivity.class);
                intent3.putExtra("openwhich", "3");
                startActivity(intent3);

                break;


        }

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() != null) {
                    userInfo = baseResult.getData().getData().get(0);
                    String Money = String.format("%.2f", userInfo.getTotalMoney());
                    mTvMoney.setText(Money);
                    String Unfinished = String.format("%.2f", userInfo.getUnfinishedAmount());
                    mTvUnfinished.setText(Unfinished + "元");
                    String CanWithdraw = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                    mTvCanWithdraw.setText(CanWithdraw);
                    String Con = String.format("%.2f", userInfo.getCon());
                    mTvFreeGift.setText(Con);
                    mTvFreeze.setText(String.format("%.2f", userInfo.getFrozenMoney()));
                }

                break;
            case 401:
                break;
        }
    }

    @Override
    public void AccountBill(BaseResult<Data<Bill>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (baseResult.getData().getItem2().getData() != null) {
                        switch (baseResult.getData().getItem2().getData().get(0).getState()) {
                            case "1"://充值
                                recharge_list.addAll(baseResult.getData().getItem2().getData());
                                if (recharge_list.size() <= 4) {
                                    wallet_record_adapter2.setNewData(recharge_list);
                                } else {
                                    List<Bill.DataBean> list = new ArrayList<>();//充值记录
                                    for (int i = 0; i < 5; i++) {
                                        list.add(recharge_list.get(i));
                                    }
                                    wallet_record_adapter2.setNewData(list);

                                }

                                break;
                            case "2"://支出
                            case "5"://收入
                                expend_income_list.addAll(baseResult.getData().getItem2().getData());
                                if (expend_income_list.size() <= 4) {
                                    wallet_record_adapter1.setNewData(expend_income_list);
                                } else {
                                    List<Bill.DataBean> list1 = new ArrayList<>();//提现记录

                                    for (int i = 0; i < 5; i++) {
                                        list1.add(expend_income_list.get(i));
                                    }
                                    wallet_record_adapter1.setNewData(list1);

                                }


                                break;
                            case "3"://提现
                                withdraw_list.addAll(baseResult.getData().getItem2().getData());
//
                                if (withdraw_list.size() <= 4) {
                                    wallet_record_adapter3.setNewData(withdraw_list);
                                } else {
                                    List<Bill.DataBean> list2 = new ArrayList<>();//提现记录

                                    for (int i = 0; i < 5; i++) {
                                        list2.add(withdraw_list.get(i));
                                    }
                                    wallet_record_adapter3.setNewData(list2);
                                }
                                break;
                            case "4"://待支付
                                break;
                            default:
                                break;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                cardList = 0;
                if (baseResult.getData() == null) {
                    mIvBankNo.setVisibility(View.VISIBLE);
                    mTvBank.setVisibility(View.VISIBLE);
                } else {
                    mIvBankNo.setVisibility(View.GONE);
                    mTvBank.setVisibility(View.VISIBLE);
                    for (int i = 0; i < baseResult.getData().size(); i++) {
                        if ("Y".equals(baseResult.getData().get(i).getIsUse())) {
                            cardList = cardList + 1;
                        }
                    }
                    mTvBank.setText("已绑定" + cardList + "张银行卡");

                }
                break;
            default:
                break;
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if ("GetUserInfoList".equals(message)) {
            mPresenter.GetUserInfoList(userId, "1");
        }
        if ("card".equals(message)) {
            mPresenter.GetAccountPayInfoList(userId);
        }
        if (!"GetAccountPayInfoList".equals(message)) {
            return;
        }

        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
