package com.ying.administrator.masterappdemo.widget.paypassword;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;


public class PayPasswordView extends LinearLayout implements View.OnClickListener, PasswordEditText.PasswordFullListener {
    private LinearLayout mKeyBoardView;
    private ImageView img_back;
    private PasswordEditText mPasswordEditText;

    public PasswordEditText getmPasswordEditText() {
        return mPasswordEditText;
    }

    public void setmPasswordEditText(PasswordEditText mPasswordEditText) {
        this.mPasswordEditText = mPasswordEditText;
    }

    public ImageView getImg_back() {
        return img_back;
    }

    public void setImg_back(ImageView img_back) {
        this.img_back = img_back;
    }

    public PayPasswordView(Context context) {
        this(context, null);
    }

    public PayPasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayPasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.pay_password_layout, this);
        mKeyBoardView = findViewById(R.id.keyboard);
        img_back=findViewById(R.id.img_back);
        mPasswordEditText = findViewById(R.id.passwordEdt);
        mPasswordEditText.setPasswordFullListener(this);
        setItemClickListener(mKeyBoardView);
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
        if (v instanceof TextView) {
            String number = ((TextView) v).getText().toString().trim();
            mPasswordEditText.addPassword(number);
        }
        if (v instanceof ImageView) {
            switch (v.getId()){
                case R.id.img_clean:
                   mPasswordEditText.cleanPassword();
                    break;
                case R.id.img_delete:
                    mPasswordEditText.deletePassword();
                    break;
            }

        }
    }

    @Override
    public void passwordFull(String password) {
        //Toast.makeText(getContext(), "你输入的密码是：" + password, Toast.LENGTH_SHORT).show();
    }


}
