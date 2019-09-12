package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.BankCard;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.CardContract;
import com.ying.administrator.masterappdemo.mvp.model.CardModel;
import com.ying.administrator.masterappdemo.mvp.presenter.CardPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ChooseBankAdapter;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.CustomDialog_ChooseBank;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/*绑定银行卡页面*/
public class Add_Card_Activity extends BaseActivity<CardPresenter, CardModel> implements View.OnClickListener, CardContract.View {


    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    //    @BindView(R.id.tv_add_card_name)
//    TextView mTvAddCardName;
    @BindView(R.id.tv_add_card_bankname)
    TextView mTvAddCardBankname;
    @BindView(R.id.et_add_card_phone)
    EditText met_add_card_phone;
    /*    @BindView(R.id.et_add_card_verification_code)
        EditText mEtAddCardVerificationCode;*/
   /* @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;*/
    /*@BindView(R.id.tv_getcode)
    TextView mtv_getcode;*/
    @BindView(R.id.tv_bind_card)
    TextView mtv_bind_card;
    @BindView(R.id.et_banknumber)
    EditText met_banknumber;
    @BindView(R.id.et_add_card_name)
    EditText mEtAddCardName;

   /* @BindView(R.id.img_scan_card)
    ImageView mimg_scan_card;*/

    /* private TimeCount time;*/
    private CustomDialog_ChooseBank customDialog_chooseBank;//选择银行dialog
    private RecyclerView recyclerView_custom_bank;//显示银行的RecyclerView
    private ChooseBankAdapter chooseBankAdapter;
    private String userId;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();


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
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");


    }

    @Override
    protected void initView() {
        customDialog_chooseBank = new CustomDialog_ChooseBank(mActivity);
        /*time = new TimeCount(60000, 1000);*/
    }

    @Override
    protected void setListener() {
        // mLlChooseBank.setOnClickListener(this);
        mImgActionbarReturn.setOnClickListener(this);
        /*  mtv_getcode.setOnClickListener(this);*/
        mtv_bind_card.setOnClickListener(this);
        /*mimg_scan_card.setOnClickListener(this);*/
        met_banknumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 6) {

                    mPresenter.GetBankNameByCardNo(s.toString());
                } else if (s.toString().length() >= 16) {

                    String num = s.toString();
                    String substring = num.substring(0, 6);

                    mPresenter.GetBankNameByCardNo(substring);
                } else {
                    mTvAddCardBankname.setText("");
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.ll_choose_bank:
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


                break;*/
            case R.id.img_actionbar_return:
                Add_Card_Activity.this.finish();

                break;
       /*     case R.id.tv_getcode:
                time.start();
                break;*/
            case R.id.tv_bind_card:
                String name=mEtAddCardName.getText().toString();
                if ("".equals(name)||name==null){
                    ToastUtils.showShort("请输入姓名");
                }else if (mTvAddCardBankname.getText().toString().length() == 0 || met_banknumber.getText().toString().length() == 0 || met_add_card_phone.getText() == null) {
                    Toast.makeText(this, "请选择银行并输入卡号和手机号", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.AddorUpdateAccountPayInfo(userId, "Bank", mTvAddCardBankname.getText().toString(), met_banknumber.getText().toString(),name);
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
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    userInfo = baseResult.getData().getData().get(0);
                    if (userInfo.getTrueName() == null) {
                        return;
                    } else {
//                         mTvAddCardName.setText(userInfo.getTrueName());
                        if (userInfo.getPhone() == null) {
                            return;
                        } else {
                            met_add_card_phone.setText(userInfo.getPhone());
                        }

                    }


                }
                break;

            default:
                break;
        }


    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    setResult(2000);
                    EventBus.getDefault().post("GetAccountPayInfoList");
                    Add_Card_Activity.this.finish();

                } else {
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }

    /*根据卡号判断是否支持银行*/
    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1() && !baseResult.getData().getItem2().equals("")) {

                    mTvAddCardBankname.setText(baseResult.getData().getItem2()); //绑定银行名


                } else {//不支持的银行
                    met_banknumber.setText("");
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("暂时不支持绑定该银行")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
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
            default:
                break;
        }
    }


}
