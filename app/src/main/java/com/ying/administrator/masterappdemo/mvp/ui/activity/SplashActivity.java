package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;

import java.util.Timer;
import java.util.TimerTask;

/*引导页*/
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private int recLen=5; //倒计时5秒
    private TextView tv_splash_skin;
    Timer timer =new Timer();
    private Handler handler;
    private Runnable runnable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();//隐藏actionbar
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash);

        tv_splash_skin=findViewById(R.id.tv_splash_skin);
        tv_splash_skin.setOnClickListener(this);

        timer.schedule(task,0,1000);
        /**
         * 正常情况下不点击跳过
         */
        handler =new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                /*调转到主界面界面*/
                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();

            }
        },5000); //延时5秒后发送信息


    }


    TimerTask task= new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { //主线程
                @Override
                public void run() {
                    recLen--;
                    tv_splash_skin.setText("跳过 "+recLen);
                    if (recLen<0){
                        timer.cancel();
                        tv_splash_skin.setVisibility(View.GONE); //倒计时到0隐藏字体
                    }
                }
            });

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_splash_skin:

                /*调转到登录界面*/
                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                if (runnable!=null){
                    handler.removeCallbacks(runnable);
                }

                break;
            default:
                break;
        }
    }
}
