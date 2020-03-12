package com.ying.administrator.masterappdemo.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class TradeTextWatcher implements TextWatcher {
    private EditText mEditText;
//      private TextView mTextView;

    public TradeTextWatcher(EditText edit, TextView text) {
        mEditText = edit;
//          mTextView = text;
    }

    @Override
    public void afterTextChanged(Editable arg0) {

        int len = mEditText.getText().length();

        mEditText.setSelection(len);

    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {

    }

    @Override
    public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

    }

}
