package com.ying.administrator.masterappdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.util.TUtil;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
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

    /*???*/
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

    }

    @Override
    public void hideProgress() {

    }
}
