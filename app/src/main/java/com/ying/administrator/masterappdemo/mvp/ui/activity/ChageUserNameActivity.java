package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChageUserNameActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.btn_save)
    Button mBtnSave;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_username;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("修改昵称");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_return:
                ChageUserNameActivity.this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
