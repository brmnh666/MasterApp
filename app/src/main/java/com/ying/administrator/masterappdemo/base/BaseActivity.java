package com.ying.administrator.masterappdemo.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.SkinAppCompatDelegateImpl;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.mvp.ui.activity.LoginActivity;
import com.ying.administrator.masterappdemo.util.TUtil;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Activity基类
 * Created by geyifeng on 2017/5/9.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity implements BaseView {

    private InputMethodManager imm;
    private Unbinder unbinder;
    public Context mActivity;
    public P mPresenter;
    public M mModel;
    private RxManager mRxManage;
    private ZLoadingDialog dialog;
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        try {
            if (!EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().register(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //竖屏
        setContentView(setLayoutId());
        this.mActivity=this;

        mPresenter = obtainPresenter();
        mModel = obtainModel();
        if (mPresenter != null) {
            mPresenter.mContext = mActivity;
            if (this instanceof BaseView) {
                mPresenter.setVM(this, mModel);
            }
            mPresenter.onCreate(savedInstanceState);
        }

        mRxManage = new RxManager();
        //绑定控件
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
      /*  if (isImmersionBarEnabled())
            initImmersionBar();*/
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();

    }

    protected P obtainPresenter() {
        return TUtil.getT(this, 0);
    }

    protected M obtainModel() {
        return TUtil.getT(this, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      unbinder.unbind();
      if (mPresenter!=null){
          mPresenter.onDestroy();
      }
      EventBus.getDefault().unregister(this);
     /*     this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁*/
    }

    protected abstract int setLayoutId();

    protected void initImmersionBar() {
      /*  //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.init();*/
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void setListener();

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }



   /* public View getEmptyView() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data,null);
    }
*/
    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

  /*  @Override
    public void onBackPressed() {
        if (!HandleBackUtil.handleBackPress(this)) {
            super.onBackPressed();
        }
    }*/

     /*没有配件*/
    public View getEmptyViewAC() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_empty_ac,null);
    }

    /*没有服务*/
    public View getEmptyViewService() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_empty_service,null);
    }

    public View getEmptyView() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data,null);
    }

    public View getEmptyViewCz() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data_cz,null);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        dialog=new ZLoadingDialog(mActivity);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    @Override
    public void hideProgress() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    /*电话*/
    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码
    /**
     * 判断是否有某项权限
     * @param string_permission 权限
     * @param request_code 请求码
     * @return
     */
    public boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(mActivity, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions((Activity) mActivity, new String[]{string_permission}, request_code);
        }
        return flag;
    }

    /**
     * 检查权限后的回调
     * @param requestCode 请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION: //拨打电话
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    Toast.makeText(mActivity,"请允许拨号权限后再试",Toast.LENGTH_SHORT).show();
                } else {//成功
//                    call("tel:"+"10086");
                }
                break;
        }
    }
    /**
     * 拨打电话（直接拨打）
     * @param telPhone 电话
     */
    public void call(String telPhone) {
        if (checkReadPermission(Manifest.permission.CALL_PHONE, REQUEST_CALL_PERMISSION)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
            startActivity(intent);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if ("账号在别处登录".equals(message)){
            final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
            dialog.setMessage("账号在别处登录是否重新登录")
                    //.setImageResId(R.mipmap.ic_launcher)
                    .setTitle("提示")
                    .setSingle(true).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                @Override
                public void onPositiveClick() {//重新登录
                    ActivityUtils.finishAllActivities();
                    startActivity(new Intent(mActivity, LoginActivity.class));
//                    finish();
                }

                @Override
                public void onNegtiveClick() {//取消
                    dialog.dismiss();
                    // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}
