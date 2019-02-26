package com.ying.administrator.masterappdemo.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.SubAccount;
import com.ying.administrator.masterappdemo.entity.SubUserInfo;
import com.ying.administrator.masterappdemo.entity.UserInfo;
import com.ying.administrator.masterappdemo.mvp.contract.SubAccountContract;
import com.ying.administrator.masterappdemo.mvp.model.SubAccountModel;
import com.ying.administrator.masterappdemo.mvp.presenter.SubAccountPresenter;
import com.ying.administrator.masterappdemo.mvp.ui.adapter.SubAccountAdapter;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;
import com.ying.administrator.masterappdemo.widget.QRCodeDialog;
import com.ying.administrator.masterappdemo.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SubAccountManagementActivity extends BaseActivity<SubAccountPresenter, SubAccountModel> implements View.OnClickListener, SubAccountContract.View {
    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_actionbar_return)
    TextView mTvActionbarReturn;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.tv_actionbar_title)
    TextView mTvActionbarTitle;
    @BindView(R.id.img_actionbar_message)
    ImageView mImgActionbarMessage;
    @BindView(R.id.actionbar_layout)
    RelativeLayout mActionbarLayout;
    @BindView(R.id.ll_add_account)
    LinearLayout mLlAddAccount;
    @BindView(R.id.rv_sub_account)
    RecyclerView mRvSubAccount;
    @BindView(R.id.img_add_sub_account)
    ImageView mImg_sub_account_qrcode;
    @BindView(R.id.tv_add_sub_account)
    TextView mTv_add_sub_account;

    private ArrayList<SubAccount> subAccountArrayList;
    SPUtils spUtils = SPUtils.getInstance("token");
    private String userID;//用户id
    private QRCodeDialog qrCodeDialog;
    private UserInfo.UserInfoDean userInfoDean=new UserInfo.UserInfoDean();
    @Override
    protected int setLayoutId() {
        return R.layout.activtity_sub_account_management;
    }

    @Override
    protected void initData() {
        userID = spUtils.getString("userName"); //获取用户id

       /* subAccountArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            subAccountArrayList.add(new SubAccount());
        }
        SubAccountAdapter subAccountAdapter = new SubAccountAdapter(R.layout.item_sub_account, subAccountArrayList);
        mRvSubAccount.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSubAccount.setAdapter(subAccountAdapter);*/

       mPresenter.GetUserInfoList(userID,"1");


    }

    @Override
    public void initView() {
        mTvActionbarTitle.setText("子账号管理");
        mImgActionbarMessage.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void setListener() {
        mLlReturn.setOnClickListener(this);
        mImg_sub_account_qrcode.setOnClickListener(this);
        mTv_add_sub_account.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_return:
                SubAccountManagementActivity.this.finish();
                break;
            case R.id.img_add_sub_account:
            case R.id.tv_add_sub_account:

                if (userInfoDean.getParentUserID()==null){
                    qrCodeDialog=new QRCodeDialog(mActivity,userID);
                    qrCodeDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    qrCodeDialog.show();
                }else {//存在父账号
                    final CommonDialog_Home dialog = new CommonDialog_Home(this);
                    dialog.setMessage("已是子账号不能再添加")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(true).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                        }
                    }).show();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfoDean=baseResult.getData().getData().get(0);
                break;
                default:

                    break;

        }
    }
}
