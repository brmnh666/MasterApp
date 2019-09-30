package com.ying.administrator.masterappdemo.mvp.ui.activity;


/*提现activity*/

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.WithDrawMoney;
import com.ying.administrator.masterappdemo.mvp.contract.WithDrawContract;
import com.ying.administrator.masterappdemo.mvp.model.WithDrawModel;
import com.ying.administrator.masterappdemo.mvp.presenter.WithDrawPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.PopwidowBankAdapter;
import com.ying.administrator.masterappdemo.util.MyUtils;
import com.ying.administrator.masterappdemo.util.SingleClick;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithDrawActivity extends BaseActivity<WithDrawPresenter, WithDrawModel> implements View.OnClickListener, WithDrawContract.View {


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
    @BindView(R.id.tv_withdraw_bankname)
    TextView mtv_withdraw_bankname;
    @BindView(R.id.tv_withdraw_banknum)
    TextView mtv_withdraw_banknum;

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
    @BindView(R.id.ll_choose_withdraw_bank)
    LinearLayout mll_choose_withdraw_bank;
    @BindView(R.id.tv_hint)
    TextView mtv_hint;
    @BindView(R.id.img_wechat)
    ImageView mImgWechat;
    @BindView(R.id.img_alipay)
    ImageView mImgAlipay;
    @BindView(R.id.img_bank)
    ImageView mImgBank;
    @BindView(R.id.view)
    View mView;


    private PopwidowBankAdapter popwidowBankAdapter;
    private ImageView img_bankcancle;
    private String userId;
    private WithDrawMoney withDrawMoney = new WithDrawMoney();
    private View popupWindow_view;
    private View popipwinow_addcard;
    private PopupWindow mPopupWindow;
    private List<BankCard> list = new ArrayList<>();//获取银行卡列表


    private String DrawMoney;
    private String CardNo;
    private String payName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //EventBus.getDefault().register(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetDepositMoneyDisplay(userId);
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void initView() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_choosebank, null);
        popipwinow_addcard = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_foot_add, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


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
        mFullWithdrawalTv.setOnClickListener(this);
        mll_choose_withdraw_bank.setOnClickListener(this);
        mConfirmWithdrawalBtn.setOnClickListener(this);
      /*  mMoneyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double ktx= Double.parseDouble(s.toString());
                double getktx= Double.parseDouble(withDrawMoney.getKtx());

                if (ktx>getktx){
                    mtv_hint.setText("超出可提现范围");
                }

            }
        });*/

        mMoneyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mMoneyEt.setText(s);
                        mMoneyEt.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mMoneyEt.setText(s);
                    mMoneyEt.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mMoneyEt.setText(s.subSequence(0, 1));
                        mMoneyEt.setSelection(1);
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
            case R.id.img_actionbar_return:
                WithDrawActivity.this.finish();
                break;
            case R.id.wechat_pay_ll:
            case R.id.img_withdraw_wechat:
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
            case R.id.img_withdraw_alipay:
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
            case R.id.img_withdraw_bank:
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
            case R.id.full_withdrawal_tv://全部提现
                mMoneyEt.setText(withDrawMoney.getKtx());
                mMoneyEt.setSelection(mMoneyEt.getText().toString().length()); //光标移到最后
                break;
            case R.id.ll_choose_withdraw_bank://选择银行
                if (list.size()==0){
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("是否去添加银行卡")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//添加银行卡
                            dialog.dismiss();
                            startActivity(new Intent(mActivity,Add_Card_Activity.class));
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }else {
                    showPopupWindow();
                }

                break;
            case R.id.confirm_withdrawal_btn: //提交

                if (mMoneyEt.getText().toString().isEmpty() || CardNo == null) {
                    Toast.makeText(WithDrawActivity.this, "请输入金额并选择银行卡", Toast.LENGTH_SHORT).show();
                } else {
                    double money = Double.parseDouble(mMoneyEt.getText().toString());
                    if (money == 0) {
                        Toast.makeText(WithDrawActivity.this, "不能提现0元", Toast.LENGTH_SHORT).show();
                    } else {
                        DrawMoney = mMoneyEt.getText().toString();
                        mPresenter.WithDraw(DrawMoney, CardNo, userId,payName);
                    }

                }


                break;

        }

        if (mImgWithdrawBank.isSelected()) {
            mll_choose_withdraw_bank.setVisibility(View.VISIBLE);
            mView.setVisibility(View.VISIBLE);
        } else {
            mll_choose_withdraw_bank.setVisibility(View.GONE);
            mView.setVisibility(View.GONE);
        }
    }

    @Override
    public void GetDepositMoneyDisplay(BaseResult<WithDrawMoney> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    withDrawMoney = baseResult.getData();
                    mMarginAmountTv.setText(baseResult.getData().getBzj());
                    mCashWithdrawalAmountTv.setText(baseResult.getData().getKtx());
                    if (baseResult.getData().getTxz() == null) {
                        mWithdrawTv.setText("0.00");
                    } else {
                        mWithdrawTv.setText(baseResult.getData().getTxz());
                    }
                    //累计提现
                    if (baseResult.getData().getLjtx() == null) {
                        mAllWithdrawTv.setText("0.00");
                    } else {
                        mAllWithdrawTv.setText(baseResult.getData().getLjtx());
                    }
                    //累计收入

                    if (baseResult.getData().getLjsr() == null) {
                        mIncomeTv.setText("0.00");
                    } else {
                        mIncomeTv.setText(baseResult.getData().getLjsr());
                    }

                    mAmountToBeConfirmedTv.setText(baseResult.getData().getDqr());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    list.addAll(baseResult.getData());
                    final RecyclerView recyclerView = popupWindow_view.findViewById(R.id.rv_popwindow_bank);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                    popwidowBankAdapter = new PopwidowBankAdapter(R.layout.item_popwindow_bank, list);
                    recyclerView.setAdapter(popwidowBankAdapter);
                    //popwidowBankAdapter.addFooterView(popipwinow_addcard);

                /*    popwidowBankAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(WithDrawActivity.this,"添加新卡",Toast.LENGTH_SHORT).show();
                        }
                    });*/

                    popwidowBankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setIscheck(false);
                            }
                            popwidowBankAdapter.notifyDataSetChanged();

                            switch (view.getId()) {
                                case R.id.rl_popwindow_bank:
                                    if (list.get(position).isIscheck()) {
                                        popwidowBankAdapter.notifyDataSetChanged();
                                    } else {
                                        list.get(position).setIscheck(true);
                                        popwidowBankAdapter.notifyDataSetChanged();

                                        mtv_withdraw_bankname.setText(((BankCard) adapter.getData().get(position)).getPayInfoName());
                                        String cardNo = ((BankCard) adapter.getData().get(position)).getPayNo();
                                        CardNo = cardNo;//赋值给全局变量
                                        payName = ((BankCard) adapter.getData().get(position)).getPayName();
                                        mtv_withdraw_banknum.setText("(" + cardNo.substring(cardNo.length() - 4, cardNo.length()) + ")");
                                        mPopupWindow.dismiss();
                                    }

                                    break;

                            }
                        }
                    });

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
                    Toast.makeText(this, baseResult.getData().getItem2(), Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("GetUserInfoList");
                    WithDrawActivity.this.finish();
                } else {
                    Toast.makeText(this, baseResult.getData().getItem2(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }


    }


    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
        img_bankcancle = popupWindow_view.findViewById(R.id.img_bankcancle);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);

        img_bankcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"GetAccountPayInfoList".equals(message)){
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
