package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.entity.Data;
import com.ying.administrator.masterappdemo.mvp.contract.LoginContract;
import com.ying.administrator.masterappdemo.mvp.model.LoginModel;
import com.ying.administrator.masterappdemo.mvp.presenter.LoginPresenter;

import java.util.Timer;
import java.util.TimerTask;

/*引导页*/
public class SplashActivity extends BaseActivity<LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {

    private int recLen=4; //倒计时3秒
    private TextView tv_splash_skin;
    Timer timer =new Timer();
    private Handler handler;
    private Runnable runnable;
    private String userName;
    private String password;
    private boolean isLogin;
    private String admintoken;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userName=spUtils.getString("userName");
        password = spUtils.getString("passWord");
        isLogin = spUtils.getBoolean("isLogin");
        admintoken=spUtils.getString("adminToken");

   /*     Log.d("======>", userName);
        Log.d("======>", password);
        Log.d("======>", String.valueOf(isLogin));
        Log.d("======>", admintoken);*/
    }

    @Override
    protected void initView() {
        tv_splash_skin=findViewById(R.id.tv_splash_skin);
    }

    @Override
    protected void setListener() {
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
                if (userName!=null&&password!=null&&isLogin){ //存在用户名说明登录过 直接登录到主界面
                    Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }else {
                    Intent intent =new Intent(SplashActivity.this,Login_New_Activity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }

            }
        },3000); //延时3秒后发送信息


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

                /*调转到主界面界面*/
                if (userName!=null&&password!=null&&isLogin){ //存在用户名说明登录过 直接登录到主界面
                    Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }else {
                    Intent intent =new Intent(SplashActivity.this,Login_New_Activity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }


                if (runnable!=null){
                    handler.removeCallbacks(runnable);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void Login(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {

    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void contentLoading() {

    }

    @Override
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
