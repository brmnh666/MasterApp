package com.ying.administrator.masterappdemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.blankj.utilcode.util.KeyboardUtils;

public class HideSoftInputDialog extends Dialog {


    public HideSoftInputDialog(Context context) {
        super(context);
    }

    public HideSoftInputDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected HideSoftInputDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        View view = getCurrentFocus();
        if (view!=null){
            KeyboardUtils.hideSoftInput(view);
        }
        super.dismiss();
    }

}
