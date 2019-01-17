package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.BaseActivity.BaseActivity;
import com.ying.administrator.masterappdemo.mvp.ui.fragment.Home_Fragment;

/*认证通过activity*/
public class Certification_passed_Activity extends BaseActivity {
    private TextView tv_go_to_the_order;
    private LinearLayout ll_return;
    private TextView tv_actionbar_title;
    private ImageView img_actionbar_message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_passed);
        tv_go_to_the_order=findViewById(R.id.tv_go_to_the_order);
        ll_return=findViewById(R.id.ll_return);
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        img_actionbar_message=findViewById(R.id.img_actionbar_message);
        tv_go_to_the_order.setOnClickListener(new CustomOnClickListner());
        ll_return.setOnClickListener(new CustomOnClickListner());
        tv_actionbar_title.setText("认证通过");
        img_actionbar_message.setVisibility(View.INVISIBLE);



    }

    public class CustomOnClickListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_go_to_the_order:
                    Intent intent=new Intent(Certification_passed_Activity.this, Home_Fragment.class);
                    startActivity(intent);
                    Certification_passed_Activity.this.finish();
                    break;
                case  R.id.ll_return:
                    Certification_passed_Activity.this.finish();
                    break;

            }
        }
    }
}
