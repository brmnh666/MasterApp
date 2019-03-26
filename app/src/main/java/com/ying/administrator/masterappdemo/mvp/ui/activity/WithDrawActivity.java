package com.ying.administrator.masterappdemo.mvp.ui.activity;


/*提现activity*/

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.io.File;
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


    private PopwidowBankAdapter popwidowBankAdapter;
    private ImageView img_bankcancle;
    private String userId;
    private WithDrawMoney withDrawMoney=new WithDrawMoney();
    private View popupWindow_view ;
    private View popipwinow_addcard;
    private PopupWindow mPopupWindow;
    private List<BankCard> list=new ArrayList<>();//获取银行卡列表


    private String DrawMoney;
    private String CardNo;
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
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetDepositMoneyDisplay(userId);
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void initView() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_choosebank, null);
        popipwinow_addcard=LayoutInflater.from(mActivity).inflate(R.layout.popwindow_foot_add,null);

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



    }


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
                break;
            case R.id.ll_choose_withdraw_bank://选择银行
                showPopupWindow();
            break;
            case R.id.confirm_withdrawal_btn: //提交
                if (mMoneyEt.getText().toString()==null||CardNo==null){
                    Toast.makeText(WithDrawActivity.this,"请输入金额并选择银行卡",Toast.LENGTH_SHORT).show();
                }else {
                    DrawMoney=mMoneyEt.getText().toString();
                    mPresenter.WithDraw(DrawMoney,CardNo,userId);
                }

                break;

        }

        if (mImgWithdrawBank.isSelected()){
            mll_choose_withdraw_bank.setVisibility(View.VISIBLE);
        }else {
            mll_choose_withdraw_bank.setVisibility(View.GONE);
        }
    }

    @Override
    public void GetDepositMoneyDisplay(BaseResult<WithDrawMoney> baseResult) {
switch (baseResult.getStatusCode()){
       case 200:
           withDrawMoney=baseResult.getData();
           mMarginAmountTv.setText(baseResult.getData().getBzj());
           mCashWithdrawalAmountTv.setText(baseResult.getData().getKtx());
           mWithdrawTv.setText(baseResult.getData().getTxz());
           mAmountToBeConfirmedTv.setText(baseResult.getData().getDqr());
        break;
        default:
            break;
}
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData()==null){
                    return;
                }else {
                    list.addAll(baseResult.getData());
                    final RecyclerView recyclerView = popupWindow_view.findViewById(R.id.rv_popwindow_bank);
                    recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                    popwidowBankAdapter=new PopwidowBankAdapter(R.layout.item_popwindow_bank,list);
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

                            for (int i=0;i<list.size();i++){
                                list.get(i).setIscheck(false);
                            }
                            popwidowBankAdapter.notifyDataSetChanged();

                            switch (view.getId()){
                                case R.id.rl_popwindow_bank:
                                     if (list.get(position).isIscheck()){
                                         popwidowBankAdapter.notifyDataSetChanged();
                                     }else {
                                         list.get(position).setIscheck(true);
                                         popwidowBankAdapter.notifyDataSetChanged();

                                         mtv_withdraw_bankname.setText(((BankCard)adapter.getData().get(position)).getPayInfoName());
                                         String cardNo=((BankCard)adapter.getData().get(position)).getPayNo();
                                         CardNo=cardNo;//赋值给全局变量
                                         mtv_withdraw_banknum.setText("("+cardNo.substring(cardNo.length()-4,cardNo.length())+")");
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
    switch (baseResult.getStatusCode()){
    case 200:
        if (baseResult.getData().isItem1()){
            Toast.makeText(this,baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
            WithDrawActivity.this.finish();
        }else {
            Toast.makeText(this,baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
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

        img_bankcancle=popupWindow_view.findViewById(R.id.img_bankcancle);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
            public void onClick(View v)  {
                mPopupWindow.dismiss();
            }
        });

    }
}
