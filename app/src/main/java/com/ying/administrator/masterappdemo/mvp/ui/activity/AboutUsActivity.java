package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.common.DefineView;

public class AboutUsActivity extends AppCompatActivity implements DefineView {
private LinearLayout ll_return;
private TextView tv_actionbar_title;
private ImageView img_actionbar_message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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
        tv_actionbar_title.setText("关于我们");
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
                    AboutUsActivity.this.finish();
                    break;
            }
        }
    }
}
