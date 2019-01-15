package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.BaseActivity.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;

public class Wallet_Activity extends BaseActivity implements DefineView {
private LinearLayout ll_return;
private TextView tv_actionbar_title;
private ImageView img_actionbar_message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        ll_return=findViewById(R.id.ll_return);
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        img_actionbar_message=findViewById(R.id.img_actionbar_message);


    }

    @Override
    public void initValidata() {
        tv_actionbar_title.setText("我的钱包");
        img_actionbar_message.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initListener() {
        ll_return.setOnClickListener(new Customlistner());
    }

    @Override
    public void bindData() {

    }
    public class Customlistner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.ll_return:
                    Wallet_Activity.this.finish();
                    break;
            }
        }
    }
}
