package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;

public class Share_Activity extends AppCompatActivity {
    private TextView tv_actionbar_title;
    private LinearLayout ll_return;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        ll_return=findViewById(R.id. ll_return);
       tv_actionbar_title.setText("分享有礼");
       ll_return.setOnClickListener(new CustomLister());

    }
    public class CustomLister implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_return:
                    Share_Activity.this.finish();
                    break;
            }
        }
    }
}
