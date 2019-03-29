package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
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

    private Wallet_record_Adapter wallet_record_adapter;

    private String userId;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();

    private List<Bill.DataBean> recharge_list = new ArrayList<>();//充值记录
    private List<Bill.DataBean> withdraw_list = new ArrayList<>();//提现记录
    private List<Bill.DataBean> expend_income_list = new ArrayList<>();//收入支出记录

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");

        mPresenter.AccountBill(userId, "1");//充值
        mPresenter.AccountBill(userId, "3");//提现
        mPresenter.AccountBill(userId, "2,5");//收入和支出
        //  mPresenter.AccountBill(userId,"4");//待支付
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("我的钱包");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
                    return;
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

            case R.id.ll_wallet_sz_detail:
                Intent intent1=new Intent(this,DetailRecordActivity.class);
                intent1.putExtra("openwhich","1");
                startActivity(intent1);
                break;
            case R.id.ll_withdraw:
                Intent intent2=new Intent(this,DetailRecordActivity.class);
                intent2.putExtra("openwhich","2");
                startActivity(intent2);
                break;
            case R.id.ll_recharge:
                Intent intent3=new Intent(this,DetailRecordActivity.class);
                intent3.putExtra("openwhich","3");
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
                    mTvMoney.setText(userInfo.getTotalMoney().toString());
                    mTvUnfinished.setText(userInfo.getFrozenMoney().toString() + "元");


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
                                mRv_recharge_record.setLayoutManager(new LinearLayoutManager(mActivity));
                                mRv_recharge_record.setHasFixedSize(true);
                                mRv_recharge_record.setNestedScrollingEnabled(false);

                                if (recharge_list.size()<=4){
                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, recharge_list);
                                }else {
                                    List<Bill.DataBean> list = new ArrayList<>();//充值记录

                                    for (int i=0;i<4;i++){
                                        list.add(recharge_list.get(i));
                                    }
                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, list);


                                }

                                mRv_recharge_record.setAdapter(wallet_record_adapter);

                                break;
                            case "2"://支出
                            case "5"://收入
                                expend_income_list.addAll(baseResult.getData().getItem2().getData());
                                mRvIncomeAndExpenditureDetails.setLayoutManager(new LinearLayoutManager(mActivity));
                                mRvIncomeAndExpenditureDetails.setHasFixedSize(true);
                                mRvIncomeAndExpenditureDetails.setNestedScrollingEnabled(false);



                                if (withdraw_list.size()<=4){
                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, expend_income_list);
                                }else {
                                    List<Bill.DataBean> list = new ArrayList<>();//提现记录

                                    for (int i=0;i<4;i++){
                                        list.add(expend_income_list.get(i));
                                    }
                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, list);

                                }

                                mRvIncomeAndExpenditureDetails.setAdapter(wallet_record_adapter);


                                break;
                            case "3"://提现
                                withdraw_list.addAll(baseResult.getData().getItem2().getData());
                                mRvWithdrawalsRecord.setLayoutManager(new LinearLayoutManager(mActivity));
                                mRvWithdrawalsRecord.setHasFixedSize(true);
                                mRvWithdrawalsRecord.setNestedScrollingEnabled(false);

                                if (withdraw_list.size()<=4){
                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, withdraw_list);
                                }else {
                                    List<Bill.DataBean> list = new ArrayList<>();//提现记录

                                   for (int i=0;i<4;i++){
                                       list.add(withdraw_list.get(i));
                                   }
                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, list);
                                }
                                mRvWithdrawalsRecord.setAdapter(wallet_record_adapter);
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
                if (baseResult.getData() == null) {
                    mIvBankNo.setVisibility(View.VISIBLE);
                    mTvBank.setVisibility(View.VISIBLE);
                } else {
                    mIvBankNo.setVisibility(View.GONE);
                    mTvBank.setVisibility(View.VISIBLE);
                    mTvBank.setText("已绑定" + baseResult.getData().size() + "张银行卡");

                }
                break;
            default:
                break;
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        mPresenter.GetAccountPayInfoList(userId);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
