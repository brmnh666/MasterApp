package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;

public class Opinion_Activity extends AppCompatActivity {
    private static final int MAX_COUNT = 200;  //显示的最大字数

    private LinearLayout ll_return;
    private TextView tv_actionbar_title;
    private ImageView img_actionbar_message;
    private EditText et_opinion;
    private TextView tv_word_count;
    private TextView tv_account_problem;
    private TextView tv_payment_issues;
    private TextView tv_other_questions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);
        ll_return=findViewById(R.id.ll_return);
        tv_actionbar_title=findViewById(R.id.tv_actionbar_title);
        img_actionbar_message=findViewById(R.id.img_actionbar_message);
        tv_word_count=findViewById(R.id.tv_word_count);
        tv_account_problem = findViewById(R.id.tv_account_problem);
        tv_payment_issues = findViewById(R.id.tv_payment_issues);
        tv_other_questions = findViewById(R.id.tv_other_questions);
        tv_account_problem.setOnClickListener(new CustomClickListnear());
        tv_payment_issues.setOnClickListener(new CustomClickListnear());
        tv_other_questions.setOnClickListener(new CustomClickListnear());


        et_opinion=findViewById(R.id.et_opinion);
        ll_return.setOnClickListener(new CustomClickListnear());
        tv_actionbar_title.setText("意见反馈");
        img_actionbar_message.setVisibility(View.INVISIBLE);
        et_opinion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_word_count.setText((MAX_COUNT - editable.length())+"/200");
            }
        });

        /*对edittext进行监听*/

    }
    public class CustomClickListnear implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_return:
                    Opinion_Activity.this.finish();
                    break;
                case R.id.tv_account_problem:
                    tv_account_problem.setSelected(true);
                    tv_payment_issues.setSelected(false);
                    tv_other_questions.setSelected(false);
                    break;
                case R.id.tv_payment_issues:
                    tv_account_problem.setSelected(false);
                    tv_payment_issues.setSelected(true);
                    tv_other_questions.setSelected(false);
                    break;
                case R.id.tv_other_questions:
                    tv_account_problem.setSelected(false);
                    tv_payment_issues.setSelected(false);
                    tv_other_questions.setSelected(true);
                        break;

            }
        }
    }
}
