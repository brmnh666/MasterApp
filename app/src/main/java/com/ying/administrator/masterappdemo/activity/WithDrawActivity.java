package com.ying.administrator.masterappdemo.activity;


/*提现activity*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.activity.BaseActivity.BaseActivity;
import com.ying.administrator.masterappdemo.common.DefineView;

public class WithDrawActivity extends BaseActivity implements DefineView {
    private ImageView img_actionbar_return;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        initView();
        initValidata();
        initListener();
    }

    @Override
    public void initView() {
        img_actionbar_return=findViewById(R.id.img_actionbar_return);


    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        img_actionbar_return.setOnClickListener(new CustomListner());
    }

    @Override
    public void bindData() {

    }

    public class CustomListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_actionbar_return:
                    WithDrawActivity.this.finish();
                    break;

            }
        }
    }

}
