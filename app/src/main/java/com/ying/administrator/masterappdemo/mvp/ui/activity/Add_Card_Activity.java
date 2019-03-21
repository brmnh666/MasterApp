package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.entity.Bank;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.ChooseBankAdapter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.Redeploy_Adapter;
import com.ying.administrator.masterappdemo.util.BankUtil;
import com.ying.administrator.masterappdemo.widget.CustomDialog_ChooseBank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*绑定银行卡页面*/
public class Add_Card_Activity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.et_add_card_verification_code)
    EditText mEtAddCardVerificationCode;
    @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;


    private CustomDialog_ChooseBank customDialog_chooseBank;//选择银行dialog
    private RecyclerView recyclerView_custom_bank;//显示银行的RecyclerView
    private ChooseBankAdapter chooseBankAdapter;
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

    }

    @Override
    protected void initView() {
        customDialog_chooseBank = new CustomDialog_ChooseBank(mActivity);

    }

    @Override
    protected void setListener() {
        mLlChooseBank.setOnClickListener(this);
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
                recyclerView_custom_bank=customDialog_chooseBank.findViewById(R.id.recyclerView_custom_choosebank);
                recyclerView_custom_bank.setLayoutManager(new LinearLayoutManager(mActivity));
                chooseBankAdapter=new ChooseBankAdapter(R.layout.item_bank,BankUtil.getBankList(),mActivity);
                recyclerView_custom_bank.setAdapter(chooseBankAdapter);


                chooseBankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.ll_choose_bank:
                                mTvAddCardBankname.setText(((Bank)adapter.getItem(position)).getBankname());
                                customDialog_chooseBank.dismiss();
                                break;
                        }

                    }
                });



            break;
            default:
                break;
        }
    }
}
