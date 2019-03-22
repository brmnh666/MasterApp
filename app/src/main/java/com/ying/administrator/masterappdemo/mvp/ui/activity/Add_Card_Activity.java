package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Bank;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.model.CardModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CardPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ChooseBankAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.util.BankUtil;
import com.ying.administrator.masterappdemo.widget.CustomDialog_ChooseBank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/*绑定银行卡页面*/
public class Add_Card_Activity extends BaseActivity<CardPresenter, CardModel> implements View.OnClickListener, CardContract.View {

    private static int REQUEST_AUTOTEST=1011;

    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_add_card_name)
    TextView mTvAddCardName;
    @BindView(R.id.tv_add_card_bankname)
    TextView mTvAddCardBankname;
    @BindView(R.id.add_card_phone)
    TextView mAddCardPhone;
/*    @BindView(R.id.et_add_card_verification_code)
    EditText mEtAddCardVerificationCode;*/
    @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;
    /*@BindView(R.id.tv_getcode)
    TextView mtv_getcode;*/
    @BindView(R.id.btn_bind_card)
    Button mbtn_bind_card;
    @BindView(R.id.et_banknumber)
    EditText met_banknumber;

   /* @BindView(R.id.img_scan_card)
    ImageView mimg_scan_card;*/

   /* private TimeCount time;*/
    private CustomDialog_ChooseBank customDialog_chooseBank;//选择银行dialog
    private RecyclerView recyclerView_custom_bank;//显示银行的RecyclerView
    private ChooseBankAdapter chooseBankAdapter;
    private String userId;
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    protected void initData() {
        SPUtils spUtils=SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId,"1");



    }

    @Override
    protected void initView() {
        customDialog_chooseBank = new CustomDialog_ChooseBank(mActivity);
        /*time = new TimeCount(60000, 1000);*/
    }

    @Override
    protected void setListener() {
        mLlChooseBank.setOnClickListener(this);
        mImgActionbarReturn.setOnClickListener(this);
      /*  mtv_getcode.setOnClickListener(this);*/
        mbtn_bind_card.setOnClickListener(this);
        /*mimg_scan_card.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_choose_bank:

                customDialog_chooseBank.getWindow().setBackgroundDrawableResource(R.color.transparent);
                customDialog_chooseBank.show();
                Window window = customDialog_chooseBank.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                Display d = window.getWindowManager().getDefaultDisplay();
                wlp.height = (d.getHeight());
                wlp.width = (d.getWidth());
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                recyclerView_custom_bank = customDialog_chooseBank.findViewById(R.id.recyclerView_custom_choosebank);
                recyclerView_custom_bank.setLayoutManager(new LinearLayoutManager(mActivity));
                chooseBankAdapter = new ChooseBankAdapter(R.layout.item_bank, BankUtil.getBankList(), mActivity);
                recyclerView_custom_bank.setAdapter(chooseBankAdapter);


                chooseBankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.ll_choose_bank:
                                mTvAddCardBankname.setText(((Bank) adapter.getItem(position)).getBankname());
                                customDialog_chooseBank.dismiss();
                                break;
                        }

                    }
                });


                break;
            case R.id.img_actionbar_return:
                Add_Card_Activity.this.finish();

                break;
       /*     case R.id.tv_getcode:
                time.start();
                break;*/
            case R.id.btn_bind_card:
                //验证码接口为写好

                if (mTvAddCardBankname.getText().toString().length()==0||met_banknumber.getText().toString().length()==0){
                    Toast.makeText(this,"请选择银行并输入卡号",Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.AddorUpdateAccountPayInfo(userId,"Bank",mTvAddCardBankname.getText().toString(),met_banknumber.getText().toString());
                }


                break;
         /*   case R.id.img_scan_card:
                break;*/

            default:
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
         switch (baseResult.getStatusCode()){
             case 200:
                 if (baseResult.getData()==null){
                     return;
                 }else {
                     userInfo=baseResult.getData().getData().get(0);
                     if (userInfo.getTrueName()==null){
                         return;
                     }else {
                         mTvAddCardName.setText(userInfo.getTrueName());
                     }
                     if (userInfo.getPhone()==null){
                         return;
                     }else {
                         mAddCardPhone.setText(userInfo.getPhone());
                     }



                 }
                 break;

                 default:
                     break;
         }



    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    setResult(2000);
                    Add_Card_Activity.this.finish();

                }else {
                    Toast.makeText(this,"添加失败",Toast.LENGTH_SHORT).show();

                }
                break;
                default:
                    break;
        }

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }


/*    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mtv_getcode.setClickable(false);
            mtv_getcode.setText( millisUntilFinished / 1000 +"秒后可重新发送");
            mtv_getcode.setBackgroundResource(R.drawable.shape_code_after);
        }

        @Override
        public void onFinish() {
            mtv_getcode.setText("重新获取验证码");
            mtv_getcode.setClickable(true);
            mtv_getcode.setBackgroundResource(R.drawable.shape_code);

        }
    }*/

  /*  public void scan() {
        Intent intent = new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)
                .putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)//去除水印
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true)//去除键盘
                .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "zh-Hans")//设置提示为中文
                .putExtra("debug_autoAcceptResult", true);
        startActivityForResult(intent, REQUEST_AUTOTEST);
    }*/


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultDisplayStr;
        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

            // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
            //resultDisplayStr = "银行卡号: " + scanResult.getRedactedCardNumber() + "\n"; //只显示尾号
            resultDisplayStr = "银行卡号: " + scanResult.getFormattedCardNumber() + "\n";  //显示银行卡号

            // Do something with the raw number, e.g.:
            // myService.setCardNumber( scanResult.cardNumber );

            if (scanResult.isExpiryValid()) {
                resultDisplayStr += "有效期：" + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
            }

            if (scanResult.cvv != null) {
                // Never log or display a CVV
                resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
            }

            if (scanResult.postalCode != null) {
                resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
            }
        } else {
            resultDisplayStr = "Scan was canceled.";
        }
        met_banknumber.setText(resultDisplayStr);
        // do something with resultDisplayStr, maybe display it in a textView
        // resultTextView.setText(resultDisplayStr);
    }*/
}
