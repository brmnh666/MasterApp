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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.base.BaseActivity;
import com.ying.administrator.masterappdemo.base.BaseResult;
import com.ying.administrator.masterappdemo.common.Config;
import com.ying.administrator.masterappdemo.common.DefineView;
import com.ying.administrator.masterappdemo.entity.Data;
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
    private List<SubUserInfo.SubUserInfoDean> subUserInfolist=new ArrayList<>();
    private SubAccountAdapter subAccountAdapter;
    private int cancleposition;
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
        }*/
         mPresenter.GetUserInfoList(userID,"1");
         mPresenter.GetChildAccountByParentUserID(userID);



         subAccountAdapter = new SubAccountAdapter(R.layout.item_sub_account, subUserInfolist,mActivity);
         mRvSubAccount.setLayoutManager(new LinearLayoutManager(mActivity));
         mRvSubAccount.setAdapter(subAccountAdapter);
         subAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
             @Override
             public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                 switch (view.getId()){
                     case R.id.tv_close_account:
                     case R.id.img_tv_close_account:
                         final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                         dialog.setMessage("是否删除子账号")
                                 //.setImageResId(R.mipmap.ic_launcher)
                                 .setTitle("提示")
                                 .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                             @Override
                             public void onPositiveClick() {//删除用户
                                 cancleposition=position;
                                 mPresenter.CancelChildAccount(((SubUserInfo.SubUserInfoDean)adapter.getData().get(position)).getUserID(),userID);
                                 dialog.dismiss();
                             }

                             @Override
                             public void onNegtiveClick() {//取消
                                 dialog.dismiss();
                                 // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                             }
                         }).show();


                         break;
                         default:
                             break;
                 }

             }
         });



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

    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {

       switch (baseResult.getStatusCode()){
           case 200:
               subUserInfolist.addAll(baseResult.getData());
               subAccountAdapter.notifyDataSetChanged();
               break;
               default:
                   break;

         }


    }

    @Override
    public void CancelChildAccount(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()){
            case 200:
                if ( baseResult.getData().isItem1()){
                    subAccountAdapter.remove(cancleposition);

                }

                break;
                default:break;
        }
    }
}
