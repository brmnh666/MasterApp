package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.m7.imkfsdk.utils.ToastUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShootActivity extends BaseActivity {
    @BindView(R.id.jcameraview)
    JCameraView mJcameraview;
    private Bitmap bit;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shoot;
    }

    @Override
    protected void initData() {
//设置视频保存路径
        mJcameraview.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");

//设置只能录像或只能拍照或两种都可以（默认两种都可以）
        mJcameraview.setFeatures(JCameraView.BUTTON_STATE_BOTH);

//设置视频质量
        mJcameraview.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

        //JCameraView监听
        mJcameraview.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //打开Camera失败回调
                Log.i("CJT", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                //没有录取权限回调
                Log.i("CJT", "AudioPermissionError");
            }
        });

        mJcameraview.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
                Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
                bit = bitmap;
//                ToastUtils.showShort(bitmap+"");
                EventBus.getDefault().post(bit);
                ShootActivity.this.finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //获取视频路径
                Log.i("CJT", "url = " + url);
//                ToastUtils.showShort(url);
                bit=firstFrame;
                EventBus.getDefault().post(bit);
                ShootActivity.this.finish();
            }
        });

//左边按钮点击事件
        mJcameraview.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                ShootActivity.this.finish();
            }
        });
        //右边按钮点击事件
        mJcameraview.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
//                Toast.makeText(ShootActivity.this,"Right",Toast.LENGTH_SHORT).show();
//                EventBus.getDefault().post(bit);
                ShootActivity.this.finish();
            }
        });



    }


    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mJcameraview.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mJcameraview.onPause();
    }
}
