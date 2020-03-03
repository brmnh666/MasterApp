package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.ChangePayPasswordContract;
import com.ying.administrator.masterappdemo.mvp.model.ChangePayPasswordModel;
import com.ying.administrator.masterappdemo.mvp.presenter.ChangePayPasswordPresenter;
import com.ying.administrator.masterappdemo.widget.paypassword.PasswordEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingPayPasswordActivity extends BaseActivity<ChangePayPasswordPresenter, ChangePayPasswordModel> implements View.OnClickListener, PasswordEditText.PasswordFullListener, ChangePayPasswordContract.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.ll_customer_service)
    LinearLayout mLlCustomerService;
    @BindView(R.id.passwordEdt)
    PasswordEditText mPasswordEdt;
    @BindView(R.id.tv_prompt)
    TextView mTvPrompt;
    @BindView(R.id.img_clean)
    ImageView mImgClean;
    @BindView(R.id.img_delete)
    ImageView mImgDelete;
    @BindView(R.id.keyboard)
    LinearLayout mKeyboard;
    private int Type;  //1设置 2修改

    private String paypassword; //支付密码

    private List<String> paylist = new ArrayList<>();
    private SPUtils spUtils;
    private String userID;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_settingpaypassword;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userID, "1");
        mPasswordEdt.setPasswordFullListener(this);
        setItemClickListener(mKeyboard);

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 给每一个自定义数字键盘上的View 设置点击事件
     *
     * @param view
     */
    private void setItemClickListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                //不断的给里面所有的View设置setOnClickListener
                View childView = ((ViewGroup) view).getChildAt(i);
                setItemClickListener(childView);
            }
        } else {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
        if (v instanceof TextView) {
            String number = ((TextView) v).getText().toString().trim();
            mPasswordEdt.addPassword(number);
        }
        if (v instanceof ImageView) {
            switch (v.getId()) {
                case R.id.img_clean:
                    mPasswordEdt.cleanPassword();
                    break;
                case R.id.img_delete:
                    mPasswordEdt.deletePassword();
                    break;
            }
        }
    }

    @Override
    public void passwordFull(String password) {
        Log.d("=======>pass", password);
        if (Type == 1) {
            mPresenter.ChangePayPassword(userID, "", password);
        } else if (Type == 2) {
            if (paylist.size() == 0) { //输入旧支付密码
                if (!paypassword.equals(password)) {
                    Toast.makeText(mActivity, "旧支付密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    paylist.add(password);
                    mPasswordEdt.cleanPassword();//清除支付密码 输入新的支付密码
                    mTvPrompt.setText("请输入新支付密码");
                }

            } else {
                mPresenter.ChangePayPassword(userID, paypassword, password);
            }


        }


    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() != null) {
                    /*判断是否有支付密码*/

                    if ("".equals(baseResult.getData().getData().get(0).getPayPassWord())) {
                        mTvTitle.setText("设置支付密码");
//                        mTvActionbarTitle.setVisibility(View.VISIBLE);
                        Type = 1;
                        mTvPrompt.setText("请输入六位密码");
                    } else {
                        paypassword = baseResult.getData().getData().get(0).getPayPassWord();
                        mTvTitle.setText("修改支付密码");
//                        mTvActionbarTitle.setVisibility(View.VISIBLE);
                        Type = 2;
                        mTvPrompt.setText("请输入旧支付密码");
                    }

                }

                break;
        }


    }

    @Override
    public void ChangePayPassword(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (Type == 1) {
                        Toast.makeText(mActivity, "设置支付密码成功", Toast.LENGTH_SHORT).show();
                        SettingPayPasswordActivity.this.finish();
                    } else if (Type == 2) {
                        Toast.makeText(mActivity, "修改支付密码成功", Toast.LENGTH_SHORT).show();
                        SettingPayPasswordActivity.this.finish();
                    }
                }

                break;

        }

    }
}
